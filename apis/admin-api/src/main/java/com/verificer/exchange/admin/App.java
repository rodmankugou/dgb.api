package com.verificer.exchange.admin;


import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * Hello world!
 *
 */
@Configuration
@PropertySource("classpath:properties/mysql.properties")
@PropertySource("classpath:properties/systemSetting.properties")
@MapperScan("com.verificer.exchange.admin.mapper")
@ImportResource(locations= {"classpath:ApplicationContext-dubbo.xml","classpath:ApplicationContext-redis.xml","classpath:ApplicationContext-beans.xml"})
@SpringBootApplication
public class App
{
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main( String[] args )
    {
        logger.info("Version 220812");
        SpringApplication.run(App.class, args);
    }
}
