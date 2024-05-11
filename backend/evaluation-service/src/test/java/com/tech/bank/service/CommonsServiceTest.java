package com.tech.bank.service;

import com.tech.bank.dao.CommonsDAO;
import com.tech.bank.data.dto.CurrencyDTO;
import com.tech.bank.data.dto.DocumentTypeDTO;
import com.tech.bank.data.dto.ReferenceNumberDTO;
import com.tech.bank.data.entity.CurrencyEntity;
import com.tech.bank.data.entity.DocumentTypeEntity;
import com.tech.bank.data.entity.ReferenceNumberEntity;
import com.tech.bank.servie.CommonsService;
import com.tech.bank.util.BankEvaluationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CommonsServiceTest {

    @Mock
    private CommonsDAO commonsDAO;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CommonsService commonsService;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testGetCurrencies() {
        List<CurrencyEntity> currencyEntities = new ArrayList<>();
        // Add some dummy CurrencyEntity objects to the list

        List<CurrencyDTO> currencyDTOs = new ArrayList<>();
        // Add corresponding CurrencyDTO objects

        when(commonsDAO.getCurrencies()).thenReturn(currencyEntities);
        when(modelMapper.map(any(), eq(CurrencyDTO.class))).thenReturn(new CurrencyDTO());

        List<CurrencyDTO> result = commonsService.getCurrencies();

        assertEquals(currencyDTOs, result);
        verify(commonsDAO, times(1)).getCurrencies();
        verify(modelMapper, times(currencyEntities.size())).map(any(), eq(CurrencyDTO.class));
    }

    @Test
    public void testGetDocumentTypes() {
        List<DocumentTypeEntity> documentTypeEntities = new ArrayList<>();
        // Add some dummy DocumentTypeEntity objects to the list

        List<DocumentTypeDTO> documentTypeDTOs = new ArrayList<>();
        // Add corresponding DocumentTypeDTO objects

        when(commonsDAO.getDocumentTypes()).thenReturn(documentTypeEntities);
        when(modelMapper.map(any(), eq(DocumentTypeDTO.class))).thenReturn(new DocumentTypeDTO());

        List<DocumentTypeDTO> result = commonsService.getDocumentTypes();

        assertEquals(documentTypeDTOs, result);
        verify(commonsDAO, times(1)).getDocumentTypes();
        verify(modelMapper, times(documentTypeEntities.size())).map(any(), eq(DocumentTypeDTO.class));
    }

    @Test
    public void testGetReference_Success() throws BankEvaluationException {
        ReferenceNumberEntity referenceNumberEntity = new ReferenceNumberEntity();
        // Set properties for referenceNumberEntity

        ReferenceNumberDTO referenceNumberDTO = new ReferenceNumberDTO();
        // Set properties for referenceNumberDTO

        when(commonsDAO.getReferenceNumber("PV")).thenReturn(Optional.of(referenceNumberEntity));
        when(modelMapper.map(referenceNumberEntity, ReferenceNumberDTO.class)).thenReturn(referenceNumberDTO);

        ReferenceNumberDTO result = commonsService.getReference();

        assertEquals(referenceNumberDTO, result);
        verify(commonsDAO, times(1)).getReferenceNumber("PV");
        verify(modelMapper, times(1)).map(referenceNumberEntity, ReferenceNumberDTO.class);
    }

    @Test
    public void testGetReference_ThrowsException() {
        when(commonsDAO.getReferenceNumber("PV")).thenReturn(Optional.empty());

        assertThrows(BankEvaluationException.class, () -> commonsService.getReference());

        verify(commonsDAO, times(1)).getReferenceNumber("PV");
        verify(modelMapper, never()).map(any(), eq(ReferenceNumberDTO.class));
    }
}
