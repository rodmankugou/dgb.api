package com.verificer.exchange.web.controller.shop;

import com.verificer.biz.beans.vo.shop.AShopBaseVo;
import com.verificer.biz.beans.vo.shop.AShopDtlVo;
import com.verificer.utils.reflect.SBeanUtils;

import java.math.BigDecimal;

public class ShopTool {
    private static final String shopId = "d000e3c443794213a92d61a9c6f6f6fe";

    public static AShopBaseVo getShop(){
        AShopBaseVo shop = new AShopBaseVo();
        shop.setId(shopId);
        shop.setName("后海科兴总店");
        shop.setDistance("1.3公里");
        shop.setImg("https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/7.png");
        shop.setOpTime("8:00-21:00");
        return shop;
    }

    public static AShopDtlVo shopDtl(){
        AShopDtlVo vo = new AShopDtlVo();
        SBeanUtils.copyProperties2(getShop(),vo);
        vo.setLongitude(new BigDecimal("113.88"));
        vo.setLatitude(new BigDecimal("22.36"));
        return vo;
    }
}
