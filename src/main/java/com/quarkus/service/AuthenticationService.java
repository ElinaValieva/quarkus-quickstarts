package com.quarkus.service;

import com.quarkus.model.Credential;

public interface AuthenticationService {

    String auth(Credential credential);
}
