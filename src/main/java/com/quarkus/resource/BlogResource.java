package com.quarkus.resource;

import com.quarkus.model.Comment;
import com.quarkus.model.Credential;
import com.quarkus.model.Post;
import com.quarkus.model.UserDetail;
import com.quarkus.service.AuthenticationService;
import com.quarkus.service.CommentService;
import com.quarkus.service.PostService;
import com.quarkus.service.UserService;
import lombok.AllArgsConstructor;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/blog")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class BlogResource {

    private final UserService userService;
    private final PostService postService;
    private final AuthenticationService authenticationService;
    private final CommentService commentService;

    @GET
    @Path("/posts/{id}")
    @RolesAllowed("FOLLOWER")
    public Response getPosts(@PathParam("id") Long id) {
        List<Post> posts = postService.getUserPosts(id);
        return Response.ok(posts).build();
    }

    @POST
    @Path("/post/{id}")
    @RolesAllowed("FOLLOWER")
    public Response createPost(@PathParam("id") Long id, Post post) {
        Long postId = postService.createPost(post, id);
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

    @POST
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
