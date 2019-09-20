package com.codeway.domain.port;

import com.codeway.domain.Post;

public interface PostPersistencePort {

     void create(Post post);

     Post read(String domainIdentifier);
}
