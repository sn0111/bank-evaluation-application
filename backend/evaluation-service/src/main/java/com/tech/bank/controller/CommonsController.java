package com.tech.bank.controller;

import com.tech.bank.data.dto.CurrencyDTO;
import com.tech.bank.data.dto.DocumentTypeDTO;
import com.tech.bank.data.dto.ReferenceNumberDTO;
import com.tech.bank.data.dto.ResponseDTO;
import com.tech.bank.servie.CommonsService;
import com.tech.bank.util.BankEvaluationException;
import com.tech.bank.util.ResponseBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bank")
@CrossOrigin("*")
public class CommonsController {

    private final CommonsService commonsService;

    public CommonsController(CommonsService commonsService) {
        this.commonsService = commonsService;
    }

    @GetMapping("/commons/currencies")
    public ResponseEntity<ResponseDTO> getCurrencies(){
        List<CurrencyDTO> currencies = commonsService.getCurrencies();
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(currencies));
    }

    @GetMapping("/commons/document-types")
    public ResponseEntity<ResponseDTO> getDocumentTypes(){
        List<DocumentTypeDTO> result = commonsService.getDocumentTypes();
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(result));
    }

    @GetMapping("/commons/reference")
    public ResponseEntity<ResponseDTO> getReference() throws BankEvaluationException {
        ReferenceNumberDTO reference = commonsService.getReference();
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(reference));
    }
}
