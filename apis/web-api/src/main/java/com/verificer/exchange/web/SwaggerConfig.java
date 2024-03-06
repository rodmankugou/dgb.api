package com.verificer.exchange.web;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 配置swagger接口文档
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("#{configProperties['ENABLE_SWAGGER']}")
    private boolean enable;


    @Bean
    public Docket createCmsApi(){

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(enable)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.verificer.exchange.web.controller.cms"))
                .build().groupName("cms");
    }




    private ApiInfo apiInfo(){

        return new ApiInfoBuilder()
                .title("Seven Sea  API Doc")
                .description("Please click the selection box above to select different groups of APIs for viewing.")
                .version("1.0")
                .build();
    }

}