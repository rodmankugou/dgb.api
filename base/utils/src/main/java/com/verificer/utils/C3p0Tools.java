package com.verificer.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyVetoException;

public class C3p0Tools {
    private static Logger LOGGER = LoggerFactory.getLogger(C3p0Tools.class);
    private static String driverClass = JavaPropertiesUtil.getValue("properties/mysql.properties", "mysql.driverClass");
//    private static String jdbcUrl = JavaPropertiesUtil.getValue("properties/mysql.properties", "mysql.jdbcUrl");
    private static String jdbcUrl = "jdbc:mysql://localhost:3306/@db?serverTimezone=UTC";
    private static String user = JavaPropertiesUtil.getValue("properties/mysql.properties", "mysql.user");
    private static String passwd = JavaPropertiesUtil.getValue("properties/mysql.properties", "mysql.password");
    private static int poolSize = 1;
    private static int maxPoolSize = 1;

    private static ComboPooledDataSource dataSource ;
    private static String DF_DB = "dbg";

    public static  ComboPooledDataSource getInstance(String db){

        return createDataSource(db);
    }

    public static  ComboPooledDataSource getInstance(){
        return getInstance(DF_DB);
    }

    private static ComboPooledDataSource createDataSource(String db) {
        dataSource = new ComboPooledDataSource();

        try {
            dataSource.setDriverClass(driverClass);

        } catch (PropertyVetoException e) {
            LOGGER.error("C3PO配置驱动类时，报错：{}", e);
            throw new RuntimeException(e.getMessage(), e);
        }

        String jUrl = jdbcUrl.replaceAll("@db",db);
        dataSource.setJdbcUrl(jUrl);

        dataSource.setUser(user);

        dataSource.setPassword(passwd);

        dataSource.setAcquireIncrement(5);

        dataSource.setInitialPoolSize(poolSize);

        dataSource.setMaxPoolSize(maxPoolSize);

        return dataSource;
    }
}
