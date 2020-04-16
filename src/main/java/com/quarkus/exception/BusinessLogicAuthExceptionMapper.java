package com.quarkus.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BusinessLogicAuthExceptionMapper implements ExceptionMapper<BusinessLogicAuthException> {

    @Override
    public Response toResponse(BusinessLogicAuthException exception) {
        return Response.status(Response.Status.UNAUTHORIZED).entity(exception.getMessage()).build();
    }
}
