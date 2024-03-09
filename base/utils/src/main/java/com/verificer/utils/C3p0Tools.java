package com.verificer.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyVetoException;

public class C3p0Tools {
    private static Logger LOGGER = LoggerFactory.getLogger(C3p0Tools.class);
    private static String driverClass = JavaPropertiesUtil.getValue("properties/mysql.properties", "mysql.driverClass");
//    private static String jdbcUrl = JavaPropertiesUtil.getValue("properties/mysql.properties", "mysql.jdbcUrl");
    private static String jdbcUrl = "jdbc:mysql://localhost:3306/dbg?serverTimezone=UTC";
    private static String user = JavaPropertiesUtil.getValue("properties/mysql.properties", "mysql.user");
    private static String passwd = JavaPropertiesUtil.getValue("properties/mysql.properties", "mysql.password");
    private static int poolSize = Integer.parseInt(JavaPropertiesUtil.getValue("properties/mysql.properties", "mysql.initialPoolSize"));
    private static int maxPoolSize = Integer.parseInt(JavaPropertiesUtil.getValue("properties/mysql.properties", "mysql.maxPoolSize"));

    private static ComboPooledDataSource dataSource ;

    public static synchronized ComboPooledDataSource getInstance(){
        if(dataSource == null)
            createDataSource();
        return dataSource;
    }

    private static ComboPooledDataSource createDataSource() {
        dataSource = new ComboPooledDataSource();

        try {
            dataSource.setDriverClass(driverClass);

        } catch (PropertyVetoException e) {
            LOGGER.error("C3PO配置驱动类时，报错：{}", e);
            throw new RuntimeException(e.getMessage(), e);
        }

        dataSource.setJdbcUrl(jdbcUrl);

        dataSource.setUser(user);

        dataSource.setPassword(passwd);

        dataSource.setAcquireIncrement(5);

        dataSource.setInitialPoolSize(poolSize);

        dataSource.setMaxPoolSize(maxPoolSize);

        return dataSource;
    }
}
