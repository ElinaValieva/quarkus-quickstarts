package com.quarkus.exception;

public class BusinessLogicAuthException extends BusinessLogicException {

    public BusinessLogicAuthException(String message) {
        super(message);
    }
}
