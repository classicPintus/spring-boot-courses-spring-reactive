package com.codeway.rest;

import com.codeway.domain.Post;
import com.codeway.rest.model.PostModel;

public class PostMapperRest implements PostMapper {

    @Override
    public PostModel map(Post post) {
        return new PostModel(
                post.getOwner(),
                post.getContent(),
                post.getIdentifier()
        );
    }
}
