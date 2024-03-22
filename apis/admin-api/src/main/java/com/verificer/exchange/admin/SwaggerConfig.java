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
    public Docket createGoodsApi(){

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(enable)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.verificer.exchange.admin.controller.goods"))
                .build().groupName("goods");
    }


    @Bean
    public Docket createCodeApi(){

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(enable)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.verificer.exchange.admin.controller.code"))
                .build().groupName("code");
    }

    @Bean
    public Docket createShopApi(){

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(enable)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.verificer.exchange.admin.controller.shop"))
                .build().groupName("shop");
    }

    @Bean
    public Docket createSystemApi(){

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(enable)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.verificer.exchange.admin.controller.system"))
                .build().groupName("system");
    }

    @Bean
    public Docket createOperationApi(){

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(enable)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.verificer.exchange.admin.controller.operation"))
                .build().groupName("operation");
    }




    @Bean
    public Docket createOrderApi(){

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(enable)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.verificer.exchange.admin.controller.order"))
                .build().groupName("order");
    }

//    @Bean
//    public Docket createTestApi(){
//
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
//                .enable(enable)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.verificer.exchange.admin.controller.test"))
//                .build().groupName("test");
//    }

    @Bean
    public Docket createSettleApi(){

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(enable)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.verificer.exchange.admin.controller.settle"))
                .build().groupName("settle");
    }

    @Bean
    public Docket createDebugApi(){

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(enable)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.verificer.exchange.admin.controller.debug"))
                .build().groupName("debug");
    }

    private ApiInfo apiInfo(){

        return new ApiInfoBuilder()
                .title("NFT ADMIN API Doc")
                .description("目前共有common-通用接口、system-系统（登录等）、goods-商品、shop-店铺、code-图片验证码,order-订单,operation-运营中心 、settle-结算 ，共8组api，\n请点击上方的选择框选择不同分组的api进行查阅。")
                .version("1.0")
                .build();
    }

}