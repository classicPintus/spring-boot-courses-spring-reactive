package com.codeway.persistence.repository;

import com.codeway.persistence.document.PostDocument;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PostDocumentRepository extends ReactiveCrudRepository<PostDocument, String> {
    Mono<PostDocument> findByIdentifier(String identifier);
}
