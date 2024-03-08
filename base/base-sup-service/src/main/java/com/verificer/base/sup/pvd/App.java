package com.verificer.base.sup.pvd;

import com.verificer.GlobalConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import java.util.Date;

/**
 * Hello world!
 *
 */
@Configuration
@PropertySource("classpath:properties/mysql.properties")
@MapperScan("com.verificer.base.sup.pvd.mapper")
@ImportResource(locations= {"classpath:ApplicationContext-dubbo.xml","classpath:ApplicationContext-redis.xml","classpath:ApplicationContext-beans.xml"})
@SpringBootApplication
public class App implements ApplicationRunner
{
    public static void main(String[] args) {
        //确认配置加载正常
        GlobalConfig.init();
        new SpringApplicationBuilder(App.class)
                .web(WebApplicationType.NONE) // .REACTIVE, .SERVLET
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        while(true) {
            Thread.sleep(30000);
        }
    }

}
