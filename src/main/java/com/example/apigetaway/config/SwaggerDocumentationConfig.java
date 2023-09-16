package com.example.apigetaway.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties.SwaggerUrl;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class SwaggerDocumentationConfig implements WebFluxConfigurer {
    /**
     * Swagger documentation
     */
    private static final String SWAGGER_YAML_LOCATION = "/swagger";
    private static final String DL_OFFER_API = "/DLOfferAPIs.yaml";
    private static final String ROUTER_TO_DL = "/apiRouterToDL.yaml";
    private static final String ROUTER_TO_BPP = "/apiRouterToBPP.yaml";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(SWAGGER_YAML_LOCATION + DL_OFFER_API).
                addResourceLocations("classpath:" + SWAGGER_YAML_LOCATION);
        registry.addResourceHandler(SWAGGER_YAML_LOCATION + ROUTER_TO_DL).
                addResourceLocations("classpath:" + SWAGGER_YAML_LOCATION);
        registry.addResourceHandler(SWAGGER_YAML_LOCATION + ROUTER_TO_BPP).
                addResourceLocations("classpath:" + SWAGGER_YAML_LOCATION);
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("springshop-public")
                .pathsToMatch("/public/**")
                .build();
    }

    @Bean
    public Set<SwaggerUrl> apis(SwaggerUiConfigProperties swaggerUiConfig) {
        Set<SwaggerUrl> swaggerUrlSet = new HashSet<>();
        SwaggerUrl wsResource = new SwaggerUrl("DL1", SWAGGER_YAML_LOCATION + DL_OFFER_API, "DL Offer API");
        swaggerUrlSet.add(wsResource);
        wsResource = new SwaggerUrl("DL2", SWAGGER_YAML_LOCATION + ROUTER_TO_DL, "Router to DL Connect API");
        swaggerUrlSet.add(wsResource);
        wsResource = new SwaggerUrl("DL3", SWAGGER_YAML_LOCATION + ROUTER_TO_BPP, "Router to BPP API");
        swaggerUrlSet.add(wsResource);
        swaggerUiConfig.setUrls(swaggerUrlSet);
        return swaggerUrlSet;
    }
}
