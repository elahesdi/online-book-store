package com.TOSAN.onlineBookStore.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.TOSAN.onlineBookStore.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfoMetaData())
                .securitySchemes(List.of(new ApiKey("Bearer Token", "Authorization", "header")));
    }

    private ApiInfo apiInfoMetaData() {
        return new ApiInfoBuilder().title("API Documentation")
                .description("Online Book Store")
                .build();
    }
}