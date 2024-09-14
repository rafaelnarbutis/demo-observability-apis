package com.shopcar.application.domain.entities.vo;

public class EnterpriseId {
    private final String value;

    public String getValue() {
        return value;
    }

    private EnterpriseId(String value) { this.value = value; }

    public static EnterpriseId create(String value) {
        if(value == null || value.isBlank()) throw new RuntimeException("invalid value");

        return new EnterpriseId(value);
    }
}
