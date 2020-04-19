package com.quarkus.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "post")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "tags")
    private String tags;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "title")
    private String title;

    @Column(name = "post_text")
    private String text;

    @Column(name = "post_date")
    private Date date;

    @OneToMany
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<CommentEntity> commentEntities;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;
}
