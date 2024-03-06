package com.verificer.exchange.admin;
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
    public Docket createCommonApi(){

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(enable)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.verificer.exchange.admin.controller.common"))
                .build().groupName("common");
    }

    @Bean
    public Docket createUserApi(){

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(enable)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.verificer.exchange.admin.controller.user"))
                .build().groupName("user");
    }


    @Bean
    public Docket createProjectApi(){

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(enable)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.verificer.exchange.admin.controller.project"))
                .build().groupName("project");
    }

    @Bean
    public Docket createBankApi(){

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(enable)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.verificer.exchange.admin.controller.bank"))
                .build().groupName("bank");
    }

    @Bean
    public Docket createCompanyApi(){

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(enable)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.verificer.exchange.admin.controller.company"))
                .build().groupName("company");
    }

    @Bean
    public Docket createTokenApi(){

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(enable)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.verificer.exchange.admin.controller.token"))
                .build().groupName("token");
    }




    private ApiInfo apiInfo(){

        return new ApiInfoBuilder()
                .title("NFT ADMIN API Doc")
                .description("目前共有common-通用接口、user-用户、bank-银行、project-项目、company-企业、token-账户，共6组api，\n请点击上方的选择框选择不同分组的api进行查阅。")
                .version("1.0")
                .build();
    }

}