package com.codeway.persistence.mapper;

import com.codeway.domain.PostDomain;
import com.codeway.persistence.document.PostDocument;

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
