package com.quarkus.resource;

import com.quarkus.entity.UserEntity;
import com.quarkus.model.Comment;
import com.quarkus.model.Credential;
import com.quarkus.model.Post;
import com.quarkus.model.UserDetail;
import com.quarkus.service.AuthenticationService;
import com.quarkus.service.CommentService;
import com.quarkus.service.PostService;
import com.quarkus.service.UserService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/blog")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BlogResource {

    @Inject
    UserService userService;

    @Inject
    PostService postService;

    @Inject
    AuthenticationService authenticationService;

    @Inject
    CommentService commentService;

    @GET
    @Path("/posts")
    @RolesAllowed("FOLLOWER")
    public Response getPosts(@Context SecurityContext sec) {
        String username = sec.getUserPrincipal().getName();
        UserEntity userEntity = userService.getUserEntityByUsername(username);
        List<Post> posts = postService.getUserPosts(userEntity);
        return Response.ok(posts).build();
    }

    @GET
    @Path("/posts/all")
    @RolesAllowed("FOLLOWER")
    public Response getAllPosts() {
        List<Post> posts = postService.getPosts();
        return Response.ok(posts).build();
    }

    @GET
    @Path("/user")
    @RolesAllowed("FOLLOWER")
    public Response getUserInfo(@Context SecurityContext sec) {
        String username = sec.getUserPrincipal().getName();
        UserDetail userDetail = userService.getUserByUsername(username);
        return Response.ok(userDetail).build();
    }

    @POST
    @Path("/posts/post")
    @RolesAllowed("FOLLOWER")
    public Response createPost(@Context SecurityContext sec, Post post) {
        String username = sec.getUserPrincipal().getName();
        UserEntity userEntity = userService.getUserEntityByUsername(username);
        Long postId = postService.createPost(post, userEntity);
        return Response.ok(postId).build();
    }

    @POST
    @Path("/register")
    @PermitAll
    public Response registerUser(UserDetail userDetail) {
        userService.register(userDetail);
        return Response.ok().build();
    }

    @POST
    @Path("/posts/{id}/comment")
    @RolesAllowed("FOLLOWER")
    public Response createCommentForPost(@PathParam("id") Long id, Comment comment) {
        commentService.createCommentForPost(comment.getCommentText(), id);
        return Response.ok().build();
    }

    @GET
    @Path("/posts/{id}/comments")
    @RolesAllowed("FOLLOWER")
    public Response getCommentsForPost(@PathParam("id") Long id) {
        List<Comment> commends = commentService.getCommendsForPost(id);
        return Response.ok(commends).build();
    }

    @GET
    @Path("/posts/title/{title}")
    @RolesAllowed("FOLLOWER")
    public Response getPostsByTitle(@PathParam("title") String title) {
        List<Post> posts = postService.getPostsByTitle(title);
        return Response.ok(posts).build();
    }

    @GET
    @Path("/posts/tags/{tags}")
    @RolesAllowed("FOLLOWER")
    public Response getPostsByTags(@PathParam("tags") String tags) {
        List<Post> posts = postService.getPostsByTags(tags);
        return Response.ok(posts).build();
    }

    @POST
    @Path("/login")
    @PermitAll
    public Response login(Credential credential) {
        String auth = authenticationService.auth(credential);
        return Response.ok(auth).build();
    }
}
