package com.verificer.tools.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class IReader<Model> {
    private String sql;
    public abstract Model read(ResultSet rs) throws SQLException;

    public IReader(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }
}
