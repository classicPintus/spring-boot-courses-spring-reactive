package com.codeway.adapter.persistence.repository;

import com.codeway.adapter.persistence.document.PostDocument;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 2019/09/24
 *
 * @author Sirius
 */
public class InMemoryPostDocumentRepository implements PostRepository {

    private Map<String, PostDocument> documents = new HashMap<>();

    @Override
    public Optional<PostDocument> findByIdentifier(String identifier) {
        PostDocument value = documents.get(identifier);
        return value != null ? Optional.of(value) : Optional.empty();
    }

    @Override
    public PostDocument save(PostDocument postDocument) {
        documents.put(postDocument.getIdentifier(), postDocument);
        return postDocument;
    }
}
