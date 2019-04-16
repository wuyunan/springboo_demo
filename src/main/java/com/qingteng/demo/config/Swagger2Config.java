package com.qingteng.demo.config;

import de.pentabyte.springfox.ApiEnumDescriptionPlugin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Configuration
@EnableSwagger2
@Import({springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class,
        ApiEnumDescriptionPlugin.class,})

public class Swagger2Config {
    @Value("${jwt.header}")
    private static String tokenHeader;

    public static final String BASE_PACKAGE = "com.qingteng.demo";

    @Value("${swagger.enable}")
    private boolean enableSwagger;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 生产环境的时候关闭 swagger 比较安全
                .enable(enableSwagger)
                //将Timestamp类型全部转为Long类型
                .directModelSubstitute(Timestamp.class, Long.class)
                //将Date类型全部转为Long类型
                .directModelSubstitute(Date.class, Long.class)
                .select()
                // 扫描接口的包路径，不要忘记改成自己的
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    @Bean
    public ApiListingScannerPlugin commonApiScanner(CachingOperationNameGenerator operationNames) {
        return new CommonApiListScannerPlugin(operationNames);
    }

    private List<SecurityContext> securityContexts() {
        AuthorizationScope[] authorizationScopes = {new AuthorizationScope("global", "accessEverything")};
        List<SecurityReference> securityReferences = Collections.singletonList(new SecurityReference("Jwt", authorizationScopes));
        SecurityContext securityContext = SecurityContext.builder().securityReferences(securityReferences).build();
        return Collections.singletonList(securityContext);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("RESTful APIs")
                .description("API 接口")
                .termsOfServiceUrl("http://swagger.io/")
                .contact(new Contact("Swagger", "127.0.0.1", "wuyunan888@126.com"))
                .version("1.0")
                .build();
    }

    private static ArrayList<? extends SecurityScheme> securitySchemes() {
        return new ArrayList<>(Collections.singletonList(new ApiKey("Jwt", "Authorization", "header")));
    }
}