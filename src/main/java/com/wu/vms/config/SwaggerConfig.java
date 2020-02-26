package com.wu.vms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author ：wuba
 * @date ：Created in 2019/11/17 17:21
 * @description：swagger配置类
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wu.vms.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("导师双选系统接口文档")
                        .version("1.0.0")
                        .contact(new Contact("wuba","http://wubazx.online","wmnihaoya@qq.com"))
                        .license("The Apache License")
                        .licenseUrl("http://httpd.apache.org/docs/2.4/en/license.html")
                        .build());
    }
}
