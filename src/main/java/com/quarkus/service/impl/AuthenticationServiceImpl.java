package com.quarkus.service.impl;

import com.quarkus.exception.BusinessLogicAuthException;
import com.quarkus.exception.ErrorMessage;
import com.quarkus.model.Credential;
import com.quarkus.security.TokenGenerator;
import com.quarkus.service.AuthenticationService;
import com.quarkus.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AuthenticationServiceImpl implements AuthenticationService {

    @Inject
    UserService userService;

    @Inject
    TokenGenerator tokenGenerator;

    @Override
    public String auth(Credential credential) {
        String token = null;
        if (userService.isAuthorized(credential)) {
            try {
                token = tokenGenerator.generateToken(credential.getUsername());
            } catch (Exception e) {
                throw new BusinessLogicAuthException(ErrorMessage.NOT_AUTHORIZED);
            }
        }

        return token;
    }
}
