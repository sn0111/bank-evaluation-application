package com.example.workflow.dto;

import lombok.Getter;

@Getter
public enum ValuationType {
    Reparation("1 - Reparation"),
    Inheritance("2 - Inheritance"),
    Construction("3 - Construction");

    private final String displayName;

    ValuationType(String displayName) {
        this.displayName = displayName;
    }

}
