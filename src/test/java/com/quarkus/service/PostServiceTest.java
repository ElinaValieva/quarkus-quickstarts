package com.quarkus.service;

import com.quarkus.entity.PostEntity;
import com.quarkus.entity.UserEntity;
import com.quarkus.model.Post;
import com.quarkus.repository.PostRepository;
import com.quarkus.util.ModelMapper;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@QuarkusTest
public class PostServiceTest {

    @InjectMock
    PostRepository postRepository;

    @Inject
    PostService postService;

    @Test
    void createPost() {
        Post post = Post.builder().tags("test").text("text").title("test").date("01 янв.").build();
        UserEntity userEntity = UserEntity.builder().id(0L).build();
        PostEntity expectedPostEntity = PostEntity.builder().id(1L).build();
        when(postRepository.save(any())).thenReturn(expectedPostEntity);
        Assertions.assertSame(expectedPostEntity.getId(), postService.createPost(post, userEntity));
    }

    @Test
    void getUserPosts() {
        UserEntity userEntity = UserEntity.builder().build();
        when(postRepository.findAllByUserEntity(userEntity))
                .thenReturn(getPostsEntitiesList());
        Assertions.assertEquals(getPostList(), postService.getUserPosts(userEntity));
    }

    @Test
    void getPostsByTitle() {
        String title = "test";
        when(postRepository.findAllByTitle(title))
                .thenReturn(getPostsEntitiesList());
        Assertions.assertEquals(getPostList(), postService.getPostsByTitle(title));
    }

    @Test
    void getPostsByTags() {
        String tags = "test";
        when(postRepository.findAllByTags(tags))
                .thenReturn(getPostsEntitiesList());
        Assertions.assertEquals(getPostList(), postService.getPostsByTags(tags));
    }

    @Test
    void getPosts() {
        when(postRepository.findAll())
                .thenReturn(getPostsEntitiesList());
        Assertions.assertEquals(getPostList(), postService.getPosts());
    }

    private List<PostEntity> getPostsEntitiesList() {
        return Collections.singletonList(PostEntity.builder()
                .id(1L)
                .likes(0)
                .tags("test")
                .text("text")
                .title("test")
                .date(new Date(100))
                .userEntity(UserEntity.builder().build())
                .build());
    }

    private List<Post> getPostList() {
        return Collections.singletonList(Post.builder().tags("test").text("text").title("test").date(ModelMapper.createDate(new Date(100))).build());
    }
}
