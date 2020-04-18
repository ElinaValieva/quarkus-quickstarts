package com.quarkus.resource;

import com.quarkus.model.Comment;
import com.quarkus.model.Post;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class BlogResourceForErrorTest {

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
