package com.tech.bank.data.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BaseDTO {
    private String createdDate;
    private String updatedDate;
    private String createdBy;
    private String updatedBy;
}
