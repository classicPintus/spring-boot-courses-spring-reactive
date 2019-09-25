package com.codeway.reactive;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FrancoTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void shouldCallCodeway() {
        webTestClient
                .get().uri("/posts-http")
                .exchange()
                .expectStatus().isOk();

    }
}
