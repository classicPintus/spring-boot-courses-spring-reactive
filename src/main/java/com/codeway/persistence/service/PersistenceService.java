package com.codeway.persistence.service;

import com.codeway.domain.port.PersistencePort;
import com.codeway.persistence.mapper.PersistenceMapper;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public abstract class PersistenceService<DOMAIN, KEY, ENTITY> implements PersistencePort<DOMAIN, KEY> {

    private final ReactiveCrudRepository<ENTITY, KEY> jpaRepository;
    private final PersistenceMapper<ENTITY, DOMAIN> mapper;

    protected PersistenceService(ReactiveCrudRepository<ENTITY, KEY> jpaRepository,
                                 PersistenceMapper<ENTITY, DOMAIN> mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    public Mono<DOMAIN> create(Mono<DOMAIN> domainObject) {
        return domainObject
                .map(mapper::toEntity)
                .flatMap(jpaRepository::save)
                .map(mapper::toDomainObject);
    }

    public Mono<DOMAIN> read(KEY key) {
        return jpaRepository.findById(key).map(mapper::toDomainObject);
    }

    public Mono<DOMAIN> update(DOMAIN domainObject) {
        return jpaRepository.save(mapper.toEntity(domainObject)).map(mapper::toDomainObject);
    }

    public Mono<Void> delete(DOMAIN domainObject) {
        return jpaRepository.delete(mapper.toEntity(domainObject));
    }
}
