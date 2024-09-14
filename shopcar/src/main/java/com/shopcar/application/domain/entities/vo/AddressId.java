package com.shopcar.application.domain.entities.vo;

import java.util.Objects;

public class AddressId {
    private final String value;

    private AddressId(String value) { this.value = value; }

    public static AddressId create(String value) {
        if(value == null || value.isBlank()) throw new RuntimeException("invalid value");

        return new AddressId(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressId addressId = (AddressId) o;
        return Objects.equals(value, addressId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String getValue() {
        return value;
    }
}
