package com.codeway.rest;

import com.codeway.domain.port.PostPersistencePort;
import com.codeway.rest.model.PostModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(path = "/hello/{identifier}")
    public Mono<ResponseEntity<PostModel>> getPost(@PathVariable("identifier") String id) {
        PostModel response = postMapper.map(postPersistencePort.read(id));
        return Mono.just(new ResponseEntity<>(response, HttpStatus.OK));
    }

}
