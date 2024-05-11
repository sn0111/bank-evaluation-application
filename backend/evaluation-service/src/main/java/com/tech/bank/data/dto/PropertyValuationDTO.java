package com.tech.bank.data.dto;

import com.tech.bank.data.entity.EvaluationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyValuationDTO extends BaseDTO{

    private String valuationUuid;
    private EvaluationType evaluationType;
    private String fosRefNumber;
    private boolean isFosRef;
}
