package com.example.workflow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FacilityDetailsDTO {

    private String facilityDetailUuid;
    private FacilityType facilityType;
    private Category category;
    private ValuationType valuationType;
    private int term;
    private CurrencyDTO currency;
    private int amount;
    private boolean isHousingLoan;
}
