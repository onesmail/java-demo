package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 配置 Swagger 的基本信息，如 API 的版本、标题、描述
 */
@EnableOpenApi
@Configuration
public class SwaggerConfig {

    @Value("${swagger.enabled}")
    Boolean swaggerEnabled;

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())// 用于生成API信息
                .enable(swaggerEnabled)
                .select() // select()函数返回一个ApiSelectorBuilder实例,用来控制接口被swagger做成文档
                .apis(RequestHandlerSelectors.basePackage("com")) //apis： 添加swagger接口提取范围s
                .paths(PathSelectors.any()) //匹配所有的请求路径
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API接口文档")
                .description("演示项目")
                .version("1.0")
                .build();
    }
}
