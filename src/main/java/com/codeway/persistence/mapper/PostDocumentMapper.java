package com.codeway.persistence.mapper;

import com.codeway.domain.Post;
import com.codeway.persistence.document.PostDocument;

public interface PostDocumentMapper {

    PostDocument toPostDocument(Post post);

    Post toPost(PostDocument postDocument);
}
