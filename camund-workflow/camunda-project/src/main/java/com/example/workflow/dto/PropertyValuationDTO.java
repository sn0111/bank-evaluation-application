package com.example.workflow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyValuationDTO{

    private String valuationUuid;
    private EvaluationType evaluationType;
    private String fosRefNumber;
    private boolean isFosRef;
}
