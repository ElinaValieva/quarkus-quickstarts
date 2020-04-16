package com.quarkus.service;

import com.quarkus.model.Post;

import java.util.List;

public interface PostService {

    Long createPost(Post post, Long id);

    List<Post> getUserPosts(Long id);

    List<Post> getPostsByTitle(String title);

    List<Post> getPostsByTags(String tags);
}
