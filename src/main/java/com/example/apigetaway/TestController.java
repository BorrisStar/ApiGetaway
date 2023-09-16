package com.example.apigetaway;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@RestController
public class TestController {

    @GetMapping("test")
    public Mono<String> getTestDto() {

        return Mono.just("Test");
    }
}
