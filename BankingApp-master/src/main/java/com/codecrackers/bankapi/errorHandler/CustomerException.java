package com.codecrackers.bankapi.errorHandler;

import org.springframework.http.HttpStatus;

public class CustomerException extends RuntimeException{

    private HttpStatus status;

    public CustomerException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
