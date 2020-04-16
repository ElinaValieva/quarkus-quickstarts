package com.quarkus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDetail {

    private String userName;

    private String firstName;

    private String lastName;

    private String password;
}
