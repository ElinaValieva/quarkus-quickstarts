package com.quarkus.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Table(name = "comment")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @OneToOne
    private UserEntity userEntity;

    @Column(name = "comment")
    private String comment;

    @Column(name = "comment_date")
    private Date date;
}
