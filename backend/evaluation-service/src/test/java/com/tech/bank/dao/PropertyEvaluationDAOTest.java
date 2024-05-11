package com.tech.bank.dao;

import com.tech.bank.data.entity.*;
import com.tech.bank.data.repository.*;
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

public class PropertyEvaluationDAOTest {

    @Mock
    private PropertyEvaluationRepository propertyEvaluationRepository;

    @Mock
    private FacilityDetailsRepository facilityDetailsRepository;

    @Mock
    private CommentsRepository commentsRepository;

    @Mock
    private BorrowersDetailsRepository borrowersDetailsRepository;

    @Mock
    private DocumentsRepository documentsRepository;

    @InjectMocks
    private PropertyEvaluationDAO propertyEvaluationDAO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSavePropertyEvaluation() {
        PropertyEvaluationEntity propertyEvaluationEntity = new PropertyEvaluationEntity();
        // Set properties for propertyEvaluationEntity

        when(propertyEvaluationRepository.save(propertyEvaluationEntity)).thenReturn(propertyEvaluationEntity);

        PropertyEvaluationEntity result = propertyEvaluationDAO.savePropertyEvaluation(propertyEvaluationEntity);

        assertEquals(propertyEvaluationEntity, result);
        verify(propertyEvaluationRepository, times(1)).save(propertyEvaluationEntity);
    }

    @Test
    public void testSaveFacilityDetails() {
        FacilityDetailsEntity facilityDetailsEntity = new FacilityDetailsEntity();
        // Set properties for facilityDetailsEntity

        when(facilityDetailsRepository.save(facilityDetailsEntity)).thenReturn(facilityDetailsEntity);

        FacilityDetailsEntity result = propertyEvaluationDAO.saveFacilityDetails(facilityDetailsEntity);

        assertEquals(facilityDetailsEntity, result);
        verify(facilityDetailsRepository, times(1)).save(facilityDetailsEntity);
    }

    @Test
    public void testSaveComments() {
        List<CommentsEntity> commentsEntities = new ArrayList<>();
        // Add some dummy CommentsEntity objects to the list

        when(commentsRepository.saveAll(commentsEntities)).thenReturn(commentsEntities);

        List<CommentsEntity> result = propertyEvaluationDAO.saveComments(commentsEntities);

        assertEquals(commentsEntities, result);
        verify(commentsRepository, times(1)).saveAll(commentsEntities);
    }

    @Test
    public void testSaveBorrowerDetails() {
        List<BorrowersDetailsEntity> borrowersDetailsEntities = new ArrayList<>();
        // Add some dummy BorrowersDetailsEntity objects to the list

        when(borrowersDetailsRepository.saveAll(borrowersDetailsEntities)).thenReturn(borrowersDetailsEntities);

        List<BorrowersDetailsEntity> result = propertyEvaluationDAO.saveBorrowerDetails(borrowersDetailsEntities);

        assertEquals(borrowersDetailsEntities, result);
        verify(borrowersDetailsRepository, times(1)).saveAll(borrowersDetailsEntities);
    }

    @Test
    public void testGetAllPropertyEvaluation() {
        List<PropertyEvaluationEntity> propertyEvaluations = new ArrayList<>();
        // Add some dummy PropertyEvaluationEntity objects to the list

        when(propertyEvaluationRepository.findAll()).thenReturn(propertyEvaluations);

        List<PropertyEvaluationEntity> result = propertyEvaluationDAO.getAllPropertyEvaluation();

        assertEquals(propertyEvaluations, result);
        verify(propertyEvaluationRepository, times(1)).findAll();
    }

    @Test
    public void testSaveDocument() {
        DocumentsEntity documentsEntity = new DocumentsEntity();
        // Set properties for documentsEntity

        when(documentsRepository.save(documentsEntity)).thenReturn(documentsEntity);

        DocumentsEntity result = propertyEvaluationDAO.saveDocument(documentsEntity);

        assertEquals(documentsEntity, result);
        verify(documentsRepository, times(1)).save(documentsEntity);
    }

    @Test
    public void testGetDocumentByUuid() {
        String documentsUuid = "123";
        DocumentsEntity documentsEntity = new DocumentsEntity();
        // Set properties for documentsEntity

        when(documentsRepository.findByDocumentsUuid(documentsUuid)).thenReturn(Optional.of(documentsEntity));

        Optional<DocumentsEntity> result = propertyEvaluationDAO.getDocumentByUuid(documentsUuid);

        assertEquals(documentsEntity, result.orElse(null));
        verify(documentsRepository, times(1)).findByDocumentsUuid(documentsUuid);
    }

    @Test
    public void testDeleteDocument() {
        DocumentsEntity documentsEntity = new DocumentsEntity();
        // Set properties for documentsEntity

        doNothing().when(documentsRepository).delete(documentsEntity);

        propertyEvaluationDAO.deleteDocument(documentsEntity);

        verify(documentsRepository, times(1)).delete(documentsEntity);
    }
}
