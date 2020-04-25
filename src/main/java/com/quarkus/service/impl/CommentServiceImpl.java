package com.quarkus.service.impl;

import com.quarkus.entity.CommentEntity;
import com.quarkus.entity.PostEntity;
import com.quarkus.exception.BusinessLogicException;
import com.quarkus.exception.ErrorMessage;
import com.quarkus.model.Comment;
import com.quarkus.repository.PostRepository;
import com.quarkus.service.CommentService;
import com.quarkus.util.ModelMapper;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Slf4j
@ApplicationScoped
public class CommentServiceImpl implements CommentService {

    @Inject
    PostRepository postRepository;

    @Override
    public void createCommentForPost(String comment, Long id) {
        log.debug("Create comment: {} for post with id: {}", comment, id);

        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(() -> new BusinessLogicException(ErrorMessage.POST_DOESNT_EXIST));

        List<CommentEntity> commentEntities = postEntity.getCommentEntities();
        commentEntities.add(CommentEntity.builder()
                .comment(comment)
                .date(new Date())
                .build());
        postEntity.setCommentEntities(commentEntities);
        postRepository.save(postEntity);
    }

    @Override
    public List<Comment> getCommendsForPost(Long id) {
        log.debug("Get comments for post: {}", id);

        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(() -> new BusinessLogicException(ErrorMessage.POST_DOESNT_EXIST));
        return ModelMapper.mapCommentEntitiesToComment(postEntity.getCommentEntities());
    }
}
