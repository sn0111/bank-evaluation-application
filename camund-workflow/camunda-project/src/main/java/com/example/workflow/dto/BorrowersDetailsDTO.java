package com.example.workflow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowersDetailsDTO {

    private String borrowersUuid;
    private String customerNumber;
    private String customerName;
    private String address;
    private String contactNumber;
    private String email;
}
