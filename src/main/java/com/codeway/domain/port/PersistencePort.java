package com.codeway.domain.port;

public interface PersistencePort<DOMAIN, KEY> {
    DOMAIN create(DOMAIN domainObject);
    DOMAIN read(KEY key);
    DOMAIN update(DOMAIN domainObject);
    void delete(DOMAIN domainObject);
}
