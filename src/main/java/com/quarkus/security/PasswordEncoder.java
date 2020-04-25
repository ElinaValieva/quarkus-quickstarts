package com.quarkus.security;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.enterprise.context.ApplicationScoped;
import java.util.Base64;

@Slf4j
@ApplicationScoped
public class PasswordEncoder {

    @ConfigProperty(name = "quarkus.jwt.password.iteration")
    Integer iteration;

    @ConfigProperty(name = "quarkus.jwt.password.key.length")
    Integer keyLength;

    @ConfigProperty(name = "quarkus.jwt.password.secret")
    String secret;

    @SneakyThrows
    public String encode(String password) {
        log.debug("Encoding password");

        byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
                .generateSecret(new PBEKeySpec(password.toCharArray(), secret.getBytes(), iteration, keyLength))
                .getEncoded();
        return Base64.getEncoder().encodeToString(result);
    }
}