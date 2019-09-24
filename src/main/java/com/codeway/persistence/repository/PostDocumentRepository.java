package com.codeway.persistence.repository;

import com.codeway.persistence.document.PostDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PostDocumentRepository extends ReactiveMongoRepository<PostDocument, String> {
    Mono<PostDocument> findByIdentifier(String identifier);
}
