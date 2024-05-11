package com.example.workflow.dto;

import lombok.Getter;

@Getter
public enum FacilityType {
    Revolving("Revolving"),
    NonRevolving("Non-revolving");

    private final String displayName;

    FacilityType(String displayName) {
        this.displayName = displayName;
    }

}
