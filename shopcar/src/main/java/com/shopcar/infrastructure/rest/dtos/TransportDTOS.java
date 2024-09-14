package com.shopcar.infrastructure.rest.dtos;

public interface TransportDTOS {

    record TransportPostRequest(String type, Double price, String description){}
}
