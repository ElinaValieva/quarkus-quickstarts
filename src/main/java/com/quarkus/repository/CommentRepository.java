package com.quarkus.repository;

import com.quarkus.entity.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
