package com.quarkus.resource;

import com.quarkus.exception.BusinessLogicException;
import com.quarkus.exception.ErrorMessage;
import com.quarkus.model.Comment;
import com.quarkus.model.Credential;
import com.quarkus.model.Post;
import com.quarkus.model.UserDetail;
import com.quarkus.service.AuthenticationService;
import com.quarkus.service.UserService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class BlogResourceTest {

    @InjectMock
    UserService userService;

    @InjectMock
    AuthenticationService authenticationService;

    @Test
    void registerUserWithSameUsername() {
        UserDetail userDetail = UserDetail.builder().build();

        Mockito.doThrow(new BusinessLogicException(ErrorMessage.USERNAME_NOT_UNIQUE))
                .when(userService).register(userDetail);
        given()
                .contentType(ContentType.JSON)
                .body(userDetail)
                .when().post("/blog/register")
                .then()
                .statusCode(400)
                .body(is(ErrorMessage.USERNAME_NOT_UNIQUE));
    }

    @Test
    void registerUserWithUniqueUsername() {
        UserDetail userDetail = UserDetail.builder().build();
        Mockito.doNothing().when(userService).register(userDetail);
        given()
                .contentType(ContentType.JSON)
                .body(userDetail)
                .when().post("/blog/register")
                .then()
                .statusCode(200);
    }

    @Test
    void loginWithTokenGeneration() {
        Credential credential = new Credential("username", "password");
        String token = "12345";
        Mockito.when(authenticationService.auth(credential)).thenReturn(token);
        given()
                .when()
                .contentType(ContentType.JSON)
                .body(credential)
                .post("/blog/login")
                .then()
                .statusCode(200)
                .body(is(token));
    }

    @Test
    void getPostForUnauthorizedUser() {
        given()
                .header(new Header("Authorization", "Bearer " + null))
                .contentType(ContentType.JSON)
                .pathParam("tags", 1)
                .when().get("blog/posts/tags/{tags}")
                .then()
                .statusCode(401);
    }

    @Test
    void getPostsByTitleForUnauthorizedUser() {
        given()
                .header(new Header("Authorization", "Bearer " + null))
                .contentType(ContentType.JSON)
                .pathParam("title", "Quarkus application")
                .when().get("blog/posts/title/{title}")
                .then()
                .statusCode(401);
    }

    @Test
    void getCommentsForPostForUnauthorizedUser() {
        given()
                .header(new Header("Authorization", "Bearer " + null))
                .contentType(ContentType.JSON)
                .pathParam("id", 1)
                .when().get("blog/posts/{id}/comments")
                .then()
                .statusCode(401);
    }

    @Test
    void createCommentForPostForUnauthorizedUser() {
        given()
                .header(new Header("Authorization", "Bearer " + null))
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
                .header(new Header("Authorization", "Bearer " + null))
                .contentType(ContentType.JSON)
                .body(Post.builder()
                        .title("Quarkus Title")
                        .text("Quarkus text")
                        .tags("#quarkus")
                        .build())
                .when().post("blog/posts/post")
                .then()
                .statusCode(401);
    }

    @Test
    void getPostsForUnauthorizedUser() {
        given()
                .header(new Header("Authorization", "Bearer " + null))
                .contentType(ContentType.JSON)
                .when().get("blog/posts")
                .then()
                .statusCode(401);
    }

    @Test
    void getAllPostsForUnauthorizedUser() {
        given()
                .header(new Header("Authorization", "Bearer " + null))
                .contentType(ContentType.JSON)
                .when().get("blog/posts/all")
                .then()
                .statusCode(401);
    }

    @Test
    void getUserInfoForUnauthorizedUser() {
        given()
                .when()
                .header(new Header("Authorization", "Bearer " + null))
                .contentType(ContentType.JSON)
                .get("/user")
                .then()
                .statusCode(401);
    }
}
