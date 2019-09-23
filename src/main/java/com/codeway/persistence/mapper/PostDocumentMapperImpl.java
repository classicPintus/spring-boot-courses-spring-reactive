package com.codeway.persistence.mapper;

import com.codeway.domain.Post;
import com.codeway.persistence.document.PostDocument;

public class PostDocumentMapperImpl implements PostDocumentMapper {

    @Override
    public PostDocument toPostDocument(Post post) {
        PostDocument res = new PostDocument();
        res.setIdentifier(post.getIdentifier());
        res.setContent(post.getContent());
        res.setOwner(post.getOwner());
        return res;
    }

    @Override
    public Post toPost(PostDocument postDocument) {
        return new Post(postDocument.getIdentifier(), postDocument.getOwner(), postDocument.getContent());
    }
}
