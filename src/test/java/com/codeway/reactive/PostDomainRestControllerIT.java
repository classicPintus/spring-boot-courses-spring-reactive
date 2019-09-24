package com.codeway.reactive;

import com.codeway.domain.PostDomain;
import com.codeway.domain.port.PostPersistencePort;
import com.codeway.persistence.exception.DocumentNotFoundException;
import com.codeway.rest.mapper.PostMapper;
import com.codeway.rest.model.PostModel;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@WebFluxTest
public class PostDomainRestControllerIT {

    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private PostPersistencePort postPersistencePort;
    @MockBean
    private PostMapper postMapper;

    @Test
    public void shouldReturnPost() {
        PostDomain p = new PostDomain("123", "234", "345");
        BDDMockito.given(postPersistencePort.read("123")).willReturn(Mono.just(p));

        PostModel pm = new PostModel("000", "111", "222");
        BDDMockito.given(postMapper.toRestObject(Mockito.eq(p))).willReturn(pm);

        webTestClient
                // Create a GET request to test an endpoint
                .get().uri("/posts/123")
                .accept(MediaType.APPLICATION_PROBLEM_JSON_UTF8)
                .exchange()
                // and use the dedicated DSL to test assertions against the response
                .expectStatus().isOk()
                .expectBody(PostModel.class)
                .consumeWith(response -> {
                    PostModel responseBody = response.getResponseBody();
                    Assertions.assertThat(responseBody.getOwner()).isEqualTo("000");
                    Assertions.assertThat(responseBody.getContent()).isEqualTo("111");
                    Assertions.assertThat(responseBody.getDomainIdentifier()).isEqualTo("222");
                });
    }

    @Test
    public void shouldBeNotFoundBecauseThePostIsNotThere() {
        PostDomain p = new PostDomain("123", "234", "345");
        BDDMockito.given(postPersistencePort.read("123")).willThrow(new DocumentNotFoundException());

        webTestClient
                // Create a GET request to test an endpoint
                .get().uri("/posts/123")
                .accept(MediaType.APPLICATION_PROBLEM_JSON_UTF8)
                .exchange()
                // and use the dedicated DSL to test assertions against the response
                .expectStatus().isNotFound()
        ;
    }

    @Test
    public void shouldSaveNewPost() {
        PostDomain p = new PostDomain("123", "234", "345");
        BDDMockito.given(postPersistencePort.create(Mockito.any(PostDomain.class))).willReturn(Mono.just(p));

        PostModel pm = new PostModel("000", "111", "222");
        BDDMockito.given(postMapper.toRestObject(Mockito.eq(p))).willReturn(pm);

        webTestClient
                // Create a GET request to test an endpoint
                .post().uri("/posts")
                .accept(MediaType.APPLICATION_PROBLEM_JSON_UTF8)
                .exchange()
                // and use the dedicated DSL to test assertions against the response
                .expectStatus().isCreated();


    }


}
