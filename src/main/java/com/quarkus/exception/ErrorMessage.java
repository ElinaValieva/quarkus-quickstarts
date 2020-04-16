package com.quarkus.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessage {

    public static final String USERNAME_NOT_UNIQUE = "User with same username already exist";
}
