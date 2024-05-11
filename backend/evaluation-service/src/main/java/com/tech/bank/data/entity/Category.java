package com.tech.bank.data.entity;

import lombok.Getter;

@Getter
public enum Category {
    Apartment("25000 - Apartment"),
    Housing("25010 – PBWM Housing");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

}
