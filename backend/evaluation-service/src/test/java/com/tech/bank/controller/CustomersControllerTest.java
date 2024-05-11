package com.tech.bank.controller;

import com.tech.bank.data.dto.CustomersDTO;
import com.tech.bank.servie.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
@DirtiesContext
@AutoConfigureMockMvc(addFilters = false)
public class CustomersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @InjectMocks
    private CustomersController customersController;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testFindCustomers() throws Exception {
        CustomersDTO customersDTO = new CustomersDTO();
        customersDTO.setCustomerUuid("123456789");
        customersDTO.setCustomerNumber("CUST001");
        customersDTO.setShortName("John Doe");
        customersDTO.setIsIndividual("Yes");
        customersDTO.setNationality("USA");
        customersDTO.setNationalityNumber("US12345");
        customersDTO.setNationalityDescription("American");
        customersDTO.setStreetAddress("123 Main Street");
        customersDTO.setAddressLine2("Apt 101");
        customersDTO.setAddress_line3("");
        customersDTO.setTownCountry("Anytown, USA");
        customersDTO.setPostCode("12345");
        customersDTO.setCountry("United States");
        customersDTO.setCountryCode("US");
        customersDTO.setCountryCodeNumber("1");
        customersDTO.setDispatchCode("DISP001");
        customersDTO.setCommunicationChannel("Phone");
        customersDTO.setPhoneNumber("123-456-7890");
        customersDTO.setOfficePhoneNumber("123-456-7891");
        customersDTO.setFaxNumber("123-456-7892");
        customersDTO.setMobileOperatorIso("ISO123");
        customersDTO.setMobileOperatorCode("OP001");
        customersDTO.setSmsNumber("987-654-3210");
        customersDTO.setEmail("john.doe@example.com");

        List<CustomersDTO> customers = Arrays.asList(
                customersDTO,customersDTO
        );
        String customerNumber = "123456"; // Sample customer number

        when(customerService.findCustomers(customerNumber)).thenReturn(customers);

        mockMvc.perform(get("/api/bank/customers/{customerNumber}", customerNumber))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].customerUuid").exists());

        verify(customerService, times(1)).findCustomers(customerNumber);
        verifyNoMoreInteractions(customerService);
    }
}
