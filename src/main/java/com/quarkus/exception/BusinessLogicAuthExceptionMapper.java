package com.quarkus.exception;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Slf4j
@Provider
public class BusinessLogicAuthExceptionMapper implements ExceptionMapper<BusinessLogicAuthException> {

    @Override
    public Response toResponse(BusinessLogicAuthException exception) {
        log.error("", exception);
        return Response.status(Response.Status.UNAUTHORIZED).entity(exception.getMessage()).build();
    }
}
