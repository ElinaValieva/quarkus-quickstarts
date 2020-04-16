package com.quarkus.service;

import com.quarkus.model.UserDetail;

public interface UserService {

    void register(UserDetail userDetail);
}
