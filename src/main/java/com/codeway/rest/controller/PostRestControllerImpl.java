package com.codeway.rest.controller;

import com.codeway.domain.PostDomain;
import com.codeway.domain.port.PostPersistencePort;
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

@RestController
public class PostRestControllerImpl implements PostRestController {

    private PostPersistencePort postPersistencePort;
    private PostMapper postMapper;

    public PostRestControllerImpl(PostPersistencePort postPersistencePort,
                                  PostMapper postMapper) {
        this.postPersistencePort = postPersistencePort;
        this.postMapper = postMapper;
    }

    @GetMapping(path = "/posts/{identifier}")
    public ResponseEntity<Mono<PostModel>> getPost(@PathVariable("identifier") String identifier) {
        return ResponseEntity.ok(postPersistencePort.read(identifier).map(postMapper::toRestObject));
    }

    @GetMapping(path = "/posts-without-mapping/{identifier}")
    public ResponseEntity<Mono<PostDomain>> getPostWithoutMapping(@PathVariable("identifier") String identifier) {
        return ResponseEntity.ok(postPersistencePort.read(identifier));
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