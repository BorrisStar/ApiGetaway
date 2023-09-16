
package com.example.apigetaway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebFluxSecurity
@Configuration(proxyBeanMethods = false)
public class ResourceServerConfig {

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchanges ->
                        exchanges
                                .pathMatchers("/**").permitAll()
//                                .pathMatchers("/api/v1/webflux/r2dbc/**").hasAuthority("SCOPE_message:read")
                                .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2ResourceServer ->
                        oauth2ResourceServer
                                .jwt(withDefaults())
                );
        return http.build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        return ReactiveJwtDecoders.fromIssuerLocation("http://localhost:9000");
    }

}
