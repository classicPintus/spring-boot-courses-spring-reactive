package com.codeway.adapter.persistence.mapper;

public interface PersistenceMapper<E, D> {
    E toEntity(D domainObject);
    D toDomainObject(E entity);
}
