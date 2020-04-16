package com.quarkus.controller;

import com.quarkus.model.Post;
import com.quarkus.model.UserDetail;
import com.quarkus.service.UserService;
import lombok.AllArgsConstructor;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/blog")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class BlogController {

    private final UserService userService;

    @GET
    @Path("/posts/{id}")
    public Response getPosts(@PathParam("id") Integer id) {
        return Response.ok("set posts with id: " + id).build();
    }

    @POST
    @Path("/post")
    public Response createPost(Post post) {
        return Response.ok(post).build();
    }

    @POST
    @Path("/register")
    public Response registerUser(UserDetail userDetail) {
        userService.register(userDetail);
        return Response.ok().build();
    }
}
