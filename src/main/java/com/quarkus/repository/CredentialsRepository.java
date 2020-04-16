package com.quarkus.repository;

import com.quarkus.entity.Credentials;
import org.springframework.data.repository.CrudRepository;

public interface CredentialsRepository extends CrudRepository<Credentials, Long> {

    Credentials findByUsername(String username);
}
