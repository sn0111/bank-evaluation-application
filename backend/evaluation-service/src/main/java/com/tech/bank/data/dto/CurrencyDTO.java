package com.tech.bank.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDTO extends BaseDTO{

    private String currencyUuid;
    private String currencyName;
    private String description;
}
