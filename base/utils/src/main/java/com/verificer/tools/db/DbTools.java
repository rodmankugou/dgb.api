package com.verificer.tools.db;

import com.verificer.tools.db.entity.IPrivateKey;
import com.verificer.utils.C3p0Tools;
import com.verificer.utils.DbUtil;
import com.verificer.utils.FastJson;
import com.verificer.utils.SStringUtils;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DbTools {

    public static <Model> List<Model> read(String dbName, IReader<Model>  reader) throws SQLException {
        Connection conn = C3p0Tools.getInstance(dbName).getConnection();

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = conn.prepareStatement(reader.getSql());

            List<Model> list = new LinkedList<>();
            rs = stm.executeQuery();
            while (rs.next()){
                Model m = reader.read(rs);
                list.add(m);
            }
            return list;
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


    public static void insertSelective(String dbName,String table,Object bean) throws SQLException {
        if(bean == null)
            throw new RuntimeException("bean can not be null");

        String json = FastJson.toJson(bean);
        Map<String,Object> map = FastJson.parseMap(json,String.class,Object.class);

        String sql = "insert into " + table + " ( @columns ) values ( @values );";
        List<String> columns  = new LinkedList<>();
        List<String> values = new LinkedList<>();
        for(String key : map.keySet()){
            if(map.get(key) == null)
                continue;
            columns.add("`"+SStringUtils.camelToUnderLine(key)+"`");
            values.add("'"+map.get(key).toString()+"'");
        }

        sql = sql.replaceAll("@columns",SStringUtils.merge(columns,","));
        sql = sql.replaceAll("@values",SStringUtils.merge(values,","));

        System.out.println(sql);
        executeUpdate(dbName,sql);
    }

    public static void updateSelectiveByPrivateKey(String dbName,String table,IPrivateKey bean) throws SQLException {
        if(bean == null)
            throw new RuntimeException("bean can not be null");

        if(SStringUtils.isEmpty(bean.loadPrivateKey()))
            throw new RuntimeException("Private key can not be null");
        String json = FastJson.toJson(bean);
        Map<String,Object> map = FastJson.parseMap(json,String.class,Object.class);

        String sql = "update  " + table +   " set @filedSets where "+bean.loadPkFieldName()+" = " + bean.loadPrivateKey();
        List<String> filedSets  = new LinkedList<>();

        if(map.size() == 0)
            return;
        for(String key : map.keySet()){
            if(map.get(key) == null)
                continue;
            Object value = map.get(key);
            if(value instanceof Boolean){

                Boolean bVal = (Boolean) value;
                value = bVal ? 1:0;
            }
            filedSets.add("`"+SStringUtils.camelToUnderLine(key)+"` = '"+value.toString()+"'");

        }

        sql = sql.replaceAll("@filedSets",SStringUtils.merge(filedSets,","));

        System.out.println(sql);
        executeUpdate(dbName,sql);
    }

//    static  class E{
//        private Long id;
//        private String value;
//        private String name;
//
//        public Long getId() {
//            return id;
//        }
//
//        public void setId(Long id) {
//            this.id = id;
//        }
//
//        public String getValue() {
//            return value;
//        }
//
//        public void setValue(String value) {
//            this.value = value;
//        }
//    }
//
//    public static void main(String args[]) throws SQLException {
//        E e = new E();
//        e.setId(1L);
//        e.setValue("Hello");
//        insertSelective("dbg","e",e);
//    }


}
