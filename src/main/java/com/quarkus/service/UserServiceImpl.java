package com.quarkus.service;

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
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CredentialsRepository credentialsRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(UserDetail userDetail) {
        CredentialEntity credentialEntity = credentialsRepository.findByUsername(userDetail.getUserName());

        if (credentialEntity != null)
            throw new BusinessLogicException(ErrorMessage.USERNAME_NOT_UNIQUE);

        credentialEntity = credentialsRepository.save(CredentialEntity.builder()
                .username(userDetail.getUserName())
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
        String password = passwordEncoder.encode(credential.getPassword());
        credentialsRepository.findByUsernameAndPassword(credential.getUsername(), password)
                .orElseThrow(() -> new BusinessLogicAuthException(ErrorMessage.NOT_AUTHORIZED));
        return true;
    }
}
