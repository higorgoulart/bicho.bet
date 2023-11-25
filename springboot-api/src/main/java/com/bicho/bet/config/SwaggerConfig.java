package com.bicho.bet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.spi.service.contexts.SecurityContext;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(apiInfo())
                .directModelSubstitute(LocalDate.class, Date.class)
                .directModelSubstitute(LocalDateTime.class, java.util.Date.class)
                .securitySchemes(Arrays.asList(apiKey()))
                .securityContexts(Arrays.asList(securityContext()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("bicho.bet")
                .description("Documentação completa")
                .version("1.0")
                .build();
    }

    private springfox.documentation.spi.service.contexts.SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    List<SecurityReference> defaultAuth() {
        return Arrays.asList(new SecurityReference("BearerToken", new springfox.documentation.service.AuthorizationScope[0]));
    }

    private springfox.documentation.service.ApiKey apiKey() {
        return new springfox.documentation.service.ApiKey("BearerToken", "Authorization", "header");
    }
}
