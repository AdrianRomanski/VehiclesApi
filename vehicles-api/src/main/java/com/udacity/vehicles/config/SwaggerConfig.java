package com.udacity.vehicles.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Vehicles REST API",
                "Car-Controller return a complete Car object with random price, location and model specifications \n" +
                "Custom-Car Controller allow you to create a custom Car object under some restrictions",
                "1.0",
                "https://github.com/AdrianRomanski",
                new Contact("Adrian Romanski", "https://www.linkedin.com/in/adrianromanski/", "adrianromanski@icloud.com"),
                "License of API", "www.linkedin.com/in/AdrianRomanski", Collections.emptyList());
    }

}