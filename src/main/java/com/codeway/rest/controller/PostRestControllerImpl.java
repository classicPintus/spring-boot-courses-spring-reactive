package com.codeway.rest.controller;

import com.codeway.domain.PostDomain;
import com.codeway.domain.port.PostPersistencePort;
import com.codeway.persistence.document.PostDocument;
import com.codeway.persistence.repository.PostDocumentRepository;
import com.codeway.rest.mapper.PostMapper;
import com.codeway.rest.model.PostModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PostRestControllerImpl implements PostRestController {

    private final PostPersistencePort postPersistencePort;
    private final PostMapper postMapper;
    private final PostDocumentRepository postDocumentRepository;

    public PostRestControllerImpl(PostPersistencePort postPersistencePort,
                                  PostMapper postMapper,
                                  PostDocumentRepository postDocumentRepository) {
        this.postPersistencePort = postPersistencePort;
        this.postMapper = postMapper;
        this.postDocumentRepository = postDocumentRepository;
    }

    @GetMapping(path = "/posts/{identifier}")
    public ResponseEntity<Mono<PostModel>> getPost(@PathVariable("identifier") String identifier) {
        return ResponseEntity.ok(postPersistencePort.findByIdentifier(identifier).map(postMapper::toRestObject));
    }

    @GetMapping(path = "/posts-without-rest-mapping/{identifier}")
    public ResponseEntity<Mono<PostDomain>> getPostWithoutRestMapping(@PathVariable("identifier") String identifier) {
        return ResponseEntity.ok(postPersistencePort.findByIdentifier(identifier));
    }

    @GetMapping(path = "/posts-without-mapping/{identifier}")
    public ResponseEntity<Mono<PostDocument>> getPostWithoutMapping(@PathVariable("identifier") String identifier) {
        return ResponseEntity.ok(postDocumentRepository.findByIdentifier(identifier));
    }

    @GetMapping(path = "/posts-only-memory")
    public ResponseEntity<Mono<Void>> getPostOnlyMemory() {
        Map<String, Instant> m = new HashMap<>();
        for (int i = 0; i < 100000; i++){
            int value = 10000 - i + i * 2;
            String key = String.valueOf(value);
            m.put(key, Instant.now());
        }
        return ResponseEntity.ok(Mono.empty());
    }

    @PostMapping(path = "/posts")
    @Override
    public ResponseEntity<Mono<PostModel>> addPost() {
        PostDomain postDomain = new PostDomain(
                RandomStringUtils.randomAlphanumeric(5),
                RandomStringUtils.randomAlphanumeric(6),
                RandomStringUtils.randomAlphanumeric(7));

        return ResponseEntity.status(HttpStatus.CREATED).body(postPersistencePort.create(postDomain).map(postMapper::toRestObject));
    }

}
