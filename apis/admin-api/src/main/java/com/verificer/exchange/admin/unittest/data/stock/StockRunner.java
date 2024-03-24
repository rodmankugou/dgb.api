package com.verificer.exchange.admin.unittest.data.stock;

import com.amazonaws.services.rekognition.model.Face;
import com.verificer.exchange.admin.unittest.Runner;
import com.verificer.exchange.admin.unittest.Tools;
import com.verificer.utils.FastJson;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StockRunner {


    public static void run() throws SQLException {
//        Tools.callApi("/stock/shop/stock/sta","{}");
//        Tools.callApi("/stock/stage/stock/sta","{}");
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("relId","6f22c403ffa94c9da21cce5b715c3cfe");
        Tools.callApi("/stock/mer/stock/list", FastJson.toJson(paramMap));


    }





    public static void main(String[] args) throws SQLException {
        run();
    }
}
