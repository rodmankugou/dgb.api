package com.verificer.biz.biz.service;

import com.verificer.biz.biz.entity.ShopGoods;

import java.util.List;

public interface ShopGoodsService {
    /**
     * 给店铺添加商品
     * @param goodsId
     * @param specId
     */
    void addGoods(String shopId,Long goodsId, Long specId);

    /**
     * 根据specId查找
     * @param specId
     * @return
     */
    List<ShopGoods> getBySpecId(Long specId);
}
