package com.quarkus.service;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@RegisterRestClient(configKey = "date-api")
public interface OpenApiDateService {

    @GET
    @Path("{day}/{month}/date")
    @Produces(MediaType.TEXT_PLAIN)
    String getDateByMonth(@PathParam("day") Integer monthDay, @PathParam("month") Integer monthNumber);
}
