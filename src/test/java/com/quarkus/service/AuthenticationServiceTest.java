package com.quarkus.service;

import com.quarkus.exception.BusinessLogicAuthException;
import com.quarkus.model.Credential;
import com.quarkus.security.TokenGenerator;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.mockito.Mockito.when;

@QuarkusTest
public class AuthenticationServiceTest {

    @InjectMock
    UserService userService;

    @InjectMock
    TokenGenerator tokenGenerator;

    @Inject
    AuthenticationService authenticationService;

    @Test
    void tokenGenerationForNotAuthorizedUser() {
        Credential credential = new Credential();
        when(userService.isAuthorized(credential)).thenReturn(false);
        Assertions.assertThrows(BusinessLogicAuthException.class, () -> authenticationService.auth(credential));
    }

    @Test
    void tokenGenerationForAuthorizedUser() throws Exception {
        Credential credential = new Credential("username", "password");
        String token = "123456";
        when(userService.isAuthorized(credential)).thenReturn(true);
        when(tokenGenerator.generateToken("username")).thenReturn(token);
        Assertions.assertEquals(token, authenticationService.auth(credential));
    }

    @Test
    void tokenGenerationForAuthorizedUserWithError() throws Exception {
        Credential credential = new Credential("username", "password");
        when(userService.isAuthorized(credential)).thenReturn(true);
        when(tokenGenerator.generateToken("username")).thenThrow(new Exception());
        Assertions.assertThrows(BusinessLogicAuthException.class, () -> authenticationService.auth(credential));
    }
}
