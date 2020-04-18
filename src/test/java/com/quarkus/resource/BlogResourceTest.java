package com.quarkus.resource;

import com.quarkus.entity.CredentialEntity;
import com.quarkus.entity.UserEntity;
import com.quarkus.model.Comment;
import com.quarkus.model.Credential;
import com.quarkus.model.Post;
import com.quarkus.model.UserDetail;
import com.quarkus.repository.CredentialsRepository;
import com.quarkus.repository.UserRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class BlogResourceTest {

    @Inject
    private UserRepository userRepository;

    @Inject
    private CredentialsRepository credentialsRepository;

    @BeforeEach
    void setUp() {
        CredentialEntity quarkus = credentialsRepository.findByUsername("quark");
        if (quarkus == null) {
            CredentialEntity credentialEntity = credentialsRepository.save(CredentialEntity.builder()
                    .username("quark")
                    .password("password")
                    .build());

            userRepository.save(UserEntity.builder()
                    .name("Quarkus user")
                    .lastName("Quarkus last name")
                    .credentialEntity(credentialEntity)
                    .build());
        }
    }

    @Test
    void registerUserWithSameUsername() {
        given()
                .contentType(ContentType.JSON)
                .body(UserDetail.builder()
                        .firstName("Quarkus user")
                        .lastName("Quarkus last name")
                        .userName("quark")
                        .password("password")
                        .build())
                .when().post("/blog/register")
                .then()
                .statusCode(400);
    }

    @Test
    void login() {
        given()
                .contentType(ContentType.JSON)
                .body(new Credential("quark", "password"))
                .when().post("/blog/login")
                .then()
                .statusCode(401);
    }

    @Test
    void getPostForUnauthorizedUser() {
        given()
                .contentType(ContentType.JSON)
                .pathParam("tags", 1)
                .when().get("blog/posts/tags/{tags}")
                .then()
                .statusCode(401);
    }

    @Test
    void getPostsByTitleForUnauthorizedUser() {
        given()
                .contentType(ContentType.JSON)
                .pathParam("title", "Quarkus application")
                .when().get("blog/posts/title/{title}")
                .then()
                .statusCode(401);
    }

    @Test
    void getCommentsForPostForUnauthorizedUser() {
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", 1)
                .when().get("blog/posts/{id}/comments")
                .then()
                .statusCode(401);
    }

    @Test
    void createCommentForPostForUnauthorizedUser() {
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", 1)
                .body(Comment.builder().commentText("Quarkus comment").build())
                .when().post("blog/posts/{id}/comment")
                .then()
                .statusCode(401);
    }

    @Test
    void createPostForUnauthorizedUser() {
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", 1)
                .body(new Post("Quarkus Title", "Quarkus text", "#quarkus"))
                .when().post("blog/post/{id}")
                .then()
                .statusCode(401);
    }

    @Test
    void getPostsForUnauthorizedUser() {
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", 1)
                .when().get("blog/posts/{id}")
                .then()
                .statusCode(401);
    }
}