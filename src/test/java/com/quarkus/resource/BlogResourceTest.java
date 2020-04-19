package com.quarkus.resource;

import com.quarkus.entity.CredentialEntity;
import com.quarkus.entity.UserEntity;
import com.quarkus.exception.ErrorMessage;
import com.quarkus.model.Comment;
import com.quarkus.model.Credential;
import com.quarkus.model.Post;
import com.quarkus.model.UserDetail;
import com.quarkus.repository.CredentialsRepository;
import com.quarkus.repository.UserRepository;
import com.quarkus.security.PasswordEncoder;
import com.quarkus.security.TokenGenerator;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class BlogResourceTest {

    private final UserRepository userRepository;
    private final CredentialsRepository credentialsRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenGenerator tokenGenerator;

    @Inject
    public BlogResourceTest(UserRepository userRepository,
                            CredentialsRepository credentialsRepository,
                            PasswordEncoder passwordEncoder,
                            TokenGenerator tokenGenerator) {
        this.userRepository = userRepository;
        this.credentialsRepository = credentialsRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenGenerator = tokenGenerator;
    }

    private String token;

    @SneakyThrows
    @BeforeEach
    void setUp() {
        CredentialEntity credentialEntity = credentialsRepository.findByUsername("mrQuarkusUserName")
                                                                 .orElseGet(() -> credentialsRepository.save(CredentialEntity
                                                                         .builder()
                                                                         .username("mrQuarkusUserName")
                                                                         .password(passwordEncoder.encode("password"))
                                                                         .build()));

        userRepository.save(UserEntity.builder()
                                      .name("Quarkus user")
                                      .lastName("Quarkus last name")
                                      .credentialEntity(credentialEntity)
                                      .build());

        token = tokenGenerator.generateToken("mrQuarkusUserName");
    }

    @Test
    void registerUserWithSameUsername() {
        given()
                .contentType(ContentType.JSON)
                .body(UserDetail.builder()
                                .firstName("Quarkus user")
                                .lastName("Quarkus last name")
                                .userName("mrQuarkusUserName")
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
                .when()
                .contentType(ContentType.JSON)
                .body(new Credential("mrQuarkusUserName", "password"))
                .post("/blog/login")
                .then()
                .statusCode(200);
    }

    @Test
    void getPostForAuthorizedUser() {
        given()
                .when()
                .header(new Header("Authorization", "Bearer " + token))
                .contentType(ContentType.JSON)
                .pathParam("tags", 1)
                .get("blog/posts/tags/{tags}")
                .then()
                .statusCode(200);
    }

    @Test
    void getPostsByTitleForAuthorizedUser() {
        given()
                .when()
                .header(new Header("Authorization", "Bearer " + token))
                .contentType(ContentType.JSON)
                .pathParam("title", "Quarkus application")
                .get("blog/posts/title/{title}")
                .then()
                .statusCode(200);
    }

    @Test
    void getCommentsForPostForAuthorizedUser() {
        given()
                .when()
                .header(new Header("Authorization", "Bearer " + token))
                .contentType(ContentType.JSON)
                .pathParam("id", 0)
                .get("blog/posts/{id}/comments")
                .then()
                .statusCode(400)
                .body(is(ErrorMessage.POST_DOESNT_EXIST));
    }

    @Test
    void createCommentForPostForAuthorizedUser() {
        given()
                .when()
                .header(new Header("Authorization", "Bearer " + token))
                .contentType(ContentType.JSON)
                .pathParam("id", 0)
                .body(Comment.builder().commentText("Quarkus comment").build())
                .post("blog/posts/{id}/comment")
                .then()
                .statusCode(400)
                .body(is(ErrorMessage.POST_DOESNT_EXIST));
    }

    @Test
    void createPostForAuthorizedUser() {
        given()
                .when()
                .header(new Header("Authorization", "Bearer " + token))
                .contentType(ContentType.JSON)
                .body(new Post("Quarkus Title", "Quarkus text", "#quarkus"))
                .post("/blog/posts/post")
                .then()
                .statusCode(200);
    }

    @Test
    void getPostsForAuthorizedUser() {
        given()
                .when()
                .header(new Header("Authorization", "Bearer " + token))
                .get("blog/posts")
                .then()
                .statusCode(200);
    }

    @AfterEach
    void tearDown() {
        userRepository.findAll().forEach(userEntity -> {
            userEntity.setCredentialEntity(null);
            userEntity.setPosts(null);
            userRepository.save(userEntity);
        });

        credentialsRepository.deleteAll();
    }
}