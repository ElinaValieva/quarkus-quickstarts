package com.quarkus.service.impl;

import com.quarkus.exception.BusinessLogicAuthException;
import com.quarkus.exception.ErrorMessage;
import com.quarkus.model.Credential;
import com.quarkus.security.TokenGenerator;
import com.quarkus.service.AuthenticationService;
import com.quarkus.service.UserService;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Slf4j
@ApplicationScoped
public class AuthenticationServiceImpl implements AuthenticationService {

    @Inject
    UserService userService;

    @Inject
    TokenGenerator tokenGenerator;

    @Override
    public String auth(Credential credential) {
        log.debug("Start authentication: {}", credential.getUsername());

        if (!userService.isAuthorized(credential))
            throw new BusinessLogicAuthException(ErrorMessage.NOT_AUTHORIZED);

        return generateToken(credential);
    }

    private String generateToken(Credential credential) {
        log.debug("Generating token for username: {}", credential.getUsername());

        try {
            return tokenGenerator.generateToken(credential.getUsername());
        } catch (Exception e) {
            log.warn("Failed to generate token: {%s}", e);
            throw new BusinessLogicAuthException(ErrorMessage.NOT_AUTHORIZED);
        }
    }
}
