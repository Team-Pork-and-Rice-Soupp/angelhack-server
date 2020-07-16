package com.hackday.angelhack.config;

import com.hackday.angelhack.common.constant.SecurityConst;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket commonApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build().forCodeGeneration(true)
                .securityContexts(List.of(securityContext()))
                .securitySchemes(List.of(apiKey()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("돼지국빱팀의 api 테스트")
                .description("API 테스트를 위한 swagger를 제공합니다 \n " +
                        "토큰을 생성하는 방법은 2가지 입니다. \n" +
                        "1. 로그인을 직접 수행 \n" +
                        "2. 제공되는 슈퍼 토큰을 사용 \n\n " +
                        "생성된 토큰은 우측에 Authorize에 등록하여 사용할 수 있습니다.\n\n" +
                        "슈퍼토큰 : `eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyb290IiwiZXhwIjoyNDU4NjUyNDAwfQ.OYUx7HqlC1LZo8Dnx5PH9MlO-4IAce5CJ4__KCOOOEZXpUwSmHUgGR3b8E30obVYR8gz875GmhiGgP6wpsz0Vw`")
                .contact(getContact())
                .build();
    }

    private Contact getContact() {
        return new Contact("hahava", "https://github.com/hahava", "hahava@naver.com");
    }

    private ApiKey apiKey() {
        return new ApiKey("jwt-auth", SecurityConst.HEADER_STRING, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "access");
        AuthorizationScope[] authScopes = {authorizationScope};
        return List.of(new SecurityReference("jwt-auth", authScopes));
    }

}
