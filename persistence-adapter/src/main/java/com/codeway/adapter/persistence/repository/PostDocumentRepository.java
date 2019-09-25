package com.codeway.adapter.persistence.repository;

import com.codeway.adapter.persistence.document.PostDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PostDocumentRepository extends MongoRepository<PostDocument, String>,PostRepository  {
    @Override
    Optional<PostDocument> findByIdentifier(String identifier);
}
