package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: SwaggerConfig
 * @Description: swagger配置信息
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/06/05 02:20
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 创建RestApi
     * @return  Docket
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //pathMapping() :  将servlet路径映射（如果有）添加到apis基本路径的可扩展性机制
                //.pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo"))
                .paths(PathSelectors.any())
                .build();

    }

    /**
     * Api展示的基本信息
     * @return  ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("myStudyApi文档")
                .description("myStudyApi文档--展示--测试")
                .contact(new Contact("willie", "http://www.baidu.com", "2689137906@qq.com"))
                .version("1.0")
                .license("springfox-swagger-ui")
                .licenseUrl("https://swagger.io")
                .build();
    }
}
