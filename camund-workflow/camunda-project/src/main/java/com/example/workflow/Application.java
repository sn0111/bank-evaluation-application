package com.example.workflow;

import com.example.workflow.dto.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.Base64;
import java.util.Map;

@SpringBootApplication
public class Application {

  public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  @ExternalTaskSubscription(
          topicName = "approval-status"
  )
  public ExternalTaskHandler approvalStatus() {
    return (externalTask, externalTaskService) -> {
      System.out.println("Manager approved evaluation request.");
      externalTaskService.complete(externalTask);
    };
  }

  @Bean
  @ExternalTaskSubscription(
          topicName = "property-requests"
  )
  public ExternalTaskHandler externalTaskHandler() {
    return (externalTask, externalTaskService) -> {
      Map<String, Object> variables = externalTask.getAllVariables();
      variables.put("userEmail", "satya@gmail.com");
      variables.put("password", "satya");
      System.out.println(" Doing some business logic ");
      String userEmail = (String) variables.get("userEmail");
      String password = (String) variables.get("password");
      String basicAuth = Base64.getEncoder().encodeToString((userEmail + ":" + password).getBytes());
      System.out.println(basicAuth);
      ResponseDTO result = RestClient.create().get()
              .uri("http://localhost:8081/api/bank/evaluations")
              .header("Authorization", "Basic " + basicAuth)
              .retrieve()
              .body(ResponseDTO.class);
      assert result != null;
      ObjectMapper mapper = new ObjectMapper();
      try {
        mapper.writeValueAsString(result.getData());
        variables.put("requests", result.getData());
        externalTaskService.setVariables(externalTask, variables);
      } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
      }


//      execution.setVariable("requestList",JSON.stringfy(execution.getVariable("requests")));

      externalTaskService.complete(externalTask);
    };
  }
}