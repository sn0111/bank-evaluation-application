package com.tech.bank.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomersDTO extends BaseDTO{

    private String customerUuid;
    private String customerNumber;
    private String shortName;
    private String isIndividual;
    private String nationality;
    private String nationalityNumber;
    private String nationalityDescription;
    private String streetAddress;
    private String addressLine2;
    private String address_line3;
    private String townCountry;
    private String postCode;
    private String country;
    private String countryCode;
    private String countryCodeNumber;
    private String dispatchCode;
    private String communicationChannel;
    private String phoneNumber;
    private String officePhoneNumber;
    private String faxNumber;
    private String mobileOperatorIso;
    private String mobileOperatorCode;
    private String smsNumber;
    private String email;

}
