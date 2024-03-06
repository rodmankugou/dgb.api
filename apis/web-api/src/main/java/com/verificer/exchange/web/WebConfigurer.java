package com.verificer.exchange.web;

import com.verificer.exchange.web.security.filter.XSSFilter;
import com.verificer.exchange.web.security.interceptor.CheckRoleInterceptor;
import com.verificer.exchange.web.security.interceptor.NeedLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by 35336 on 2020/12/25.
 */

@Configuration
public class WebConfigurer implements WebMvcConfigurer {


    @Autowired
    NeedLoginInterceptor needLoginInterceptor;

    @Autowired
    CheckRoleInterceptor checkRoleInterceptor;



    @Override
    public void addInterceptors(InterceptorRegistry registry) {



        registry.addInterceptor(needLoginInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/login","/**.html",
                        "/**/webjars/springfox-swagger-ui/**",
                        "/swagger-resources/**",
                        "/webjars/springfox-swagger-ui/**");

        registry.addInterceptor(checkRoleInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/login","/**.html",
                        "/**/webjars/springfox-swagger-ui/**",
                        "/swagger-resources/**",
                        "/webjars/springfox-swagger-ui/**");
    }


//    @Bean //将方法中返回的对象注入到IOC容器中
//    public FilterRegistrationBean corsFilterRegister(){
//        FilterRegistrationBean reFilter = new FilterRegistrationBean();
//        reFilter.setFilter(new CORSFilter()); //创建并注册TestFilter
//        reFilter.addUrlPatterns("/*"); //拦截的路径（对所有请求拦截）
//        reFilter.setName("corsFilter"); //拦截器的名称
//        reFilter.setOrder(1); //拦截器的执行顺序。数字越小越先执行
//        return  reFilter;
//
//    }

    @Bean //将方法中返回的对象注入到IOC容器中
    public FilterRegistrationBean xssFilterRegister(){
        XSSFilter xssFilter = new XSSFilter();
        xssFilter.setExclude("/" +
                ";/scripts/*" +
                ";/styles/*" +
                ";/images/*;//getImage" +
                ";*.html;/webjars/springfox-swagger-ui/*" +
                ";/*/webjars/springfox-swagger-ui/*" +
                ";/register" +
                ";*/register/register" +
                ";*/login/login" +
                ";*/user/security/login/pwd/modify" +
                ";*/user/security/login/pwd/forget"
        );
        FilterRegistrationBean reFilter = new FilterRegistrationBean();
        reFilter.setFilter(xssFilter); //创建并注册TestFilter
        reFilter.addUrlPatterns("/*"); //拦截的路径（对所有请求拦截）
        reFilter.setName("xssFilter"); //拦截器的名称
        reFilter.setOrder(2); //拦截器的执行顺序。数字越小越先执行
        return  reFilter;

    }

}
