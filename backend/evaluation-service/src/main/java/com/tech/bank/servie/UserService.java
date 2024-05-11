package com.tech.bank.servie;

import com.tech.bank.dao.UserDAO;
import com.tech.bank.data.dto.UserDTO;
import com.tech.bank.data.entity.UserEntity;
import com.tech.bank.data.entity.UserRole;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDAO userDAO;

    private final PasswordEncoder encoder;

    private final ModelMapper modelMapper;

    public UserService(UserDAO userDAO, PasswordEncoder encoder, ModelMapper modelMapper) {
        this.userDAO = userDAO;
        this.encoder = encoder;
        this.modelMapper = modelMapper;
    }

    public List<UserDTO> getAllUsers(){
        return userDAO.users()
                .stream()
                .map(o->{
                    UserDTO dto = modelMapper.map(o, UserDTO.class);
                    dto.setPassword("");
                    return dto;
                })
                .toList();
    }



    public UserDTO saveUser(UserDTO dto){
        Assert.notNull(dto.getUserEmail(),"");
        Assert.notNull(dto.getPassword(),"");

        UserEntity entity = modelMapper.map(dto, UserEntity.class);
        entity.setRole(UserRole.USER);
        entity.setPassword(encoder.encode(entity.getPassword()));
        UserEntity userEntity = userDAO.saveUser(entity);
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        userDTO.setPassword("");
        return userDTO;
    }

    public UserDTO updateUser(UserDTO dto){
        Assert.notNull(dto.getUserId(),"");
        Assert.notNull(dto.getUserEmail(),"");
        Assert.notNull(dto.getPassword(),"");
        Optional<UserEntity> byUserId = userDAO.findByUserId(dto.getUserId());
        if(byUserId.isPresent()){
            UserEntity entity = modelMapper.map(dto, UserEntity.class);
            entity.setUserId(byUserId.get().getUserId());
            entity.setPassword(byUserId.get().getPassword());
            UserEntity userEntity = userDAO.saveUser(entity);

            UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
            userDTO.setPassword("");
            return userDTO;
        }
        return null;
    }

//    @Transactional
    public String getUser(String email){
        List<UserEntity> allUsers = userDAO.getAllUsers();
        UserEntity entity = new UserEntity();
        entity.setUserEmail("");
        entity.setUserName("");
        UserEntity user = userDAO.getAllUsers()
                .stream()
                .filter(e -> e.getUsername().equals(email))
                .findFirst()
                .orElse(entity);
        return user.getUserEmail();
    }

    public UserDTO loginUser(UserDTO dto){
        Assert.notNull(dto.getUserEmail(),"");
        Assert.notNull(dto.getPassword(),"");

        Optional<UserDetails> userDetails = userDAO.userDetails(dto.getUserEmail());
        if(userDetails.isPresent() &&  encoder.matches(dto.getPassword(),userDetails.get().getPassword())){
            UserEntity entity =(UserEntity) userDetails.get();
            dto.setRole(entity.getRole());
            dto.setUserId(entity.getUserId());
            dto.setBusinessUnit(entity.getBusinessUnit());
            dto.setUserEmail(entity.getUsername());
            dto.setUserName(entity.getUserEmail());
            dto.setContactNumber(entity.getContactNumber());
            dto.setPassword("");
            return dto;
        }
        throw new BadCredentialsException("Bad user credentials");
    }
}
