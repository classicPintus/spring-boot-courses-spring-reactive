package com.codeway.domain.port;

import reactor.core.publisher.Mono;

public interface PersistencePort<DOMAIN, KEY> {
    Mono<DOMAIN> create(DOMAIN domainObject);
    Mono<DOMAIN> read(KEY key);
    Mono<DOMAIN> update(DOMAIN domainObject);
    Mono<Void> delete(DOMAIN domainObject);
}
