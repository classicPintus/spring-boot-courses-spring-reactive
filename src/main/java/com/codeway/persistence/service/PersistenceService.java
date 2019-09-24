package com.codeway.persistence.service;

import com.codeway.domain.port.PersistencePort;
import com.codeway.persistence.exception.DocumentNotFoundException;
import com.codeway.persistence.mapper.PersistenceMapper;
import org.springframework.data.mongodb.repository.MongoRepository;

public abstract class PersistenceService<DOMAIN, KEY, ENTITY> implements PersistencePort<DOMAIN, KEY> {

    private final MongoRepository<ENTITY, KEY> jpaRepository;
    private final PersistenceMapper<ENTITY, DOMAIN> mapper;

    protected PersistenceService(MongoRepository<ENTITY, KEY> jpaRepository,
                                 PersistenceMapper<ENTITY, DOMAIN> mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    public DOMAIN create(DOMAIN domainObject) {
        return mapper.toDomainObject(jpaRepository.save(mapper.toEntity(domainObject)));
    }

    public DOMAIN read(KEY key) {
        return jpaRepository.findById(key).map(mapper::toDomainObject).orElseThrow(DocumentNotFoundException::new);
    }

    public DOMAIN update(DOMAIN domainObject) {
        return mapper.toDomainObject(jpaRepository.save(mapper.toEntity(domainObject)));
    }

    public void delete(DOMAIN domainObject) {
        jpaRepository.delete(mapper.toEntity(domainObject));
    }
}
