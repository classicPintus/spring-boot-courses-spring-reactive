package com.codeway.persistence.repository;

import com.codeway.persistence.document.PostDocument;

/**
 * 2019/09/24
 *
 * @author Sirius
 */
public interface PostRepository {
    PostDocument findByIdentifier(String identifier);
    PostDocument save(PostDocument postDocument);
}

