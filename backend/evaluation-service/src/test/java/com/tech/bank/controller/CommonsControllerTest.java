package com.tech.bank.controller;

import com.tech.bank.data.dto.CurrencyDTO;
import com.tech.bank.data.dto.DocumentTypeDTO;
import com.tech.bank.data.dto.ReferenceNumberDTO;
import com.tech.bank.servie.CommonsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
public class CommonsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommonsService commonsService;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testGetCurrencies() throws Exception {
        List<CurrencyDTO> currencies = Arrays.asList(
                new CurrencyDTO("uuid1", "USD", "United States Dollar"),
                new CurrencyDTO("uuid2", "EUR", "Euro"),
                new CurrencyDTO("uuid3", "GBP", "British Pound Sterling")
        );
        when(commonsService.getCurrencies()).thenReturn(currencies);

        mockMvc.perform(get("/api/bank/commons/currencies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].currencyUuid").value("uuid1"))
                .andExpect(jsonPath("$.data[0].currencyName").value("USD"))
                .andExpect(jsonPath("$.data[0].description").value("United States Dollar"))
                .andExpect(jsonPath("$.data[1].currencyUuid").value("uuid2"))
                .andExpect(jsonPath("$.data[1].currencyName").value("EUR"))
                .andExpect(jsonPath("$.data[1].description").value("Euro"))
                .andExpect(jsonPath("$.data[2].currencyUuid").value("uuid3"))
                .andExpect(jsonPath("$.data[2].currencyName").value("GBP"))
                .andExpect(jsonPath("$.data[2].description").value("British Pound Sterling"));

        verify(commonsService, times(1)).getCurrencies();
        verifyNoMoreInteractions(commonsService);
    }

    @Test
    public void testGetDocumentTypes() throws Exception {
        List<DocumentTypeDTO> documentTypes = Arrays.asList(
                new DocumentTypeDTO("uuid1", "Passport", "Official identification document"),
                new DocumentTypeDTO("uuid2", "ID Card", "Identity card"),
                new DocumentTypeDTO("uuid3", "Driver's License", "Driving license")
        );
        when(commonsService.getDocumentTypes()).thenReturn(documentTypes);

        mockMvc.perform(get("/api/bank/commons/document-types"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].documentUuid").value("uuid1"))
                .andExpect(jsonPath("$.data[0].documentName").value("Passport"))
                .andExpect(jsonPath("$.data[0].description").value("Official identification document"))
                .andExpect(jsonPath("$.data[1].documentUuid").value("uuid2"))
                .andExpect(jsonPath("$.data[1].documentName").value("ID Card"))
                .andExpect(jsonPath("$.data[1].description").value("Identity card"))
                .andExpect(jsonPath("$.data[2].documentUuid").value("uuid3"))
                .andExpect(jsonPath("$.data[2].documentName").value("Driver's License"))
                .andExpect(jsonPath("$.data[2].description").value("Driving license"));

        verify(commonsService, times(1)).getDocumentTypes();
        verifyNoMoreInteractions(commonsService);
    }

    @Test
    public void testGetReference() throws Exception {
        ReferenceNumberDTO reference = new ReferenceNumberDTO("REF123", 123L);
        when(commonsService.getReference()).thenReturn(reference);

        mockMvc.perform(get("/api/bank/commons/reference"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.referenceName").value("REF123"))
                .andExpect(jsonPath("$.data.referenceNumber").value(123));

        verify(commonsService, times(1)).getReference();
        verifyNoMoreInteractions(commonsService);
    }
}
