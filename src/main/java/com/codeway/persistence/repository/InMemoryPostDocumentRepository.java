package com.codeway.persistence.repository;

import com.codeway.persistence.document.PostDocument;

import java.util.HashMap;
import java.util.Map;

/**
 * 2019/09/24
 *
 * @author Sirius
 */
public class InMemoryPostDocumentRepository implements PostRepository {

    private Map<String,PostDocument> documents = new HashMap<>();

    @Override
    public PostDocument findByIdentifier(String identifier) {
        return documents.get(identifier);
    }

    @Override
    public PostDocument save(PostDocument postDocument) {
        documents.put(postDocument.getIdentifier(), postDocument);
        return postDocument;
    }
}
