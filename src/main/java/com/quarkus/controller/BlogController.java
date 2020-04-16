package com.quarkus.controller;

import com.quarkus.model.Post;
import com.quarkus.model.UserDetail;
import com.quarkus.service.PostService;
import com.quarkus.service.UserService;
import lombok.AllArgsConstructor;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/blog")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class BlogController {

    private final UserService userService;
    private final PostService postService;

    @GET
    @Path("/posts/{id}")
    public Response getPosts(@PathParam("id") Long id) {
        List<Post> posts = postService.getUserPosts(id);
        return Response.ok(posts).build();
    }

    @POST
    @Path("/post/{id}")
    public Response createPost(@PathParam("id") Long id, Post post) {
        Long postId = postService.createPost(post, id);
        return Response.ok(postId).build();
    }

    @POST
    @Path("/register")
    public Response registerUser(UserDetail userDetail) {
        userService.register(userDetail);
        return Response.ok().build();
    }

    @POST
    @Path("/comment/{id}")
    public Response createCommentForPost(@PathParam("id") Integer id) {
        return Response.ok().build();
    }

    @GET
    @Path("/posts/title/{title}")
    public Response getPostsByTitle(@PathParam("title") String title) {
        List<Post> posts = postService.getPostsByTitle(title);
        return Response.ok(posts).build();
    }

    @GET
    @Path("/posts/tags/{tags}")
    public Response getPostsByTags(@PathParam("tags") String tags) {
        List<Post> posts = postService.getPostsByTags(tags);
        return Response.ok(posts).build();
    }
}
