package com.quarkus;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.springframework.stereotype.Service;

@Service
public class JWTConfiguration {

    @ConfigProperty(name = "com.ard333.quarkusjwt.jwt.duration")
    public Long duration;

    @ConfigProperty(name = "mp.jwt.verify.issuer")
    public String issuer;

    @ConfigProperty(name = "com.ard333.quarkusjwt.password.secret")
    public String secret;

    @ConfigProperty(name = "com.ard333.quarkusjwt.password.iteration")
    public Integer iteration;

    @ConfigProperty(name = "com.ard333.quarkusjwt.password.keylength")
    public Integer keylength;
}
