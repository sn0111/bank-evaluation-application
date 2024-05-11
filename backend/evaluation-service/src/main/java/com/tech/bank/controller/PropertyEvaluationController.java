package com.tech.bank.controller;

import com.tech.bank.data.dto.CommentsDTO;
import com.tech.bank.data.dto.DocumentsDTO;
import com.tech.bank.data.dto.PropertyEvaluationDTO;
import com.tech.bank.data.dto.ResponseDTO;
import com.tech.bank.servie.PropertyEvaluationService;
import com.tech.bank.util.BankEvaluationException;
import com.tech.bank.util.ResponseBuilder;
import jakarta.validation.Valid;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/bank")
@CrossOrigin("*")
public class PropertyEvaluationController {

    private final PropertyEvaluationService service;

    public PropertyEvaluationController(PropertyEvaluationService service) {
        this.service = service;
    }

    @GetMapping("/evaluations")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<ResponseDTO> getPropertyEvaluations(){
        List<PropertyEvaluationDTO> result = service.getPropertyEvaluations();
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(result));
    }

    @PostMapping("/evaluation")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<ResponseDTO> savePropertyEvaluation(@Valid @RequestBody PropertyEvaluationDTO dto) throws BankEvaluationException {
        PropertyEvaluationDTO result = service.savePropertyEvaluation(dto);
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(result));
    }

    @PostMapping("/evaluation/document/{documentTypeUuid}")
    public ResponseEntity<ResponseDTO> saveDocument(@RequestParam("file") MultipartFile file, @PathVariable(name = "documentTypeUuid") String documentTypeUuid) throws IOException, BankEvaluationException {
        DocumentsDTO result = service.saveDocument(file, documentTypeUuid);
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(result));
    }

    @GetMapping("/evaluation/document/{documentsUuid}")
    public ResponseEntity<Resource> getDocument(@PathVariable(name = "documentsUuid") String documentsUuid) throws BankEvaluationException {
        DocumentsDTO document = service.getDocument(documentsUuid);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getName() + "\"")
                .body(new InputStreamResource(new ByteArrayInputStream(document.getFile())));
    }

    @DeleteMapping("/evaluation/document/{documentsUuid}")
    public ResponseEntity<ResponseDTO> deleteDocument(@PathVariable(name = "documentsUuid") String documentsUuid) throws BankEvaluationException {
        String result = service.deleteDocument(documentsUuid);
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(result));

    }

    @GetMapping("/evaluation/comments")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<ResponseDTO> getComments(){
        List<CommentsDTO> result = service.getComments();
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(result));
    }

}
