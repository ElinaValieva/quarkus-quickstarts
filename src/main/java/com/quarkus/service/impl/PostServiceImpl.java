package com.quarkus.service.impl;

import com.quarkus.entity.PostEntity;
import com.quarkus.entity.UserEntity;
import com.quarkus.model.Post;
import com.quarkus.repository.PostRepository;
import com.quarkus.service.PostService;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.quarkus.util.ModelMapper.mapPostEntitiesToPost;

@Slf4j
@ApplicationScoped
public class PostServiceImpl implements PostService {

    @Inject
    PostRepository postRepository;

    @Override
    public Long createPost(Post post, UserEntity userEntity) {
        log.debug("Create post: {} for user: {}", post, userEntity.getId());

        PostEntity postEntity = postRepository.save(PostEntity.builder()
                .likes(0)
                .tags(post.getTags())
                .title(post.getTitle())
                .text(post.getText())
                .userEntity(userEntity)
                .date(new Date())
                .build());

        return postEntity.getId();
    }

    @Override
    public List<Post> getUserPosts(UserEntity userEntity) {
        log.debug("Get posts for user: {}", userEntity.getId());

        List<PostEntity> postEntities = postRepository.findAllByUserEntity(userEntity);
        return mapPostEntitiesToPost(postEntities);
    }

    @Override
    public List<Post> getPostsByTitle(String title) {
        log.debug("Get posts with title: {}", title);

        List<PostEntity> postEntities = postRepository.findAllByTitle(title);
        return mapPostEntitiesToPost(postEntities);
    }

    @Override
    public List<Post> getPostsByTags(String tags) {
        log.debug("Get posts with tags: {}", tags);

        List<PostEntity> postEntities = postRepository.findAllByTags(tags);
        return mapPostEntitiesToPost(postEntities);
    }

    @Override
    public List<Post> getPosts() {
        log.debug("Get all posts");

        List<PostEntity> posts = new ArrayList<>();
        postRepository.findAll().forEach(posts::add);
        return mapPostEntitiesToPost(posts);
    }
}
