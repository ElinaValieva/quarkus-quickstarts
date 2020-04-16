package com.quarkus.repository;

import com.quarkus.entity.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
