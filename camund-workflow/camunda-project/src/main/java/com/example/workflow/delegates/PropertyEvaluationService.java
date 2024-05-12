package com.example.workflow.delegates;

import camundajar.impl.scala.Int;
import com.example.workflow.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.connect.httpclient.HttpConnector;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class PropertyEvaluationService implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> variables = delegateExecution.getVariables();
        PropertyEvaluationDTO dto = new PropertyEvaluationDTO();
        CommentsDTO commentsDTO = new CommentsDTO();
        commentsDTO.setDescription((String) variables.get("description"));
        dto.setComments(List.of(commentsDTO));
        BorrowersDetailsDTO borrowersDetailsDTO = new BorrowersDetailsDTO();
        borrowersDetailsDTO.setAddress((String) variables.get("address"));
        borrowersDetailsDTO.setEmail((String) variables.get("email"));
        borrowersDetailsDTO.setCustomerName((String) variables.get("customerName"));
        borrowersDetailsDTO.setContactNumber((String) variables.get("contactNumber"));
        borrowersDetailsDTO.setCustomerNumber((String) variables.get("customerNumber"));
        BorrowersDetailsDTO borrowersDetailsDTO1 = new BorrowersDetailsDTO();
        borrowersDetailsDTO1.setAddress((String) variables.get("address1"));
        borrowersDetailsDTO1.setEmail((String) variables.get("email1"));
        borrowersDetailsDTO1.setCustomerName((String) variables.get("customerName1"));
        borrowersDetailsDTO1.setContactNumber((String) variables.get("contactNumber1"));
        borrowersDetailsDTO1.setCustomerNumber((String) variables.get("customerNumber1"));
        dto.setBorrowersDetails(List.of(borrowersDetailsDTO, borrowersDetailsDTO1));
        FacilityDetailsDTO facilityDetailsDTO = new FacilityDetailsDTO();
        facilityDetailsDTO.setAmount(Integer.parseInt((String) variables.get("amount")));
        facilityDetailsDTO.setFacilityType(FacilityType.valueOf((String) variables.get("facilityType")));
        facilityDetailsDTO.setCategory(Category.valueOf((String) variables.get("category")));
        facilityDetailsDTO.setTerm((Integer) variables.get("term"));
        facilityDetailsDTO.setHousingLoan((Boolean) variables.get("housingLoan"));
        CurrencyDTO currencyDTO = new CurrencyDTO();
        currencyDTO.setCurrencyUuid((String) variables.get("currencyUuid"));
        facilityDetailsDTO.setCurrency(currencyDTO);
        facilityDetailsDTO.setValuationType(ValuationType.valueOf((String) variables.get("valuationType")));
        dto.setFacilityDetails(facilityDetailsDTO);
        dto.setFosRef(true);
        dto.setFosRefNumber((String) variables.get("fosRefNumber"));
        dto.setEvaluationType(EvaluationType.valueOf((String) variables.get("evaluationType")));
        dto.setInitiatorName((String) variables.get("initiatorName"));
        dto.setInitiatorBusinessUnit((String) variables.get("initiatorBusinessUnit"));
        dto.setInitiatorContactNumber((String) variables.get("initiatorContactNumber"));
        dto.setDocuments(List.of());
        delegateExecution.setVariable("dto",dto);


        String userEmail = (String) variables.get("userEmail");
        String password = (String) variables.get("password");
        String basicAuth = Base64.getEncoder().encodeToString((userEmail + ":" + password).getBytes());
        System.out.println(basicAuth);
        Object result = RestClient.create().post()
                .uri("http://localhost:8081/api/bank/evaluation")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization","Basic "+basicAuth)
                .body(dto)
                .retrieve()
                .body(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(result);
        System.out.println(payload);
    }
}
