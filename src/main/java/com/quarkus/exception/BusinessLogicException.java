package com.quarkus.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BusinessLogicException extends RuntimeException {

    private final String message;
}
