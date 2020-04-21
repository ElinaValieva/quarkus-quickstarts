package com.quarkus.security;

import lombok.Data;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Data
@ApplicationScoped
public class JWTConfiguration {

    @Inject
    @ConfigProperty(name = "quarkus.jwt.duration")
    private Long duration;

    @Inject
    @ConfigProperty(name = "mp.jwt.verify.issuer")
    private String issuer;

    @Inject
    @ConfigProperty(name = "quarkus.jwt.password.secret")
    private String secret;

    @Inject
    @ConfigProperty(name = "quarkus.jwt.password.iteration")
    private Integer iteration;

    @Inject
    @ConfigProperty(name = "quarkus.jwt.password.key.length")
    private Integer keyLength;
}
