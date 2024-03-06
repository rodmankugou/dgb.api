package com.verificer.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库工具
 */
public class DbUtil {
    private static final Log logger = LogFactory.getLog(DbUtil.class);
    public static void closeConnection(Connection conn,Statement stm,ResultSet rs){
        try {
            if (rs != null){
                rs.close();
            }
            if (stm != null){
                stm.close();
            }
            if (conn != null){
                conn.close();
            }
        }catch (Throwable t){
            logger.error("释放数据库连接失败",t);
        }
    }

    public static boolean rollback(Connection conn){
        try {
            conn.rollback();
            return true;
        } catch (SQLException e) {
            logger.error("回滚失败",e);
            return false;
        }
    }
    public static boolean commit(Connection conn){
        try {
            conn.commit();
            return true;
        } catch (SQLException e) {
            logger.error("提交失败",e);
            return false;
        }
    }
}
