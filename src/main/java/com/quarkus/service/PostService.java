package com.quarkus.service;

import com.quarkus.entity.UserEntity;
import com.quarkus.model.Post;

import java.util.List;

public interface PostService {

    Long createPost(Post post, UserEntity userEntity);

    List<Post> getUserPosts(UserEntity userEntity);

    List<Post> getPostsByTitle(String title);

    List<Post> getPostsByTags(String tags);
}
