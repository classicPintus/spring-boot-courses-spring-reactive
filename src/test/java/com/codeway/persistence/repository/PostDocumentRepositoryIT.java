package com.codeway.persistence.repository;

import com.codeway.persistence.document.PostDocument;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@DataMongoTest
public class PostDocumentRepositoryIT {

    @Autowired
    private PostDocumentRepository target;

    @Test
    public void shouldFindAllValues() {
        Mono<Long> rowsNumber = target.findAll().count();

        StepVerifier.create(rowsNumber)
                .expectNext(0L)
                .expectComplete()
                .verify();
    }

    @Test
    public void shouldSaveNewPost() {
        PostDocument pd = new PostDocument();
        pd.setIdentifier("123");
        pd.setContent("234");
        pd.setOwner("345");
        Mono<PostDocument> postDocumentObservable = target.save(pd);

        StepVerifier.create(postDocumentObservable)
                .assertNext(postDocument -> {
                    Assertions.assertThat(postDocument.getId()).isNotNull();
                    Assertions.assertThat(postDocument.getIdentifier()).isEqualTo("123");
                })
                .expectComplete()
                .verify();
    }

    @Test
    public void shouldSaveMorePosts() {
        {
            PostDocument pd = new PostDocument();
            pd.setIdentifier("123");
            pd.setContent("234");
            pd.setOwner("345");
            target.save(pd).block();
        }

        {
            PostDocument pd = new PostDocument();
            pd.setIdentifier("124");
            pd.setContent("234");
            pd.setOwner("345");
            target.save(pd).block();
        }

        PostDocument expectedPostDocument = new PostDocument();
        expectedPostDocument.setIdentifier("123");

        Flux<PostDocument> all = target.findAll();
        StepVerifier.create(all)
                .expectNextCount(2)
                .expectComplete()
                .verify()
        ;
    }

    @After
    public void tearDown() throws Exception {
        target.deleteAll().block();
    }
}
