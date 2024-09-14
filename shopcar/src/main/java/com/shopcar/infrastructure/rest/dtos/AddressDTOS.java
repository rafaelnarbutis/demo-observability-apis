package com.shopcar.infrastructure.rest.dtos;

public interface AddressDTOS {

    record AddressPostRequest(String country, String city, String street, Integer number){}
}
