package com.tech.bank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.bank.data.dto.*;
import com.tech.bank.data.entity.EvaluationType;
import com.tech.bank.servie.PropertyEvaluationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
@DirtiesContext
@AutoConfigureMockMvc(addFilters = false)
public class PropertyEvaluationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PropertyEvaluationService propertyEvaluationService;

    @InjectMocks
    private PropertyEvaluationController propertyEvaluationController;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testGetPropertyEvaluations() throws Exception {
        List<PropertyEvaluationDTO> evaluations = Arrays.asList(
                new PropertyEvaluationDTO(),
                new PropertyEvaluationDTO()
        );

        when(propertyEvaluationService.getPropertyEvaluations()).thenReturn(evaluations);

        mockMvc.perform(get("/api/bank/evaluations"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(evaluations.size()));

        verify(propertyEvaluationService, times(1)).getPropertyEvaluations();
        verifyNoMoreInteractions(propertyEvaluationService);
    }

    @Test
    public void testSavePropertyEvaluation() throws Exception {
        PropertyEvaluationDTO evaluationDTO = new PropertyEvaluationDTO();
        evaluationDTO.setPropertyEvaluationUuid("123456");
        evaluationDTO.setInitiatorName("John Doe");
        evaluationDTO.setInitiatorBusinessUnit("Business Unit ABC");
        evaluationDTO.setInitiatorContactNumber("123-456-7890");
        evaluationDTO.setReference("REF123");
        evaluationDTO.setEvaluationType(EvaluationType.New);
        evaluationDTO.setFosRefNumber("FOS123");
        evaluationDTO.setFosRef(true);

        FacilityDetailsDTO facilityDetailsDTO = new FacilityDetailsDTO();
        evaluationDTO.setFacilityDetails(facilityDetailsDTO);
        List<BorrowersDetailsDTO> borrowersDetailsList = new ArrayList<>();
        BorrowersDetailsDTO borrower1 = new BorrowersDetailsDTO();
        borrowersDetailsList.add(borrower1);
        evaluationDTO.setBorrowersDetails(borrowersDetailsList);
        List<CommentsDTO> commentsList = new ArrayList<>();
        CommentsDTO comment1 = new CommentsDTO();
        commentsList.add(comment1);
        evaluationDTO.setComments(commentsList);
        List<DocumentsDTO> documentsList = new ArrayList<>();
        DocumentsDTO document1 = new DocumentsDTO();
        documentsList.add(document1);
        evaluationDTO.setDocuments(documentsList);

        when(propertyEvaluationService.savePropertyEvaluation(any())).thenReturn(evaluationDTO);

        mockMvc.perform(post("/api/bank/evaluation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(evaluationDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(propertyEvaluationService, times(1)).savePropertyEvaluation(any());
        verifyNoMoreInteractions(propertyEvaluationService);
    }

    @Test
    public void testSaveDocument() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "filename.txt", MediaType.TEXT_PLAIN_VALUE, "some text".getBytes());
        DocumentsDTO document = new DocumentsDTO(/* construct DTO object with necessary values */);

        when(propertyEvaluationService.saveDocument(any(), anyString())).thenReturn(document);

        mockMvc.perform(multipart("/api/bank/evaluation/document/{documentTypeUuid}", "docType123")
                        .file(file))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(propertyEvaluationService, times(1)).saveDocument(any(), anyString());
        verifyNoMoreInteractions(propertyEvaluationService);
    }

//    @Test
//    public void testGetDocument() throws Exception {
//        DocumentsDTO document = new DocumentsDTO(/* construct DTO object with necessary values */);
//
//        when(propertyEvaluationService.getDocument(anyString())).thenReturn(document);
//
//        mockMvc.perform(get("/api/bank/evaluation/document/{documentsUuid}", "docUuid123"))
//                .andExpect(status().isOk());
//
//        verify(propertyEvaluationService, times(1)).getDocument(anyString());
//        verifyNoMoreInteractions(propertyEvaluationService);
//    }

    @Test
    public void testDeleteDocument() throws Exception {
        String result = "Document deleted successfully";

        when(propertyEvaluationService.deleteDocument(anyString())).thenReturn(result);

        mockMvc.perform(delete("/api/bank/evaluation/document/{documentsUuid}", "docUuid123"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(propertyEvaluationService, times(1)).deleteDocument(anyString());
        verifyNoMoreInteractions(propertyEvaluationService);
    }

    @Test
    public void testGetComments() throws Exception {
        List<CommentsDTO> comments = Arrays.asList(
                new CommentsDTO(),
                new CommentsDTO()
        );

        when(propertyEvaluationService.getComments()).thenReturn(comments);

        mockMvc.perform(get("/api/bank/evaluation/comments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(comments.size()));

        verify(propertyEvaluationService, times(1)).getComments();
        verifyNoMoreInteractions(propertyEvaluationService);
    }
}
