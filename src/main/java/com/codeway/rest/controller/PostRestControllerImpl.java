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

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class PostRestControllerImpl implements PostRestController {

    private PostPersistencePort postService;
    private PostPersistencePort inMemoryPostService;
    private PostMapper postMapper;

    public PostRestControllerImpl(PostPersistencePort postService,
                                  PostPersistencePort inMemoryPostService,
                                  PostMapper postMapper) {
        this.postService = postService;
        this.inMemoryPostService = inMemoryPostService;
        this.postMapper = postMapper;
    }

    @GetMapping(path = "/posts/{identifier}")
    public ResponseEntity<PostModel> getPost(@PathVariable("identifier") String identifier) {
        return ResponseEntity.ok(postMapper.toRestObject(postService.findByIdentifier(identifier)));
    }

    @GetMapping(path = "/posts/{identifier}", headers = "type=STUB")
    public ResponseEntity<PostModel> getPostStub(@PathVariable("identifier") String identifier) {
        return ResponseEntity.ok(postMapper.toRestObject(inMemoryPostService.findByIdentifier(identifier)));
    }

    @GetMapping(path = "/posts-without-mapping/{identifier}")
    public ResponseEntity<PostDomain> getPostWithoutMapping(@PathVariable("identifier") String identifier) {
        return ResponseEntity.ok(postService.findByIdentifier(identifier));
    }

    @GetMapping(path = "/posts-only-memory")
    public ResponseEntity<Void> getPostOnlyMemory() {
        Map<String, Instant> m = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            int value = 10000 - i + i * new Random().nextInt();
            String key = String.valueOf(value);
            m.put(key, Instant.now());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/posts")
    @Override
    public ResponseEntity<PostModel> addPost() {
        PostDomain postDomain = new PostDomain(
            RandomStringUtils.randomAlphanumeric(5),
            RandomStringUtils.randomAlphanumeric(6),
            RandomStringUtils.randomAlphanumeric(7));

        return ResponseEntity.status(HttpStatus.CREATED).body(postMapper.toRestObject(postService.create(postDomain)));
    }

    @PostMapping(path = "/posts", headers = "type=STUB")
    public ResponseEntity<PostModel> addPostStub() {
        PostDomain postDomain = new PostDomain(
            RandomStringUtils.randomAlphanumeric(5),
            RandomStringUtils.randomAlphanumeric(6),
            RandomStringUtils.randomAlphanumeric(7));

        return ResponseEntity.status(HttpStatus.CREATED).body(postMapper.toRestObject(inMemoryPostService.create(postDomain)));
    }

}
