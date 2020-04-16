package com.quarkus.repository;

import com.quarkus.entity.CredentialEntity;
import org.springframework.data.repository.CrudRepository;

public interface CredentialsRepository extends CrudRepository<CredentialEntity, Long> {

    CredentialEntity findByUsername(String username);
}
