package com.tech.bank.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentTypeDTO extends BaseDTO{

    private String documentUuid;
    private String documentName;
    private String description;
}
