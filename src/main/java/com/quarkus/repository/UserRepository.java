package com.quarkus.repository;

import com.quarkus.entity.CredentialEntity;
import com.quarkus.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByCredentialEntity(CredentialEntity credential);

    void deleteAllByName(String name);
}
