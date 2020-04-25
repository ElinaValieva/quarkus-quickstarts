package com.quarkus.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Cascade({org.hibernate.annotations.CascadeType.DELETE})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credential_id")
    private CredentialEntity credentialEntity;

    @Cascade({org.hibernate.annotations.CascadeType.DELETE})
    @OneToMany(mappedBy = "userEntity")
    private List<PostEntity> posts;
}
