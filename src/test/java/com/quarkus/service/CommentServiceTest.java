package com.quarkus.service;

import com.quarkus.entity.CommentEntity;
import com.quarkus.entity.CredentialEntity;
import com.quarkus.entity.PostEntity;
import com.quarkus.entity.UserEntity;
import com.quarkus.exception.BusinessLogicException;
import com.quarkus.model.Comment;
import com.quarkus.model.UserDetail;
import com.quarkus.repository.PostRepository;
import com.quarkus.util.ModelMapper;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.*;

import static org.mockito.Mockito.when;

@QuarkusTest
public class CommentServiceTest {

    @InjectMock
    PostRepository postRepository;

    @Inject
    CommentService commentService;

    private static final long ID = 0L;

    @Test
    void createCommentForNotExistingPost() {
        when(postRepository.findById(ID)).thenReturn(Optional.empty());
        Assertions.assertThrows(BusinessLogicException.class, () -> commentService.createCommentForPost("comment", ID));
    }

    @Test
    void createCommentForExistingPost() {
        when(postRepository.findById(ID)).thenReturn(Optional.of(PostEntity.builder().commentEntities(new ArrayList<>()).build()));
        Assertions.assertDoesNotThrow(() -> commentService.createCommentForPost("comment", ID));
    }

    @Test
    void getCommendsForPostForNotExistingPost() {
        when(postRepository.findById(ID)).thenReturn(Optional.empty());
        Assertions.assertThrows(BusinessLogicException.class, () -> commentService.getCommendsForPost(ID));
    }

    @Test
    void getCommendsForPostForExistingPost() {
        when(postRepository.findById(ID)).thenReturn(Optional.of(PostEntity.builder().commentEntities(getCommentEntityList()).build()));
        Assertions.assertEquals(getCommentList(), commentService.getCommendsForPost(ID));
    }

    private List<CommentEntity> getCommentEntityList() {
        return Collections.singletonList(CommentEntity.builder()
                .comment("comment")
                .date(new Date(100))
                .id(0L)
                .userEntity(UserEntity.builder()
                        .name("first name")
                        .lastName("last name")
                        .credentialEntity(CredentialEntity.builder()
                                .username("username")
                                .build())
                        .build())
                .build());
    }

    private List<Comment> getCommentList() {
        return Collections.singletonList(Comment.builder()
                .commentText("comment")
                .date(ModelMapper.createDate(new Date(100)))
                .userDetail(UserDetail.builder()
                        .username("username")
                        .firstName("first name")
                        .lastName("last name")
                        .build())
                .build());
    }
}
