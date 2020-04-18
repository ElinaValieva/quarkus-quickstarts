package com.quarkus.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail {

    private String userName;

    private String firstName;

    private String lastName;

    private String password;
}
