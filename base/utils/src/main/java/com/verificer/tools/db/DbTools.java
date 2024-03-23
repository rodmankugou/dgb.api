package com.verificer.tools.db;

import com.verificer.utils.C3p0Tools;
import com.verificer.utils.DbUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

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
}
