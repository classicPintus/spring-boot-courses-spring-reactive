package com.codeway.persistence.repository;

import com.codeway.persistence.document.PostDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface PostDocumentRepository extends MongoRepository<PostDocument, String>  {
    Optional<PostDocument> findByIdentifier(String identifier);
}
