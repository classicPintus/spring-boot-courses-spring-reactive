package com.codeway.domain.port;

import com.codeway.domain.PostDomain;

public interface PostPersistencePort {
    PostDomain findByIdentifier(String key);
    PostDomain create(PostDomain postDomain);
}
