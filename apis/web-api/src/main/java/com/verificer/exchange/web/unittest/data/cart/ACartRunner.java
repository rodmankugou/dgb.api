package com.verificer.exchange.web.unittest.data.cart;

import com.verificer.biz.beans.vo.cart.req.CartJoinVo;
import com.verificer.exchange.web.unittest.AppTools;
import com.verificer.utils.FastJson;

import java.math.BigDecimal;

public class ACartRunner {



    public static void main(String args[]){
        AppTools.setWxCode("2ef9035dad624958969550478fdf4de9");

//        CartJoinVo joinVo = new CartJoinVo();
//        joinVo.setShopFlag(false);
//        joinVo.setCount(2);
//        joinVo.setSpecId(51L);
//        AppTools.callApi("cart/join", FastJson.toJson(joinVo));
//        AppTools.callApi("cart/pla/list", FastJson.toJson(joinVo));


        CartJoinVo shopJoin = new CartJoinVo();
        shopJoin.setShopId(AppTools.getShopId());
        shopJoin.setShopFlag(true);
        shopJoin.setCount(3);
        shopJoin.setSpecId(51L);
        AppTools.callApi("cart/join", FastJson.toJson(shopJoin));

        AppTools.callApi("cart/shop/list", "{}");

    }
}
