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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
    public ResponseEntity<PostModel> getPost(@PathVariable("identifier") String identifier) {
        return ResponseEntity.ok(postMapper.toRestObject(postPersistencePort.findByIdentifier(identifier)));
    }

    @GetMapping(path = "/posts-without-mapping/{identifier}")
    public ResponseEntity<PostDomain> getPostWithoutMapping(@PathVariable("identifier") String identifier) {
        return ResponseEntity.ok(postPersistencePort.findByIdentifier(identifier));
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

        PostDomain existingPost = postPersistencePort.findByIdentifier(postDomain.getIdentifier());
        if (existingPost == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(postMapper.toRestObject(postPersistencePort.create(postDomain)));
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(postMapper.toRestObject(existingPost));
        }
    }

    @GetMapping(path = "/posts-http-blocking")
    public ResponseEntity<String> getPostHttpBlocking() {
        return ResponseEntity.ok(new RestTemplate().getForObject(URI.create("http://192.168.245.157:8080/api/auth/slow"), String.class));
    }

    @GetMapping(path = "/posts-http")
    public Mono<ResponseEntity<String>> getPostHttp() {
        return WebClient.create()
                .get()
                .uri(URI.create("http://192.168.245.157:8080/api/auth/slow"))
                .retrieve()
                .bodyToMono(String.class)
                .map(ResponseEntity::ok);
    }


}
