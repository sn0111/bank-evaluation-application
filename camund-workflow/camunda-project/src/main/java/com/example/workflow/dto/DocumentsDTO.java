package com.example.workflow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentsDTO {

    private String documentsUuid;
    private String name;
    private String size;
    private DocumentTypeDTO documentType;
    private byte[] file;
    private String url;
}
