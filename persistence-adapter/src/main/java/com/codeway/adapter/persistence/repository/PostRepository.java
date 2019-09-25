package com.codeway.adapter.persistence.repository;

import com.codeway.adapter.persistence.document.PostDocument;

import java.util.Optional;

/**
 * 2019/09/24
 *
 * @author Sirius
 */
public interface PostRepository {
    Optional<PostDocument> findByIdentifier(String identifier);
    PostDocument save(PostDocument postDocument);
}

