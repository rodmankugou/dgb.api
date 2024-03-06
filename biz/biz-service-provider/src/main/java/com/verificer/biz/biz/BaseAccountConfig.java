package com.verificer.biz.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.verificer.account.provider")
@MapperScan("com.verificer.account.provider.mapper")
public class BaseAccountConfig {
}
