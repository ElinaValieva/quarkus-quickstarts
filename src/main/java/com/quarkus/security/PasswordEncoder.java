package com.quarkus.security;

import com.quarkus.JWTConfiguration;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Component
@AllArgsConstructor
public class PasswordEncoder {

    private final JWTConfiguration jwtConfiguration;

    public String encode(String password) {
        try {
            byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
                    .generateSecret(new PBEKeySpec(password.toCharArray(), jwtConfiguration.secret.getBytes(),
                            jwtConfiguration.iteration, jwtConfiguration.keylength))
                    .getEncoded();
            return Base64.getEncoder().encodeToString(result);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new RuntimeException(ex);
        }
    }
}