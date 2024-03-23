package com.verificer.biz.biz.service.common;

import com.verificer.ErrCode;
import com.verificer.biz.biz.entity.Shop;
import com.verificer.biz.biz.mapper.ShopMapper;
import com.verificer.common.exception.BaseException;
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

    /**
     * 获取操作员名称
     * @return
     */
    public Shop getById(String shopId){
        Shop shop = shopMapper.selectByPrimaryKey(shopId);
        return shop;
    }

    public String getName(String shopId) {
        Shop shop = shopMapper.selectByPrimaryKey(shopId);
        if (shop == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        return shop.getName();
    }
}
