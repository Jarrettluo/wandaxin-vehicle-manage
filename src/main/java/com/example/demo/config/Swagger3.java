package com.example.demo.config;

import springfox.documentation.service.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author Jarrett Luo
 * @Date 2021/3/6 22:15
 * @Version 1.0
 */
@Configuration
public class Swagger3 {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30).apiInfo(
                new ApiInfoBuilder()
                        .contact(new Contact("Jarrett", "", "luojiarui2@hirain.com"))
                        .title("wan-da-xin测试项目")
                        .build()
        );
    }
}