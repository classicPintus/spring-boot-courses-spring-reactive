package com.wideside.reactive;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class GreetingRestController {

    @GetMapping(path = "/hello")
    public Mono<ResponseEntity<String>> hello() {
        return Mono.just(new ResponseEntity<>("Hello, Spring!", HttpStatus.OK));
    }

}
