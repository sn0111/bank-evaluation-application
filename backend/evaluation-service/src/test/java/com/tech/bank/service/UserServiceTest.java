package com.tech.bank.service;

import com.tech.bank.dao.UserDAO;
import com.tech.bank.data.dto.UserDTO;
import com.tech.bank.data.entity.UserEntity;
import com.tech.bank.servie.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    // Test getAllUsers method
    @Test
    public void testGetAllUsers() {
        // Set up mock behavior
        List<UserEntity> entityList = new ArrayList<>();
        // Add some dummy UserEntity objects to the list

        when(userDAO.users()).thenReturn(entityList);
        when(modelMapper.map(any(), eq(UserDTO.class))).thenReturn(new UserDTO());

        // Call the method to be tested
        List<UserDTO> result = userService.getAllUsers();

        // Assertions
        assertNotNull(result);
        // Add more assertions as needed
    }

    // Test saveUser method
    @Test
    public void testSaveUser() {
        // Set up test data
        UserDTO dto = new UserDTO();
        dto.setUserEmail("s@gmail.com");
        dto.setPassword("pwd");
        // Set properties for dto

        // Set up mock behavior
        when(modelMapper.map(any(), eq(UserEntity.class))).thenReturn(new UserEntity());
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userDAO.saveUser(any())).thenReturn(new UserEntity());
        when(modelMapper.map(any(), eq(UserDTO.class))).thenReturn(dto);

        // Call the method to be tested
        UserDTO result = userService.saveUser(dto);

        // Assertions
        assertNotNull(result);
        // Add more assertions as needed
    }

    // Test updateUser method
    @Test
    public void testUpdateUser() {
        // Set up test data
        UserDTO dto = new UserDTO();
        dto.setUserEmail("s@gmail.com");
        dto.setPassword("pwd");
        dto.setUserId(1L);
        // Set properties for dto

        // Set up mock behavior
        when(modelMapper.map(any(), eq(UserEntity.class))).thenReturn(new UserEntity());
        when(userDAO.findByUserId(anyLong())).thenReturn(Optional.of(new UserEntity()));
        when(userDAO.saveUser(any())).thenReturn(new UserEntity());
        when(modelMapper.map(any(), eq(UserDTO.class))).thenReturn(dto);

        // Call the method to be tested
        UserDTO result = userService.updateUser(dto);

        // Assertions
        assertNotNull(result);
        // Add more assertions as needed
    }

    // Test loginUser method with invalid credentials
    @Test
    public void testLoginUserInvalidCredentials() {
        // Set up test data
        UserDTO dto = new UserDTO();
        dto.setUserEmail("s@gmail.com");
        dto.setPassword("pwd");
        // Set properties for dto

        // Set up mock behavior
        when(userDAO.userDetails(anyString())).thenReturn(Optional.of(new UserEntity()));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        // Assertions
        assertThrows(BadCredentialsException.class, () -> {
            userService.loginUser(dto);
        });
    }
}
