package com.codecrackers.bankapi.errorHandler;

public class InvalidAddressException extends RuntimeException{
    public InvalidAddressException(String message) {
        super(message);
    }
}
