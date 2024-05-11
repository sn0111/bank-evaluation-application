package com.tech.bank.dao;

import com.tech.bank.data.entity.UserEntity;
import com.tech.bank.data.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserDAOTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDAO userDAO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUsers() {
        List<UserEntity> users = new ArrayList<>();
        // Add some dummy UserEntity objects to the list

        when(userRepository.findAll()).thenReturn(users);

        List<UserEntity> result = userDAO.users();

        assertEquals(users, result);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testFindByUserId() {
        Long userId = 123L;
        UserEntity userEntity = new UserEntity();
        // Set properties for userEntity

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        Optional<UserEntity> result = userDAO.findByUserId(userId);

        assertEquals(userEntity, result.orElse(null));
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void testSaveUser() {
        UserEntity userEntity = new UserEntity();
        // Set properties for userEntity

        when(userRepository.save(userEntity)).thenReturn(userEntity);

        UserEntity result = userDAO.saveUser(userEntity);

        assertEquals(userEntity, result);
        verify(userRepository, times(1)).save(userEntity);
    }


    @Test
    public void testUserDetails() {
        when(userRepository.findAll()).thenReturn(List.of(new UserEntity()));
        assertEquals(1, userDAO.getAllUsers().size());
    }

}
