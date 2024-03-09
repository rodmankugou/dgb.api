package com.verificer.exchange.admin.unittest;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.verificer.utils.*;
import com.verificer.web.common.response.Response;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tools {
    private static String baseUrl = "http://localhost:9000/admin_api/";
    public static String TEST_DATA_PATH = "/Users/liujinhua/dev/workspace/dgb.api/apis/admin-api/src/main/java/com/verificer/exchange/admin/unittest/data/";

    /**
     * https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/3.png
     */
    public static String getRandomImg(){
        int random =  RandomUtils.getInt(3,7);
        return "https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/"+random+".png";
    }

    public static String getToken(){
        return "32594373cb6d43069b7cd038970d2a59xGRHM8p1tYQKL3MNXbPk4O0z914jD1Q6-1709956683763";
    }

    public static TResp callApi(String url,String json){
        Map<String,String> headerMap = new HashMap<>();
        headerMap.put("token",getToken());
        String s = HttpClientUtils.sendHttpPostJson(baseUrl+url,headerMap,json);
        TResp resp = null;
        try {
            resp = FastJson.fromJson(s, TResp.class);
        } catch (Exception e) {
            throw new RuntimeException("调用接口失败，返回内容:\n"+s);
        }
        if(resp.getCode() == null)
            throw new RuntimeException("调用接口失败，返回内容:\n"+s);
        System.out.println("调用成功，接口返回内容：\n"+s);
        return resp;
    }

    private static Connection getConn() throws Exception{
        ComboPooledDataSource mysql = C3p0Tools.getInstance();


        Connection conn = mysql.getConnection();
        conn.setAutoCommit(true);
        return conn;
    }

    private static void executeSql(Connection conn,String sql) throws Exception{
        Statement stm = null;
        ResultSet rs = null;

        try {
            stm = conn.createStatement();
            stm.executeUpdate(sql);

        }finally {
            DbUtil.closeConnection(null,stm,rs);
        }
    }

    public static void init(){
        List<String> sqls = null;
        try {
             sqls = FileUtils.readLines(new File(TEST_DATA_PATH+"/init.sql"));
        } catch (IOException e) {
            throw new RuntimeException("加载sql脚本失败");
        }

        Connection conn = null;

        try {
            conn = getConn();
            conn.setAutoCommit(false);
            for(String sql : sqls){
                System.out.println("执行sql: "+sql);

                if(SStringUtils.isEmpty(sql))
                    continue;
                executeSql(conn,sql);
            }
            conn.commit();
        } catch (Exception e) {
            try {
                if(conn != null)
                    conn.rollback();
            } catch (SQLException t) {
                throw new RuntimeException(t.getMessage(),t);
            }
            throw new RuntimeException(e.getMessage(),e);

        }finally {
            DbUtil.closeConnection(conn,null,null);
        }

    }

}
