package com.example.apigetaway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingGlobalPreFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest()
                .mutate()
                .header("x-jwt-assertion", "jwt")
                .build();

//       request = exchange.getRequest();
        log.info("\nGlobal Pre Filter executed. \nRequest headers: {}.\nURI: {}, path: {}",
                request.getHeaders(),
                request.getURI(),
                request.getPath());
        return chain.filter(exchange);
    }
}
