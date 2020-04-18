package com.quarkus.repository;

import com.quarkus.entity.CommentEntity;
import com.quarkus.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<CommentEntity, Long> {
}
