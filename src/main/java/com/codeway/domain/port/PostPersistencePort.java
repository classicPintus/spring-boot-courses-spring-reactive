package com.codeway.domain.port;

import com.codeway.domain.PostDomain;
import reactor.core.publisher.Mono;

public interface PostPersistencePort extends PersistencePort<PostDomain, String> {
    Mono<PostDomain> findByIdentifier(String key);
}
