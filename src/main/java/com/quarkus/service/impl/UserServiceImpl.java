package com.quarkus.service.impl;

import com.quarkus.entity.CredentialEntity;
import com.quarkus.entity.UserEntity;
import com.quarkus.exception.BusinessLogicAuthException;
import com.quarkus.exception.BusinessLogicException;
import com.quarkus.exception.ErrorMessage;
import com.quarkus.model.Credential;
import com.quarkus.model.UserDetail;
import com.quarkus.repository.CredentialsRepository;
import com.quarkus.repository.UserRepository;
import com.quarkus.security.PasswordEncoder;
import com.quarkus.service.UserService;
import com.quarkus.util.ModelMapper;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@Slf4j
@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    CredentialsRepository credentialsRepository;

    @Inject
    PasswordEncoder passwordEncoder;

    @Override
    public void register(UserDetail userDetail) {
        log.debug("Registering user: {}", userDetail);

        Optional<CredentialEntity> optionalCredentialEntity = credentialsRepository.findByUsername(userDetail.getUsername());

        if (optionalCredentialEntity.isPresent())
            throw new BusinessLogicException(ErrorMessage.USERNAME_NOT_UNIQUE);

        CredentialEntity credentialEntity = credentialsRepository.save(CredentialEntity.builder()
                .username(userDetail.getUsername())
                .password(passwordEncoder.encode(userDetail.getPassword()))
                .build());

        userRepository.save(UserEntity.builder()
                .name(userDetail.getFirstName())
                .lastName(userDetail.getLastName())
                .credentialEntity(credentialEntity)
                .build());
    }

    @Override
    public boolean isAuthorized(Credential credential) {
        log.debug("Check authorization for user with username: {}", credential.getUsername());

        String password = passwordEncoder.encode(credential.getPassword());
        credentialsRepository.findByUsernameAndPassword(credential.getUsername(), password)
                .orElseThrow(() -> new BusinessLogicAuthException(ErrorMessage.NOT_AUTHORIZED));
        return true;
    }

    @Override
    public UserEntity findUserEntityByUsername(String username) {
        log.debug("Find user by username: {}", username);

        CredentialEntity credentialEntity = credentialsRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessLogicAuthException(ErrorMessage.USER_NOT_FOUND));

        return userRepository.findByCredentialEntity(credentialEntity)
                .orElseThrow(() -> new BusinessLogicException(ErrorMessage.USER_NOT_FOUND));
    }

    @Override
    public UserDetail findUserByUsername(String username) {
        log.debug("Find user by username: {}", username);

        return ModelMapper.mapUserEntityToUserDetail(findUserEntityByUsername(username));
    }
}
