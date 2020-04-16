package com.quarkus.controller;

import com.quarkus.model.Post;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/blog")
public class BlogController {

    @GET
    @Path("/posts/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPosts(@PathParam("id") Integer id) {
        return Response.ok("set posts with id: " + id).build();
    }

    @POST
    @Path("/post")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPost(Post post) {
        return Response.ok(post).build();
    }
}
