package com.shopcar.application.exceptions;

public class ValidationException extends RuntimeException {
    private String message;

    public ValidationException(){}

    public ValidationException(String message) {
        super(message);
        this.message = message;
    }
}
