package com.quarkus.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BusinessLogicExceptionMapper implements ExceptionMapper<BusinessLogicException> {

    @Override
    public Response toResponse(BusinessLogicException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
    }
}
