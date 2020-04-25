package com.quarkus.exception;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Slf4j
@Provider
public class BusinessLogicExceptionMapper implements ExceptionMapper<BusinessLogicException> {

    @Override
    public Response toResponse(BusinessLogicException exception) {
        log.error("", exception);
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
    }
}
