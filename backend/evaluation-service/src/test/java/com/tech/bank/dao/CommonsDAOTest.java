package com.tech.bank.dao;

import com.tech.bank.data.entity.CommentsEntity;
import com.tech.bank.data.entity.CurrencyEntity;
import com.tech.bank.data.entity.DocumentTypeEntity;
import com.tech.bank.data.entity.ReferenceNumberEntity;
import com.tech.bank.data.repository.CommentsRepository;
import com.tech.bank.data.repository.CurrencyRepository;
import com.tech.bank.data.repository.DocumentTypeRepository;
import com.tech.bank.data.repository.ReferenceNumberRepository;
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

public class CommonsDAOTest {

    @Mock
    private DocumentTypeRepository documentTypeRepository;

    @Mock
    private CurrencyRepository currencyRepository;

    @Mock
    private CommentsRepository commentsRepository;

    @Mock
    private ReferenceNumberRepository referenceNumberRepository;

    @InjectMocks
    private CommonsDAO commonsDAO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetDocumentTypes() {
        List<DocumentTypeEntity> documentTypes = new ArrayList<>();
        // Add some dummy DocumentTypeEntity objects to the list

        when(documentTypeRepository.findAll()).thenReturn(documentTypes);

        List<DocumentTypeEntity> result = commonsDAO.getDocumentTypes();

        assertEquals(documentTypes, result);
        verify(documentTypeRepository, times(1)).findAll();
    }

    @Test
    public void testGetDocumentByUuid() {
        String documentUuid = "123";
        DocumentTypeEntity documentType = new DocumentTypeEntity();
        // Set properties for documentType

        when(documentTypeRepository.findByDocumentUuid(documentUuid)).thenReturn(Optional.of(documentType));

        Optional<DocumentTypeEntity> result = commonsDAO.getDocumentByUuid(documentUuid);

        assertEquals(documentType, result.orElse(null));
        verify(documentTypeRepository, times(1)).findByDocumentUuid(documentUuid);
    }

    @Test
    public void testGetCurrencies() {
        List<CurrencyEntity> currencies = new ArrayList<>();
        // Add some dummy CurrencyEntity objects to the list

        when(currencyRepository.findAll()).thenReturn(currencies);

        List<CurrencyEntity> result = commonsDAO.getCurrencies();

        assertEquals(currencies, result);
        verify(currencyRepository, times(1)).findAll();
    }

    @Test
    public void testGetComments() {
        List<CommentsEntity> comments = new ArrayList<>();
        // Add some dummy CommentsEntity objects to the list

        when(commentsRepository.findAll()).thenReturn(comments);

        List<CommentsEntity> result = commonsDAO.getComments();

        assertEquals(comments, result);
        verify(commentsRepository, times(1)).findAll();
    }

    @Test
    public void testGetCurrencyByUuid() {
        String currencyUuid = "456";
        CurrencyEntity currencyEntity = new CurrencyEntity();
        // Set properties for currencyEntity

        when(currencyRepository.findByCurrencyUuid(currencyUuid)).thenReturn(Optional.of(currencyEntity));

        Optional<CurrencyEntity> result = commonsDAO.getCurrencyByUuid(currencyUuid);

        assertEquals(currencyEntity, result.orElse(null));
        verify(currencyRepository, times(1)).findByCurrencyUuid(currencyUuid);
    }

    @Test
    public void testGetReferenceNumber() {
        String referenceName = "Ref123";
        ReferenceNumberEntity referenceNumberEntity = new ReferenceNumberEntity();
        // Set properties for referenceNumberEntity

        when(referenceNumberRepository.findByReferenceName(referenceName)).thenReturn(Optional.of(referenceNumberEntity));

        Optional<ReferenceNumberEntity> result = commonsDAO.getReferenceNumber(referenceName);

        assertEquals(referenceNumberEntity, result.orElse(null));
        verify(referenceNumberRepository, times(1)).findByReferenceName(referenceName);
    }

    @Test
    public void testSaveReference() {
        ReferenceNumberEntity referenceNumberEntity = new ReferenceNumberEntity();
        // Set properties for referenceNumberEntity

        when(referenceNumberRepository.save(referenceNumberEntity)).thenReturn(referenceNumberEntity);

        ReferenceNumberEntity result = commonsDAO.saveReference(referenceNumberEntity);

        assertEquals(referenceNumberEntity, result);
        verify(referenceNumberRepository, times(1)).save(referenceNumberEntity);
    }
}
