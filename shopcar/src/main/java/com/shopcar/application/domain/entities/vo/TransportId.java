package com.shopcar.application.domain.entities.vo;


import java.util.Objects;

public class TransportId {
    private final String value;

    private TransportId(String value){ this.value = value; }

    public static TransportId create(String value) {
        if(value == null || value.isBlank()) throw new RuntimeException("invalid value");

        return new TransportId(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransportId that = (TransportId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String getValue() {
        return value;
    }
}
