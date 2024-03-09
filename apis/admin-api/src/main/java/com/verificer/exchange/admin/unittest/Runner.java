package com.verificer.exchange.admin.unittest;

import com.alibaba.fastjson.annotation.JSONField;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.verificer.exchange.admin.unittest.order.OrderGen;
import com.verificer.utils.C3p0Tools;
import com.verificer.utils.FastJson;
import com.verificer.utils.RandomUtils;
import com.verificer.utils.SStringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Runner {

    public static class Req{
        @JSONField(name = "TAP")
        String apiPath;

        public String getApiPath() {
            return apiPath;
        }

        public void setApiPath(String apiPath) {
            this.apiPath = apiPath;
        }
    }

    public static void runPageQry(String url){

        runPageQry(url,new HashMap<>());
    }

    public static void runPageQry(String url,Map<String,Object> params){
        Map<String,Object> map = new HashMap<>();
        map.put("page",1);
        map.put("pageSize",10);
        for(String key : params.keySet()){
            map.put(key,params.get(key));
        }

        Tools.callApi(url,FastJson.toJson(map));
    }

    public static void runDel(String url,String id){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);

        Tools.callApi(url,FastJson.toJson(map));
    }

    public static void  run(String reqFilePath){
        String path = Tools.TEST_DATA_PATH+reqFilePath+".json";
        File file = new File(path);
        if(!file.exists())
            throw new RuntimeException("请求文件 "+path+" 不存在");
        String json = null;
        try {
            json = FileUtils.readFileToString(file,"utf-8");
            json = replaceParam(json);
        } catch (IOException e) {
            throw new RuntimeException("读取请求文件 "+path+" ，读取失败");
        }
        Req req = FastJson.fromJson(json,Req.class);
        Tools.callApi(req.getApiPath(),json);
    }

    private static String replaceParam(String json){
        String s = json.replaceAll("@img",Tools.getRandomImg());
        s = s.replaceAll("@100", SStringUtils.generateRandomNumSequence(100));
        s = s.replaceAll("@20", SStringUtils.generateRandomNumSequence(20));
        s = s.replaceAll("@10", SStringUtils.generateRandomNumSequence(10));
        s = s.replaceAll("@30", SStringUtils.generateRandomNumSequence(30));
        s = s.replaceAll("@40", SStringUtils.generateRandomNumSequence(40));
        s = s.replaceAll("@40", SStringUtils.generateRandomNumSequence(50));
        s = s.replaceAll("@60", SStringUtils.generateRandomNumSequence(60));
        s = s.replaceAll("@1", SStringUtils.generateRandomNumSequence(1));
        s = s.replaceAll("@2", SStringUtils.generateRandomNumSequence(2));
        s = s.replaceAll("@3", SStringUtils.generateRandomNumSequence(3));
        s = s.replaceAll("@4", SStringUtils.generateRandomNumSequence(4));
        s = s.replaceAll("@5", SStringUtils.generateRandomNumSequence(5));
        s = s.replaceAll("@6", SStringUtils.generateRandomNumSequence(6));

        return s;
    }

    public static void runTemp(){
        run("temp");
    }

    public static void main(String args[]) throws SQLException {
//        Tools.init();

//        run("shop/add_1");
//        run("shop/upd_1");
//        run("shop/del");
//        run("shop/upd_frozen");

//        run("stage/add_1");
//        run("stage/upd_1");
//
//        runPageQry("stage/page");

//        run("brand/add_1");
//        run("brand/upd_1");
//        runDel("brand/del",4+"");
//        runPageQry("brand/list");

//        run("cat/add_1");
//        run("cat/upd_1");
//        runDel("cat/del",3+"");
//        runPageQry("cat/page");

//        run("goods/add_1");
//        run("goods/add_2");
//        run("goods/upd_1");
//        run("goods/upd_sale");
//        run("goods/rubbish");
//        run("goods/recover");
//        runDel("goods/del",10+"");


//        runDel("goods/del",3+"");
//        runPageQry("goods/page");

//        runTemp();
//        Map map = new HashMap();
//        map.put("saleFlag",true);
//        runPageQry("goods/page",map);

//        OrderGen.genGoods();

//        runPageQry("order/page");
        run("order/dtl");

    }


}
