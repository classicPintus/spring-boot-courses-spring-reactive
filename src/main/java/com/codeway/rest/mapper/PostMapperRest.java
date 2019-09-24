package com.codeway.rest.mapper;

import com.codeway.domain.PostDomain;
import com.codeway.rest.model.PostModel;

public class PostMapperRest implements PostMapper {

    @Override
    public PostDomain toDomainObject(PostModel restObject) {
        return new PostDomain(
                restObject.getDomainIdentifier(),
                restObject.getOwner(),
                restObject.getContent()
        );
    }

    @Override
    public PostModel toRestObject(PostDomain domainObject) {
        return new PostModel(
                domainObject.getOwner(),
                domainObject.getContent(),
                domainObject.getIdentifier()
        );
    }
}
