package com.codeway.persistence.service;

import com.codeway.domain.Post;
import com.codeway.domain.port.PostPersistencePort;
import com.codeway.persistence.exception.DocumentNotFoundException;
import com.codeway.persistence.mapper.PostDocumentMapper;
import com.codeway.persistence.repository.PostDocumentRepository;

public class PostServiceImpl implements PostPersistencePort {

    private final PostDocumentRepository postDocumentRepository;
    private final PostDocumentMapper postDocumentMapper;

    public PostServiceImpl(PostDocumentRepository postDocumentRepository,
                           PostDocumentMapper postDocumentMapper) {
        this.postDocumentRepository = postDocumentRepository;
        this.postDocumentMapper = postDocumentMapper;
    }

    @Override
    public void create(Post post) {
        postDocumentRepository.save(this.postDocumentMapper.toPostDocument(post));
    }

    @Override
    public Post read(String domainIdentifier) {
        Post result = postDocumentRepository.findByIdentifier(domainIdentifier).map(postDocumentMapper::toPost).block();
        if (result == null) {
            throw new DocumentNotFoundException();
        }
        return result;
    }
}
