package com.codeway.rest.mapper;

public interface RestMapper<REST, DOMAIN> {
    DOMAIN toDomainObject(REST restObject);
    REST toRestObject(DOMAIN domainObject);
}
