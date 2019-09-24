package com.codeway.adapter.persistence.mapper;

import com.codeway.adapter.persistence.document.PostDocument;
import com.codeway.domain.PostDomain;

public class PostDocumentMapperImpl implements PostDocumentMapper {

    @Override
    public PostDocument toEntity(PostDomain postDomain) {
        PostDocument res = new PostDocument();
        res.setIdentifier(postDomain.getIdentifier());
        res.setContent(postDomain.getContent());
        res.setOwner(postDomain.getOwner());
        return res;
    }

    @Override
    public PostDomain toDomainObject(PostDocument postDocument) {
        return new PostDomain(postDocument.getIdentifier(), postDocument.getOwner(), postDocument.getContent());
    }
}
