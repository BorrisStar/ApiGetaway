package com.example.apigetaway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
public class LoggingGlobalFiltersConfigurations {

    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> {
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        var response = exchange.getResponse();
                        log.info("\nGlobal Post Filter executed. \nResponse headers: {}.\nStatus code: {}",
                                response.getHeaders(),
                                response.getStatusCode());
                    }));
        };
    }
}
