package com.quarkus.security;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class JWTConfiguration {

    @Inject
    @ConfigProperty(name = "quarkus.jwt.duration")
    public Long duration;

    @Inject
    @ConfigProperty(name = "mp.jwt.verify.issuer")
    public String issuer;

    @Inject
    @ConfigProperty(name = "quarkus.jwt.password.secret")
    public String secret;

    @Inject
    @ConfigProperty(name = "quarkus.jwt.password.iteration")
    public Integer iteration;

    @Inject
    @ConfigProperty(name = "quarkus.jwt.password.key.length")
    public Integer keyLength;
}
