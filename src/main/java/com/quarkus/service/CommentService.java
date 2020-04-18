package com.quarkus.service;

import com.quarkus.model.Comment;

import java.util.List;

public interface CommentService {

    void createCommentForPost(String comment, Long id);

    List<Comment> getCommendsForPost(Long id);
}
