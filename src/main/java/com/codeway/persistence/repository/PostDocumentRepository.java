package com.codeway.persistence.repository;

import com.codeway.persistence.document.PostDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostDocumentRepository extends MongoRepository<PostDocument, String>,PostRepository  {
    @Override
    PostDocument findByIdentifier(String identifier);
}
