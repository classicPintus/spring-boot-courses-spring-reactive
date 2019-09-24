package com.codeway.persistence.service;

import com.codeway.domain.PostDomain;
import com.codeway.persistence.document.PostDocument;
import com.codeway.persistence.mapper.PostDocumentMapper;
import com.codeway.persistence.repository.PostDocumentRepository;
import reactor.core.publisher.Mono;

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
    public Mono<PostDomain> findByIdentifier(String key) {
        return this.postDocumentRepository.findByIdentifier(key).map(this.postDocumentMapper::toDomainObject);
    }
}
