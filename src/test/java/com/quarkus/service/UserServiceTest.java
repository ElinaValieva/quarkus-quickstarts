package com.quarkus.service;

import com.quarkus.entity.CredentialEntity;
import com.quarkus.exception.BusinessLogicAuthException;
import com.quarkus.exception.BusinessLogicException;
import com.quarkus.model.Credential;
import com.quarkus.model.UserDetail;
import com.quarkus.repository.CredentialsRepository;
import com.quarkus.repository.UserRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@QuarkusTest
public class UserServiceTest {

    @InjectMock
    CredentialsRepository credentialsRepository;

    @InjectMock
    UserRepository userRepository;

    @Inject
    UserService userService;

    @Test
    void registerWithSameUsername() {
        UserDetail userDetail = UserDetail.builder()
                .firstName("Test")
                .lastName("Last")
                .password("pass")
                .username("username")
                .build();

        when(credentialsRepository.findByUsername(userDetail.getUsername()))
                .thenReturn(Optional.of(CredentialEntity.builder()
                        .id(0L)
                        .password("")
                        .build()));
        Assertions.assertThrows(BusinessLogicException.class, () -> userService.register(userDetail));
    }

    @Test
    void isAuthorizedWithWrongCredentials() {
        when(credentialsRepository.findByUsernameAndPassword(eq("username"), anyString()))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(BusinessLogicAuthException.class, () -> userService.isAuthorized(new Credential("username", "password")));
    }

    @Test
    void isAuthorizedWithRightCredentials() {
        when(credentialsRepository.findByUsernameAndPassword(eq("username"), anyString()))
                .thenReturn(Optional.of(CredentialEntity.builder().build()));
        Assertions.assertTrue(userService.isAuthorized(new Credential("username", "password")));
    }

    @Test
    void getUserEntityByUsernameWithWrongUsername() {
        when(credentialsRepository.findByUsername("username")).thenReturn(Optional.empty());
        Assertions.assertThrows(BusinessLogicException.class, () -> userService.getUserByUsername("username"));
    }

    @Test
    void getUserEntityByUsernameWithWrongCredentials() {
        CredentialEntity credentialEntity = CredentialEntity.builder().build();
        when(credentialsRepository.findByUsername("username")).thenReturn(Optional.of(credentialEntity));
        when(userRepository.findByCredentialEntity(credentialEntity)).thenReturn(Optional.empty());
        Assertions.assertThrows(BusinessLogicException.class, () -> userService.getUserByUsername("username"));
    }
}
