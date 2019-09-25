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
    public Mono<ResponseEntity<PostModel>> getPost(@PathVariable("identifier") String identifier) {

        return postPersistencePort.findByIdentifier(identifier)
                .map(postMapper::toRestObject)
                .map(ResponseEntity::ok);
    }

    @GetMapping(path = "/posts-without-rest-mapping/{identifier}")
    public Mono<ResponseEntity<PostDomain>> getPostWithoutRestMapping(@PathVariable("identifier") String identifier) {
        return postPersistencePort.findByIdentifier(identifier).map(ResponseEntity::ok);
    }

    @GetMapping(path = "/posts-without-mapping/{identifier}")
    public Mono<ResponseEntity<PostDocument>> getPostWithoutMapping(@PathVariable("identifier") String identifier) {
        return postDocumentRepository.findByIdentifier(identifier).map(ResponseEntity::ok);
    }

    @GetMapping(path = "/posts-only-memory")
    public Mono<ResponseEntity<Void>> getPostOnlyMemory() {
        Map<String, Instant> m = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            int value = 10000 - i + i * new Random().nextInt();
            String key = String.valueOf(value);
            m.put(key, Instant.now());
        }
        return Mono.just(ResponseEntity.ok().build());
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

    @GetMapping(path = "/posts-http-blocking")
    public Mono<String> getPostHttpBlocking() {
        return Mono.fromSupplier(() -> new RestTemplate().getForObject(URI.create("http://192.168.245.157:8080/api/auth/slow"), String.class));
    }


    @PostMapping(path = "/posts")
    @Override
    public Mono<ResponseEntity<PostModel>> addPost() {
        String identifier = RandomStringUtils.randomAlphanumeric(5);

        return postPersistencePort.findByIdentifier(identifier)
                .switchIfEmpty(postPersistencePort.create(Mono.just(new PostDomain(
                        RandomStringUtils.randomAlphanumeric(5),
                        RandomStringUtils.randomAlphanumeric(6),
                        RandomStringUtils.randomAlphanumeric(7)))))
                .map(postMapper::toRestObject)
                .map(x -> ResponseEntity.status(HttpStatus.CREATED).body(x))
        ;
    }

}
