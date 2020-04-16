package com.quarkus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User user;

    private String comment;
}
