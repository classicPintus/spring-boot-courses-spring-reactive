package com.codeway.persistence.repository;

import com.codeway.persistence.document.PostDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface PostDocumentRepository extends MongoRepository<PostDocument, String>  {
    PostDocument findByIdentifier(String identifier);
}
