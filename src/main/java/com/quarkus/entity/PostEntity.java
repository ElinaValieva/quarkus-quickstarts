package com.quarkus.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String tags;

    private Integer likes;

    private String title;

    private String text;

    @OneToMany
    private List<CommentEntity> commentEntities;

    @OneToOne
    private UserEntity userEntity;
}
