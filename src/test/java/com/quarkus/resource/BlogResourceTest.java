package com.quarkus.resource;

import com.quarkus.entity.CredentialEntity;
import com.quarkus.entity.UserEntity;
import com.quarkus.exception.ErrorMessage;
import com.quarkus.model.Credential;
import com.quarkus.model.UserDetail;
import com.quarkus.repository.CredentialsRepository;
import com.quarkus.repository.UserRepository;
import com.quarkus.security.PasswordEncoder;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
@AllArgsConstructor
@QuarkusTestResource(H2DatabaseTestResource.class)
public class BlogResourceTest {

    private final UserRepository userRepository;
    private final CredentialsRepository credentialsRepository;
    private final PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        CredentialEntity credentialEntity = credentialsRepository.save(CredentialEntity.builder()
                .username("mrQuarkus")
                .password(passwordEncoder.encode("password"))
                .build());

        userRepository.save(UserEntity.builder()
                .name("Quarkus user")
                .lastName("Quarkus last name")
                .credentialEntity(credentialEntity)
                .build());
    }

    @Test
    void registerUserWithSameUsername() {
        given()
                .contentType(ContentType.JSON)
                .body(UserDetail.builder()
                        .firstName("Quarkus user")
                        .lastName("Quarkus last name")
                        .userName("mrQuarkus")
                        .password("password")
                        .build())
                .when().post("/blog/register")
                .then()
                .statusCode(400)
                .body(is(ErrorMessage.USERNAME_NOT_UNIQUE));
    }

    @Test
    void login() {
        given()
                .contentType(ContentType.JSON)
                .body(new Credential("mrQuarkus", "password"))
                .when().post("/blog/login")
                .then()
                .statusCode(200);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        credentialsRepository.deleteAll();
    }
}