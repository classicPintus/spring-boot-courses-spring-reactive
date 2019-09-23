package com.codeway.persistence.service;

import com.codeway.domain.Post;
import com.codeway.domain.port.PostPersistencePort;
import com.codeway.persistence.exception.DocumentNotFoundException;
import com.codeway.persistence.mapper.PostDocumentMapper;
import com.codeway.persistence.repository.PostDocumentRepository;
import reactor.core.publisher.Mono;

public class PostServiceImpl implements PostPersistencePort {

    private final PostDocumentRepository postDocumentRepository;
    private final PostDocumentMapper postDocumentMapper;

    public PostServiceImpl(PostDocumentRepository postDocumentRepository,
                           PostDocumentMapper postDocumentMapper) {
        this.postDocumentRepository = postDocumentRepository;
        this.postDocumentMapper = postDocumentMapper;
    }

    @Override
    public Mono<Post> create(Post post) {
        return postDocumentRepository.save(this.postDocumentMapper.toPostDocument(post))
                .map(this.postDocumentMapper::toPost)
                ;
    }

    @Override
    public Mono<Post> read(String domainIdentifier) {
        return postDocumentRepository.findByIdentifier(domainIdentifier)
                .map(postDocumentMapper::toPost)
                .switchIfEmpty(Mono.error(new DocumentNotFoundException()));
    }
}
