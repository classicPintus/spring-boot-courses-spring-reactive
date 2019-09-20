package com.codeway.rest;

import com.codeway.domain.Post;
import com.codeway.rest.model.PostModel;

public interface PostMapper {
    PostModel map(Post post);
}
