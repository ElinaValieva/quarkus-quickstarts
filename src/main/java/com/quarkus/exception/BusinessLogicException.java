package com.quarkus.exception;

import java.io.Serializable;

public class BusinessLogicException extends RuntimeException implements Serializable {

    public BusinessLogicException(String message) {
        super(message);
    }
}
