package com.quarkus.util;

import com.quarkus.entity.PostEntity;
import com.quarkus.model.Post;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModelMapper {

    public static Post mapPostEntityToPost(PostEntity postEntity) {
        return Post.builder()
                   .title(postEntity.getTitle())
                   .text(postEntity.getText())
                   .tags(postEntity.getTags())
                   .build();
    }

    public static List<Post> mapPostEntitiesToPost(List<PostEntity> postEntities) {
        return postEntities.stream()
                           .map(ModelMapper::mapPostEntityToPost)
                           .collect(Collectors.toList());
    }
}
