package com.codeway.adapter.persistence.repository;

import com.codeway.adapter.persistence.document.PostDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostDocumentRepository extends MongoRepository<PostDocument, String>,PostRepository  {
    @Override
    PostDocument findByIdentifier(String identifier);
}
