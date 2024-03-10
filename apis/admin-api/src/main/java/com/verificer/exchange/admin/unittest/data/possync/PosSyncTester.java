package com.verificer.exchange.admin.unittest.data.possync;

import com.verificer.biz.beans.vo.GoodsVo;
import com.verificer.biz.beans.vo.SpecVo;
import com.verificer.biz.beans.vo.req.GoodsFormVo;
import com.verificer.exchange.admin.unittest.GoodsTools;
import com.verificer.exchange.admin.unittest.TResp;
import com.verificer.exchange.admin.unittest.Tools;
import com.verificer.utils.FastJson;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PosSyncTester {

    public static void supplyToStage(Long goodsId, Long specId, int count){
        Map<String,Object> map = new HashMap<>();
        map.put("stageId","6f22c403ffa94c9da21cce5b715c3cfe");
        map.put("goodsId",goodsId);
        map.put("specId",specId);
        map.put("count",count);
        TResp resp = Tools.callApi("/adjust/stage/supply", FastJson.toJson(map));
        if(resp.getCode() != 1)
            throw new RuntimeException("调用接口失败");

    }

    static class Item{
        Long goodsId;
        Long specId;
        long count;
    }

    public static void batchShopIn(List<Item> items){
        Map<String,Object> param = new HashMap<>();
        param.put("stageId","6f22c403ffa94c9da21cce5b715c3cfe");
        param.put("shopId","d000e3c443794213a92d61a9c6f6f6fe");
        List<Map<String,Object>> pItems = new LinkedList<>();
        for(Item item : items){
            Map<String,Object> map = new HashMap<>();
            map.put("goodsId",item.goodsId);
            map.put("specId",item.specId);
            map.put("count",item.count);
            pItems.add(map);
        }
        param.put("items",pItems);

        TResp resp = Tools.callApi("/adjust/stage/to/shop/batch", FastJson.toJson(param));
        if(resp.getCode() != 1)
            throw new RuntimeException("调用接口失败");

    }

    public static void updGoods(BigDecimal price,String name){
        List<GoodsVo> goodsVos = GoodsTools.pageGoods();
        GoodsVo goods = goodsVos.get(0);

        GoodsFormVo vo = FastJson.fromJson(FastJson.toJson(goods),GoodsFormVo.class);
        vo.getSpecList().get(0).setPrice(price);
        vo.setName(name);
        TResp resp = Tools.callApi("/goods/submit", FastJson.toJson(vo));
        if(resp.getCode() != 1)
            throw new RuntimeException("调用接口失败");
    }

    public static void disableGoods(){
        List<GoodsVo> goodsVos = GoodsTools.pageGoods();
        GoodsVo goods = goodsVos.get(1);

        Map<String,Object> param = new HashMap<>();
        param.put("id",goods.getId());
        TResp resp = Tools.callApi("/goods/rubbish", FastJson.toJson(param));
        if(resp.getCode() != 1)
            throw new RuntimeException("调用接口失败");
    }

    public static void runAdj(){
        List<GoodsVo> goodsVos = GoodsTools.pageGoods();
        List<Item> batchItems = new LinkedList<>();
        for(GoodsVo goodsVo : goodsVos){
            for(SpecVo specVo : goodsVo.getSpecList()){
                supplyToStage(goodsVo.getId(),specVo.getId(),100);
                Item item = new Item();
                item.goodsId = goodsVo.getId();
                item.specId = specVo.getId();
                item.count = 13;
                batchItems.add(item);
            }
        }

        batchShopIn(batchItems);

        updGoods(new BigDecimal(13.5),"测试-新品");
        disableGoods();
    }




    public static void runTest(){
        runAdj();
    }
}
