package com.tech.bank.data.dto;

import com.tech.bank.data.entity.Category;
import com.tech.bank.data.entity.FacilityType;
import com.tech.bank.data.entity.ValuationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FacilityDetailsDTO extends BaseDTO {

    private String facilityDetailUuid;
    private FacilityType facilityType;
    private Category category;
    private ValuationType valuationType;
    private int term;
    private CurrencyDTO currency;
    private int amount;
    private boolean isHousingLoan;
}
