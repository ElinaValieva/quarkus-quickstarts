package com.quarkus.repository;

import com.quarkus.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<PostEntity, Long> {

    List<PostEntity> findAllById(Long id);

    List<PostEntity> findAllByTitle(String title);

    List<PostEntity> findAllByTags(String tags);
}
