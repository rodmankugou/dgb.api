package com.verificer.biz.biz.service;

import com.verificer.biz.biz.entity.Goods;
import com.verificer.biz.biz.entity.Spec;

/**
 * 同步店铺Pos机的商品分类和商品
 */
public interface PosGoodsSyncService {
    void onAddGoodsToShop(String shopId, Goods goods, Spec spec);
    void onGoodsUpdate(Goods goods);
}
