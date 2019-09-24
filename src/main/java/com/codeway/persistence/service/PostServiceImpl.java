package com.codeway.persistence.service;

import com.codeway.domain.PostDomain;
import com.codeway.persistence.document.PostDocument;
import com.codeway.persistence.mapper.PostDocumentMapper;
import com.codeway.persistence.repository.PostDocumentRepository;

public class PostServiceImpl extends PersistenceService<PostDomain, String, PostDocument> implements PostService {

    private final PostDocumentRepository postDocumentRepository;
    private final PostDocumentMapper postDocumentMapper;

    public PostServiceImpl(PostDocumentRepository postDocumentRepository,
                           PostDocumentMapper postDocumentMapper) {
        super(postDocumentRepository, postDocumentMapper);
        this.postDocumentRepository = postDocumentRepository;
        this.postDocumentMapper = postDocumentMapper;
    }

    @Override
    public PostDomain findByIdentifier(String identifier) {
        return postDocumentRepository.findByIdentifier(identifier)
                .map(postDocumentMapper::toDomainObject)
                .orElse(null);
    }
}
