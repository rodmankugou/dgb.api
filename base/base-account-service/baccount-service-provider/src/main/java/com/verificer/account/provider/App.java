package com.verificer.account.provider;

import com.verificer.utils.Signal;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private static final Log logger = LogFactory.getLog(App.class);
    private static ApplicationContext context;

    public static void main( String[] args )
    {
        context = new ClassPathXmlApplicationContext("classpath:META-INF/spring/*.xml");
        //等待系统信号
        Signal.handle();
    }
}
