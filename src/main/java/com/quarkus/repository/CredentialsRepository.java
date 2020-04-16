package com.quarkus.repository;

import com.quarkus.entity.CredentialEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CredentialsRepository extends CrudRepository<CredentialEntity, Long> {

    CredentialEntity findByUsername(String username);

    Optional<CredentialEntity> findByUsernameAndPassword(String username, String password);
}
