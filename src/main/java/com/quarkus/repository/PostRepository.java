package com.quarkus.repository;

import com.quarkus.entity.PostEntity;
import com.quarkus.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<PostEntity, Long> {

    List<PostEntity> findAllByUserEntity(UserEntity user);

    List<PostEntity> findAllByTitle(String title);

    List<PostEntity> findAllByTags(String tags);
}
