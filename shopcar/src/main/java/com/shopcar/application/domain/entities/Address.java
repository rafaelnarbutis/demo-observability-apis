package com.shopcar.application.domain.entities;

import com.shopcar.application.domain.entities.vo.AddressId;
import com.shopcar.application.exceptions.ValidationException;

import java.util.Objects;
import java.util.function.Predicate;

public class Address {
    private AddressId id;

    private String country;

    private String city;

    private String street;

    private Integer number;

    private static final Predicate<String> IS_BLANK =  value -> value == null || value.isBlank();
    private static final Predicate<Integer> IS_NEGATIVE = value -> value == null || value < 0;

    private Address(AddressId id, String country, String city, String street, Integer number) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    private Address(String country, String city, String street, Integer number) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public static Address create(String country, String city, String street, Integer number) {
        if(IS_BLANK.test(country)) throw new ValidationException("invalid country");
        else if(IS_BLANK.test(city)) throw new ValidationException("invalid country");
        else if (IS_BLANK.test(street)) throw new ValidationException("invalid street");
        else if (IS_NEGATIVE.test(number)) throw new ValidationException("invalid number");

        return new Address(country, city, street, number);
    }

    public static Address create(AddressId id, String country, String city, String street, Integer number) {
        if(id == null) throw new ValidationException("invalid id");
        else if(IS_BLANK.test(country)) throw new ValidationException("invalid country");
        else if(IS_BLANK.test(city)) throw new ValidationException("invalid country");
        else if (IS_BLANK.test(street)) throw new ValidationException("invalid street");
        else if (IS_NEGATIVE.test(number)) throw new ValidationException("invalid number");

        return new Address(id, country, city, street, number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return (Objects.equals(id, address.id) || address.id == null)
                && Objects.equals(country, address.country)
                && Objects.equals(city, address.city)
                && Objects.equals(street, address.street)
                && Objects.equals(number, address.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, street, number);
    }

    public AddressId getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public Integer getNumber() {
        return number;
    }
}
