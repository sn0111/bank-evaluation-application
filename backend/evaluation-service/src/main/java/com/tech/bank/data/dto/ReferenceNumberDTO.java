package com.tech.bank.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReferenceNumberDTO extends BaseDTO{

    private String referenceName;
    private Long referenceNumber;
}
