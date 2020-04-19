package com.quarkus.service;

import com.quarkus.entity.UserEntity;
import com.quarkus.model.Credential;
import com.quarkus.model.UserDetail;

public interface UserService {

    void register(UserDetail userDetail);

    boolean isAuthorized(Credential credential);

    UserEntity findUserEntityByUsername(String username);

    UserDetail findUserByUsername(String username);
}
