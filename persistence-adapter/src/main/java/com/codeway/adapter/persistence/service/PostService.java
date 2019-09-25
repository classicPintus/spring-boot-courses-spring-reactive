package com.codeway.adapter.persistence.service;

import com.codeway.adapter.persistence.document.PostDocument;
import com.codeway.adapter.persistence.exception.DocumentNotFoundException;
import com.codeway.domain.PostDomain;
import com.codeway.domain.port.PostPersistencePort;
import com.codeway.adapter.persistence.mapper.PostDocumentMapper;
import com.codeway.adapter.persistence.repository.PostRepository;

import java.util.Optional;

public class PostService implements PostPersistencePort {

    private final PostRepository postRepository;
    private final PostDocumentMapper postDocumentMapper;

    public PostService(PostRepository postRepository,
                       PostDocumentMapper postDocumentMapper) {
        this.postRepository = postRepository;
        this.postDocumentMapper = postDocumentMapper;
    }

    @Override
    public PostDomain findByIdentifier(String identifier) {
        PostDocument document = postRepository.findByIdentifier(identifier).orElseThrow(DocumentNotFoundException::new);
        return postDocumentMapper.toDomainObject(document);
    }

    @Override
    public PostDomain create(PostDomain postDomain) {
        return postDocumentMapper.toDomainObject(postRepository.save(postDocumentMapper.toEntity(postDomain)));
    }
}
