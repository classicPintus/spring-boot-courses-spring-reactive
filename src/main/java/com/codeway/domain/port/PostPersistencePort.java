package com.codeway.domain.port;

import com.codeway.domain.PostDomain;

public interface PostPersistencePort extends PersistencePort<PostDomain, String> {
    PostDomain findByIdentifier(String key);
}
