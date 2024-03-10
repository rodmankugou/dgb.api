package com.verificer.exchange.admin.unittest;

import com.verificer.biz.beans.vo.GoodsVo;
import com.verificer.exchange.admin.unittest.order.GoodsResp;
import com.verificer.utils.FastJson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsTools {
    public static List<GoodsVo> pageGoods(){
        Map<String,Object> map = new HashMap<>();
        map.put("page",1);
        map.put("pageSize",10);
        TResp resp = Tools.callApi("goods/page", FastJson.toJson(map));
        if(resp.getCode() != 1)
            throw new RuntimeException("获取商品失败");
        String json = FastJson.toJson(resp);
        GoodsResp gr = FastJson.fromJson(json,GoodsResp.class);
        List<GoodsVo> goodsList = gr.getData();
        if(goodsList.size() < 2)
            throw new RuntimeException("商品表中数量不足，必须要有超过2条数据");
        return goodsList;
    }
}
