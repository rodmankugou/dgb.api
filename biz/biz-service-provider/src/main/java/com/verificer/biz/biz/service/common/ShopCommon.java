package com.verificer.biz.biz.service.common;

import com.verificer.biz.biz.entity.Shop;
import com.verificer.biz.biz.mapper.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopCommon {
    @Autowired
    ShopMapper shopMapper;

    /**
     * 获取操作员名称
     * @return
     */
    public String getOprName(String shopId){
        Shop shop = shopMapper.selectByPrimaryKey(shopId);
        if(shop == null)
            return "";
        return shop.getName();
    }
}
