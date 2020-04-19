package com.quarkus.service.impl;

import com.quarkus.entity.PostEntity;
import com.quarkus.entity.UserEntity;
import com.quarkus.model.Post;
import com.quarkus.repository.PostRepository;
import com.quarkus.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.quarkus.util.ModelMapper.mapPostEntitiesToPost;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Long createPost(Post post, UserEntity userEntity) {

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
        List<PostEntity> postEntities = postRepository.findAllByUserEntity(userEntity);
        return mapPostEntitiesToPost(postEntities);
    }

    @Override
    public List<Post> getPostsByTitle(String title) {
        List<PostEntity> postEntities = postRepository.findAllByTitle(title);
        return mapPostEntitiesToPost(postEntities);
    }

    @Override
    public List<Post> getPostsByTags(String tags) {
        List<PostEntity> postEntities = postRepository.findAllByTags(tags);
        return mapPostEntitiesToPost(postEntities);
    }

    @Override
    public List<Post> getPosts() {
        List<PostEntity> posts = new ArrayList<>();
        postRepository.findAll().forEach(posts::add);
        return mapPostEntitiesToPost(posts);
    }
}
