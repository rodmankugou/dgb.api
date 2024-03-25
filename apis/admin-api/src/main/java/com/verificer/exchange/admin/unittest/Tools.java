package com.verificer.exchange.admin.unittest;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.verificer.exchange.admin.vo.LoginVo;
import com.verificer.utils.*;
import com.verificer.web.common.response.Response;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tools {
    private static String baseUrl = "http://localhost:9000/admin_api/";
//    private static String baseUrl = "http://121.37.22.89/admin_api/";
    public static String TEST_DATA_PATH = "/Users/liujinhua/dev/workspace/dgb.api/apis/admin-api/src/main/java/com/verificer/exchange/admin/unittest/data/";

    /**
     * https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/3.png
     */
    public static String getRandomImg(){
        int random =  RandomUtils.getInt(3,7);
        return "https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/"+random+".png";
    }

    private static String token = null;

    public static String getToken(){
        if(SStringUtils.isEmpty(token)) {
            token =  login();
            return token;
        }else {
            return token;
        }

    }

    private static String login(){
        Map<String,String> headerMap = new HashMap<>();
        LoginVo vo = new LoginVo();
        vo.setUsername("rodman");
        vo.setPassword("Ji$6241111");
        String s = HttpClientUtils.sendHttpPostJson(baseUrl+"/login/login",headerMap,FastJson.toJson(vo));
        TResp resp = null;
        try {
            resp = FastJson.fromJson(s, TResp.class);
        } catch (Exception e) {
            throw new RuntimeException("调用接口失败，返回内容:\n"+s);
        }
        if(resp.getCode() != 1)
            throw new RuntimeException("登录失败，返回内容:\n"+s);

        System.out.println("登录成功.....");

        return (String) resp.getData();
    }

    public static Long getStaffId(){
        return 1L;
    }

    public static String getStaffName(){
        return "Rodman";
    }

    public static String getStageId(){
        return "6f22c403ffa94c9da21cce5b715c3cfe";
    }

    public static String getShopId(){
        return "d000e3c443794213a92d61a9c6f6f6fe";
    }

    public static String getUserUid(){
        return "1";
    }

    public static Long getUserId(){
        return 1L;
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

        System.out.println("调用成功，接口返回内容：\n"+FastJson.pretty(s));
        return resp;
    }

    private static Connection getConn(String db) throws Exception{
       return C3p0Tools.getInstance(db).getConnection();
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

    public static void init(String db,String path){
        List<String> sqls = null;
        try {
            sqls = FileUtils.readLines(new File(TEST_DATA_PATH+path));
        } catch (IOException e) {
            throw new RuntimeException("加载sql脚本失败,文件路径：\n"+TEST_DATA_PATH+path);
        }

        Connection conn = null;

        try {
            conn = getConn(db);
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

    public static void init(String path){
        init(null,path);

    }

    public static void init(){
        init(null,"init.sql");

    }

    public static Long getTableMaxId(String dbName, String tableName) throws SQLException {
        Connection conn = C3p0Tools.getInstance(dbName).getConnection();
        String sql = "select max(id) as mid from "+tableName;

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = conn.prepareStatement(sql);

            rs = stm.executeQuery();
            if (rs.next()){
                return rs.getLong("mid");
            }
            throw new RuntimeException(dbName+"."+tableName+"没有数据");
        }finally {
            DbUtil.closeConnection(conn,stm,rs);
        }
    }




    public static int executeUpdate(String dbName,String sql) throws SQLException {
        Connection conn = C3p0Tools.getInstance(dbName).getConnection();

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = conn.prepareStatement(sql);

            return stm.executeUpdate();

        }finally {
            DbUtil.closeConnection(conn,stm,rs);
        }
    }

}
