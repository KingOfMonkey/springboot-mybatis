package com.sailing.springbootmybatis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author baibing
 * @project: springboot-mybatis
 * @package: com.sailing.springbootmybatis.config
 * @Description: swagger2配置类
 * @date 2018/9/25 15：35
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sailing.springbootmybatis.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("SPRING-BOOT整合MYBATIS--API说明文档")
                .description("2018-8完成版本")
                .contact("Sailing西安研发中心-baibing")
                .version("1.0")
                .license("署名-非商用-相同方式共享 4.0转载请保留原文链接及作者")
                .licenseUrl("https://creativecommons.org/licenses/by-nc-sa/4.0/")
                .build();
    }
}
