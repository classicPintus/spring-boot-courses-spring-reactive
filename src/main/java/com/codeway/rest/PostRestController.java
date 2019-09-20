package com.codeway.rest;

import com.codeway.rest.model.PostModel;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface PostRestController {

    Mono<ResponseEntity<PostModel>> getPost(String id);
}
