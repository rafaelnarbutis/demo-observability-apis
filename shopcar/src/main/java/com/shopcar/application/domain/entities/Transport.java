package com.shopcar.application.domain.entities;

import com.shopcar.application.domain.entities.vo.TransportId;
import com.shopcar.application.domain.entities.vo.TransportType;
import com.shopcar.application.exceptions.ValidationException;

import java.util.Objects;
import java.util.function.Predicate;

public class Transport {
    private TransportId id;

    private TransportType type;

    private Double price;

    private String description;

    private static final Predicate<Double> IS_NEGATIVE = value -> value == null || value < 0;

    private Transport(TransportId id, TransportType type, Double price, String description) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.description = description;
    }

    private Transport(TransportType type, Double price, String description) {
        this.type = type;
        this.price = price;
        this.description = description;
    }

    public static Transport create(TransportType type, Double price, String description) {
        if(type == null) throw new ValidationException("invalid type");
        else if (IS_NEGATIVE.test(price)) throw new ValidationException("invalid price");

        return new Transport(type, price, description);
    }

    public static Transport create(TransportId id, TransportType type, Double price, String description) {
        if(id == null) throw new ValidationException("invalid id");
        else if(type == null) throw new ValidationException("invalid type");
        else if (IS_NEGATIVE.test(price)) throw new ValidationException("invalid price");

        return new Transport(id, type, price, description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transport transport = (Transport) o;
        return Objects.equals(id, transport.id) && type == transport.type && Objects.equals(price, transport.price) && Objects.equals(description, transport.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, price, description);
    }

    public TransportId getId() {
        return id;
    }

    public TransportType getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
