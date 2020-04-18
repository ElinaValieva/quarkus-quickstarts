package com.quarkus.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessage {

    public static final String USERNAME_NOT_UNIQUE = "User with same username already exist";
    public static final String USER_NOT_FOUND = "User wasn't found";
    public static final String NOT_AUTHORIZED = "User wasn't authorized";
    public static final String POST_DOESNT_EXIST = "Post doesn't exist";
}
