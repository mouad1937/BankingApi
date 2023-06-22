package com.codecrackers.bankapi.errorHandler;

public class DuplicateAddressException extends RuntimeException{
    public DuplicateAddressException(String message) {
        super(message);
    }
}
