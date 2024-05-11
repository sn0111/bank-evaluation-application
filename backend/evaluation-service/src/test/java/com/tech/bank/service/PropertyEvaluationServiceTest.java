package com.tech.bank.service;

import com.tech.bank.dao.CommonsDAO;
import com.tech.bank.dao.PropertyEvaluationDAO;
import com.tech.bank.data.dto.*;
import com.tech.bank.data.entity.*;
import com.tech.bank.servie.PropertyEvaluationService;
import com.tech.bank.util.BankEvaluationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
@DirtiesContext
@AutoConfigureMockMvc(addFilters = false)
public class PropertyEvaluationServiceTest {

    @Mock
    private PropertyEvaluationDAO propertyEvaluationDAO;

    @Mock
    private CommonsDAO commonsDAO;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PropertyEvaluationService propertyEvaluationService;

    @BeforeEach
    public void setup() {
    }


    public PropertyEvaluationDTO createDTO() {

            // Create CurrencyDTO
            CurrencyDTO currencyDTO = new CurrencyDTO();
            currencyDTO.setCurrencyUuid("CUR-123");
            currencyDTO.setCurrencyName("USD");
            currencyDTO.setDescription("United States Dollar");

            // Create DocumentTypeDTO
            DocumentTypeDTO documentTypeDTO = new DocumentTypeDTO();
            documentTypeDTO.setDocumentUuid("DOC-456");
            documentTypeDTO.setDocumentName("Passport");
            documentTypeDTO.setDescription("Travel document");

            // Create ReferenceNumberDTO
            ReferenceNumberDTO referenceNumberDTO = new ReferenceNumberDTO();
            referenceNumberDTO.setReferenceName("REF-789");
            referenceNumberDTO.setReferenceNumber(12345L);

            // Create FacilityDetailsDTO
            FacilityDetailsDTO facilityDetailsDTO = new FacilityDetailsDTO();
            facilityDetailsDTO.setFacilityDetailUuid("FAC-123");
            facilityDetailsDTO.setFacilityType(FacilityType.Revolving);
            facilityDetailsDTO.setCategory(Category.Apartment);
            facilityDetailsDTO.setValuationType(ValuationType.Construction);
            facilityDetailsDTO.setTerm(10);
            facilityDetailsDTO.setCurrency(currencyDTO);
            facilityDetailsDTO.setAmount(100000);
            facilityDetailsDTO.setHousingLoan(true);

            // Create BorrowersDetailsDTO
            BorrowersDetailsDTO borrowersDetailsDTO = new BorrowersDetailsDTO();
            borrowersDetailsDTO.setBorrowersUuid("BORR-456");
            borrowersDetailsDTO.setCustomerNumber("CUST-123");
            borrowersDetailsDTO.setCustomerName("John Doe");
            borrowersDetailsDTO.setAddress("123 Main St");
            borrowersDetailsDTO.setContactNumber("1234567890");
            borrowersDetailsDTO.setEmail("john@example.com");

            // Create CommentsDTO list
            List<CommentsDTO> commentsDTOList = new ArrayList<>();
            CommentsDTO commentsDTO1 = new CommentsDTO();
            commentsDTO1.setCommentsUuid("COM-1");
            commentsDTO1.setDescription("Comment 1");
            CommentsDTO commentsDTO2 = new CommentsDTO();
            commentsDTO2.setCommentsUuid("COM-2");
            commentsDTO2.setDescription("Comment 2");
            commentsDTOList.add(commentsDTO1);
            commentsDTOList.add(commentsDTO2);

            // Create DocumentsDTO list
            List<DocumentsDTO> documentsDTOList = new ArrayList<>();
            DocumentsDTO documentsDTO1 = new DocumentsDTO();
            documentsDTO1.setDocumentsUuid("DOC-1");
            documentsDTO1.setName("Document 1");
            documentsDTO1.setSize("10 KB");
            documentsDTO1.setDocumentType(documentTypeDTO);
            DocumentsDTO documentsDTO2 = new DocumentsDTO();
            documentsDTO2.setDocumentsUuid("DOC-2");
            documentsDTO2.setName("Document 2");
            documentsDTO2.setSize("20 KB");
            documentsDTO2.setDocumentType(documentTypeDTO);
            documentsDTOList.add(documentsDTO1);
            documentsDTOList.add(documentsDTO2);

            // Create PropertyEvaluationDTO
            PropertyEvaluationDTO propertyEvaluationDTO = new PropertyEvaluationDTO();
            propertyEvaluationDTO.setPropertyEvaluationUuid("PROP-123");
            propertyEvaluationDTO.setInitiatorName("Initiator");
            propertyEvaluationDTO.setInitiatorBusinessUnit("Business Unit");
            propertyEvaluationDTO.setInitiatorContactNumber("1234567890");
            propertyEvaluationDTO.setReference("REF-123");
            propertyEvaluationDTO.setFacilityDetails(facilityDetailsDTO);
            propertyEvaluationDTO.setEvaluationType(EvaluationType.Existing);
            propertyEvaluationDTO.setFosRefNumber("FOS-123");
            propertyEvaluationDTO.setFosRef(true);
            propertyEvaluationDTO.setBorrowersDetails(List.of(borrowersDetailsDTO));
            propertyEvaluationDTO.setComments(commentsDTOList);
            propertyEvaluationDTO.setDocuments(documentsDTOList);
        return propertyEvaluationDTO;
    }
    public PropertyEvaluationEntity createEntity(){
        // Create FacilityDetailsEntity
        FacilityDetailsEntity facilityDetailsEntity = new FacilityDetailsEntity();
        facilityDetailsEntity.setFacilityDetailId(1L); // Assuming the ID is 1
        facilityDetailsEntity.setFacilityDetailUuid("facilityDetailUuid");
        facilityDetailsEntity.setFacilityType(FacilityType.Revolving); // Assuming FacilityType is an enum
        facilityDetailsEntity.setCategory(Category.Apartment); // Assuming Category is an enum
        facilityDetailsEntity.setValuationType(ValuationType.Inheritance); // Assuming ValuationType is an enum
        facilityDetailsEntity.setTerm(10);
        CurrencyEntity currency = new CurrencyEntity(1L,"","","");
        facilityDetailsEntity.setCurrency(currency); // Assuming currencyEntity is instantiated
        facilityDetailsEntity.setAmount(100000);
        facilityDetailsEntity.setHousingLoan(true);

// Create BorrowersDetailsEntity
        BorrowersDetailsEntity borrowersDetailsEntity = new BorrowersDetailsEntity();
        borrowersDetailsEntity.setBorrowersId(1L); // Assuming the ID is 1
        borrowersDetailsEntity.setBorrowersUuid("borrowersUuid");
        borrowersDetailsEntity.setCustomerNumber("customerNumber");
        borrowersDetailsEntity.setCustomerName("customerName");
        borrowersDetailsEntity.setAddress("address");
        borrowersDetailsEntity.setContactNumber("contactNumber");
        borrowersDetailsEntity.setEmail("email");

// Create CommentsEntity
        CommentsEntity commentsEntity = new CommentsEntity();
        commentsEntity.setCommentsId(1L); // Assuming the ID is 1
        commentsEntity.setCommentsUuid("commentsUuid");
        commentsEntity.setDescription("description");

// Create DocumentsEntity
        DocumentsEntity documentsEntity = new DocumentsEntity();
        documentsEntity.setDocumentsId(1L); // Assuming the ID is 1
        documentsEntity.setDocumentsUuid("documentsUuid");
        documentsEntity.setName("name");
        documentsEntity.setSize("size");
        DocumentTypeEntity documentType = new DocumentTypeEntity(1L, "", "", "");
        documentsEntity.setDocumentType(documentType); // Assuming documentTypeEntity is instantiated
        documentsEntity.setFile(new byte[]{/* file bytes here */}); // Assuming file bytes are provided

// Create PropertyEvaluationEntity
        PropertyEvaluationEntity propertyEvaluationEntity = new PropertyEvaluationEntity();
        propertyEvaluationEntity.setPropertyEvaluationId(1L); // Assuming the ID is 1
        propertyEvaluationEntity.setPropertyEvaluationUuid("propertyEvaluationUuid");
        propertyEvaluationEntity.setInitiatorName("initiatorName");
        propertyEvaluationEntity.setInitiatorBusinessUnit("initiatorBusinessUnit");
        propertyEvaluationEntity.setInitiatorContactNumber("initiatorContactNumber");
        propertyEvaluationEntity.setReference("reference");
        propertyEvaluationEntity.setFacilityDetails(facilityDetailsEntity);
        propertyEvaluationEntity.setEvaluationType(EvaluationType.New); // Assuming EvaluationType is an enum
        propertyEvaluationEntity.setFosRefNumber("fosRefNumber");
        propertyEvaluationEntity.setFosRef(true);
        propertyEvaluationEntity.setBorrowersDetails(List.of(borrowersDetailsEntity));
        propertyEvaluationEntity.setComments(List.of(commentsEntity));
        propertyEvaluationEntity.setDocuments(List.of(documentsEntity));
        return propertyEvaluationEntity;
    }
    @Test
    public void testSavePropertyEvaluation() throws BankEvaluationException {
        PropertyEvaluationDTO dto = createDTO();
        PropertyEvaluationEntity entity = createEntity();
        // Set up test data
        when(commonsDAO.getCurrencyByUuid(anyString())).thenReturn(java.util.Optional.of(new CurrencyEntity()));
        when(propertyEvaluationDAO.saveFacilityDetails(any())).thenReturn(new FacilityDetailsEntity());
        when(commonsDAO.getReferenceNumber(anyString())).thenReturn(java.util.Optional.of(new ReferenceNumberEntity(1L,"",13L)));
        when(propertyEvaluationDAO.savePropertyEvaluation(any())).thenReturn(new PropertyEvaluationEntity());
        when(propertyEvaluationDAO.saveComments(any())).thenReturn(new ArrayList<>());
        when(propertyEvaluationDAO.saveBorrowerDetails(any())).thenReturn(new ArrayList<>());
        when(modelMapper.map(dto.getFacilityDetails(), FacilityDetailsEntity.class)).thenReturn(entity.getFacilityDetails());
        when(modelMapper.map(entity.getComments().get(0), CommentsDTO.class)).thenReturn(dto.getComments().get(0));
        when(modelMapper.map(entity.getBorrowersDetails().get(0), BorrowersDetailsDTO.class)).thenReturn(dto.getBorrowersDetails().get(0));
        when(modelMapper.map(dto, PropertyEvaluationEntity.class)).thenReturn(entity);
        when(modelMapper.map(any(), eq(PropertyEvaluationDTO.class))).thenReturn(dto);
        when((propertyEvaluationDAO.getDocumentByUuid(anyString()))).thenReturn(Optional.of(new DocumentsEntity()));
        // Call the method to be tested
        PropertyEvaluationDTO result = propertyEvaluationService.savePropertyEvaluation(dto);

        // Assertions
        assertNotNull(result);
        // Add more assertions as needed
    }

    // Test getPropertyEvaluations method
    @Test
    public void testGetPropertyEvaluations() {
        // Set up mock behavior
        List<PropertyEvaluationEntity> entityList = new ArrayList<>();
        // Add some dummy PropertyEvaluationEntity objects to the list

        when(propertyEvaluationDAO.getAllPropertyEvaluation()).thenReturn(entityList);
        when(modelMapper.map(any(), eq(PropertyEvaluationDTO.class))).thenReturn(new PropertyEvaluationDTO());

        // Call the method to be tested
        List<PropertyEvaluationDTO> result = propertyEvaluationService.getPropertyEvaluations();

        // Assertions
        assertNotNull(result);
        // Add more assertions as needed
    }

    // Test saveDocument method
    @Test
    public void testSaveDocument() throws BankEvaluationException, IOException {
        // Set up mock behavior
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Hello, World!".getBytes());
        DocumentsDTO documentsDTO = new DocumentsDTO();
        // Set properties for documentsDTO

        when(commonsDAO.getDocumentByUuid(anyString())).thenReturn(java.util.Optional.of(new DocumentTypeEntity()));
        when(propertyEvaluationDAO.saveDocument(any())).thenReturn(new DocumentsEntity());
        when(modelMapper.map(any(), eq(DocumentsDTO.class))).thenReturn(documentsDTO);

        // Call the method to be tested
        DocumentsDTO result = propertyEvaluationService.saveDocument(file, "documentTypeUuid");

        // Assertions
        assertNotNull(result);
        // Add more assertions as needed
    }

    // Test getDocument method
    @Test
    public void testGetDocument() throws BankEvaluationException {
        // Set up mock behavior
        DocumentsEntity documentsEntity = new DocumentsEntity();
        // Set properties for documentsEntity

        when(propertyEvaluationDAO.getDocumentByUuid(anyString())).thenReturn(java.util.Optional.of(documentsEntity));
        when(modelMapper.map(any(), eq(DocumentsDTO.class))).thenReturn(new DocumentsDTO());

        // Call the method to be tested
        DocumentsDTO result = propertyEvaluationService.getDocument("documentsUuid");

        // Assertions
        assertNotNull(result);
        // Add more assertions as needed
    }

    // Test deleteDocument method
    @Test
    public void testDeleteDocument() throws BankEvaluationException {
        // Set up mock behavior
        DocumentsEntity documentsEntity = new DocumentsEntity();
        // Set properties for documentsEntity

        when(propertyEvaluationDAO.getDocumentByUuid(anyString())).thenReturn(java.util.Optional.of(documentsEntity));

        // Call the method to be tested
        String result = propertyEvaluationService.deleteDocument("documentsUuid");

        // Assertions
        assertEquals("Document deleted successfully", result);
        // Add more assertions as needed
    }

    // Test getComments method
    @Test
    public void testGetComments() {
        // Set up mock behavior
        List<CommentsEntity> entityList = new ArrayList<>();
        // Add some dummy CommentsEntity objects to the list

        when(commonsDAO.getComments()).thenReturn(entityList);
        when(modelMapper.map(any(), eq(CommentsDTO.class))).thenReturn(new CommentsDTO());

        // Call the method to be tested
        List<CommentsDTO> result = propertyEvaluationService.getComments();

        // Assertions
        assertNotNull(result);
        // Add more assertions as needed
    }
}
