package com.tech.bank.controller;

import com.tech.bank.data.dto.CustomersDTO;
import com.tech.bank.data.dto.ResponseDTO;
import com.tech.bank.servie.CustomerService;
import com.tech.bank.util.ResponseBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank")
@CrossOrigin("*")
public class CustomersController {

    private final CustomerService service;

    public CustomersController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/customers/{customerNumber}")
    public ResponseEntity<ResponseDTO> findCustomers(@PathVariable(name = "customerNumber") String customerNumber){
        List<CustomersDTO> customers = service.findCustomers(customerNumber);
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(customers));
    }

}
