package com.tech.bank.dao;

import com.tech.bank.data.entity.CustomersEntity;
import com.tech.bank.data.repository.CustomersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomersDAOTest {

    @Mock
    private CustomersRepository customersRepository;

    @InjectMocks
    private CustomersDAO customersDAO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveCustomer() {
        CustomersEntity customerEntity = new CustomersEntity();
        // Set properties for customerEntity

        when(customersRepository.save(customerEntity)).thenReturn(customerEntity);

        CustomersEntity result = customersDAO.saveCustomer(customerEntity);

        assertEquals(customerEntity, result);
        verify(customersRepository, times(1)).save(customerEntity);
    }

    @Test
    public void testFindByCustomerNumber() {
        String customerNumber = "123";
        List<CustomersEntity> customers = new ArrayList<>();
        // Add some dummy CustomersEntity objects to the list

        when(customersRepository.findByCustomerNumberIgnoreCaseContaining(customerNumber)).thenReturn(customers);

        List<CustomersEntity> result = customersDAO.findByCustomerNumber(customerNumber);

        assertEquals(customers, result);
        verify(customersRepository, times(1)).findByCustomerNumberIgnoreCaseContaining(customerNumber);
    }

    @Test
    public void testFindByCustomerUuid() {
        String customerUuid = "456";
        CustomersEntity customerEntity = new CustomersEntity();
        // Set properties for customerEntity

        when(customersRepository.findByCustomerUuid(customerUuid)).thenReturn(Optional.of(customerEntity));

        Optional<CustomersEntity> result = customersDAO.findByCustomerUuid(customerUuid);

        assertEquals(customerEntity, result.orElse(null));
        verify(customersRepository, times(1)).findByCustomerUuid(customerUuid);
    }
}
