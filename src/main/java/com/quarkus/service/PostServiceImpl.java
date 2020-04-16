package com.quarkus.service;

import com.quarkus.entity.PostEntity;
import com.quarkus.entity.UserEntity;
import com.quarkus.exception.BusinessLogicException;
import com.quarkus.exception.ErrorMessage;
import com.quarkus.model.Post;
import com.quarkus.repository.PostRepository;
import com.quarkus.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.quarkus.util.ModelMapper.mapPostEntitiesToPost;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public Long createPost(Post post, Long id) {
        // todo
        userRepository.findAll().forEach(userEntity -> System.out.println(userEntity.getId()));
        UserEntity user = userRepository.findById(id)
                                        .orElseThrow(() -> new BusinessLogicException(ErrorMessage.USER_NOT_FOUND));

        PostEntity postEntity = postRepository.save(PostEntity.builder()
                                                              .likes(0)
                                                              .tags(post.getTags())
                                                              .title(post.getTitle())
                                                              .text(post.getText())
                                                              .userEntity(user)
                                                              .build());
        return postEntity.getId();
    }

    @Override
    public List<Post> getUserPosts(Long id) {
        // todo
        List<PostEntity> postEntities = postRepository.findAllById(id);
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
}
