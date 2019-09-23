package com.codeway.domain.port;

import com.codeway.domain.Post;
import reactor.core.publisher.Mono;

public interface PostPersistencePort {

     Mono<Post> create(Post post);

     Mono<Post> read(String domainIdentifier);
}
