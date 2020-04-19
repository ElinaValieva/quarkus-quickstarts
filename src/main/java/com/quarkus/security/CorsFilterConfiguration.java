package com.quarkus.security;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class CorsFilterConfiguration implements ContainerResponseFilter {

    @Override
    public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext containerRespCtx) {
        containerRespCtx.getHeaders().add("Access-Control-Allow-Origin", "http://localhost:8081");
        containerRespCtx.getHeaders().add("Access-Control-Allow-Headers", "accept, origin, authorization, content-type, x-requested-with");
        containerRespCtx.getHeaders().add("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
        containerRespCtx.getHeaders().add("Access-Control-Max-Age", "1209600");
    }
}
