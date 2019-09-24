package com.codeway.persistence.service;

import com.codeway.domain.PostDomain;
import com.codeway.persistence.document.PostDocument;
import com.codeway.persistence.mapper.PostDocumentMapper;
import com.codeway.persistence.repository.PostDocumentRepository;

public class PostServiceImpl extends PersistenceService<PostDomain, String, PostDocument> implements PostService {

    public PostServiceImpl(PostDocumentRepository postDocumentRepository,
                           PostDocumentMapper postDocumentMapper) {
        super(postDocumentRepository, postDocumentMapper);
    }

}
