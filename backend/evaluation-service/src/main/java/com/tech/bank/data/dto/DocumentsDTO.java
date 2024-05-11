package com.tech.bank.data.dto;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentsDTO extends BaseDTO {

    private String documentsUuid;
    private String name;
    private String size;
    private DocumentTypeDTO documentType;
    @Lob
    private byte[] file;
    private String url;
}
