package com.tech.bank.service;

import com.tech.bank.dao.CustomersDAO;
import com.tech.bank.data.dto.CustomersDTO;
import com.tech.bank.data.entity.CustomersEntity;
import com.tech.bank.servie.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    private CustomersDAO customersDAO;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveCustomer() {
        CustomersDTO customersDTO = new CustomersDTO();
        // Set properties for customersDTO

        CustomersEntity customersEntity = new CustomersEntity();
        // Set properties for customersEntity

        when(modelMapper.map(customersDTO, CustomersEntity.class)).thenReturn(customersEntity);
        when(customersDAO.saveCustomer(customersEntity)).thenReturn(customersEntity);
        when(modelMapper.map(customersEntity, CustomersDTO.class)).thenReturn(customersDTO);

        CustomersDTO result = customerService.saveCustomer(customersDTO);

        assertEquals(customersDTO, result);
        verify(modelMapper, times(1)).map(customersDTO, CustomersEntity.class);
        verify(customersDAO, times(1)).saveCustomer(customersEntity);
        verify(modelMapper, times(1)).map(customersEntity, CustomersDTO.class);
    }

    @Test
    public void testFindCustomers() {
        String customerNumber = "123";
        List<CustomersEntity> customersEntities = new ArrayList<>();
        // Add some dummy CustomersEntity objects to the list

        List<CustomersDTO> customersDTOs = new ArrayList<>();
        // Add corresponding CustomersDTO objects

        when(customersDAO.findByCustomerNumber(customerNumber)).thenReturn(customersEntities);
        when(modelMapper.map(any(), eq(CustomersDTO.class))).thenReturn(new CustomersDTO());

        List<CustomersDTO> result = customerService.findCustomers(customerNumber);

        assertEquals(customersDTOs, result);
        verify(customersDAO, times(1)).findByCustomerNumber(customerNumber);
        verify(modelMapper, times(customersEntities.size())).map(any(), eq(CustomersDTO.class));
    }
}
