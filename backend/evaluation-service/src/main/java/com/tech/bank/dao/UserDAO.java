package com.tech.bank.dao;

import com.tech.bank.data.entity.UserEntity;
import com.tech.bank.data.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDAO {

    private final UserRepository userRepository;

    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> users(){
        return userRepository.findAll();
    }

    public Optional<UserEntity> findByUserId(Long userId){
        return userRepository.findById(userId);
    }

    public UserEntity saveUser(UserEntity entity){
        return userRepository.save(entity);
    }

    public Optional<UserDetails> userDetails(String userEmail){
        return userRepository.findByUserEmail(userEmail);
    }

    @Cacheable(cacheNames = "all-users")
    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }
}
