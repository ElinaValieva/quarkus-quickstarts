package com.quarkus.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Table(name = "user")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String lastName;

    @OneToOne(orphanRemoval = true)
    @Cascade({org.hibernate.annotations.CascadeType.MERGE})
    private CredentialEntity credentialEntity;

    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OneToMany(mappedBy = "userEntity", orphanRemoval = true)
    private List<PostEntity> posts;
}
