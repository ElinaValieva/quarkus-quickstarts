package com.quarkus.security;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.springframework.stereotype.Service;

@Service
public class JWTConfiguration {

    @ConfigProperty(name = "quarkus.jwt.duration")
    public Long duration;

    @ConfigProperty(name = "mp.jwt.verify.issuer")
    public String issuer;

    @ConfigProperty(name = "quarkus.jwt.password.secret")
    public String secret;

    @ConfigProperty(name = "quarkus.jwt.password.iteration")
    public Integer iteration;

    @ConfigProperty(name = "quarkus.jwt.password.key.length")
    public Integer keyLength;
}
