package com.quarkus.service;

import com.quarkus.entity.Credentials;
import com.quarkus.entity.User;
import com.quarkus.exception.BusinessLogicException;
import com.quarkus.exception.ErrorMessage;
import com.quarkus.model.UserDetail;
import com.quarkus.repository.CredentialsRepository;
import com.quarkus.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CredentialsRepository credentialsRepository;

    @Override
    public void register(UserDetail userDetail) {
        Credentials credentials = credentialsRepository.findByUsername(userDetail.getUserName());

        if (credentials != null)
            throw new BusinessLogicException(ErrorMessage.USERNAME_NOT_UNIQUE);

        credentials = credentialsRepository.save(Credentials.builder()
                                                            .username(userDetail.getUserName())
                                                            .password(userDetail.getPassword())
                                                            .build());
        userRepository.save(User.builder()
                                .name(userDetail.getFirstName())
                                .lastName(userDetail.getLastName())
                                .credentials(credentials)
                                .build());
    }
}
