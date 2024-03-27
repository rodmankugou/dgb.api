package com.verificer.biz.biz.service.cache.gache;

import com.verificer.biz.biz.service.cache.vo.CacGoods;
import com.verificer.biz.biz.service.cache.vo.CacShop;
import com.verificer.biz.biz.service.cache.vo.CacStage;
import com.verificer.biz.biz.service.cache.vo.EffMerSet;

import java.util.List;

public interface ICacheLoader {

    /**
     * 获取有效的店铺列表及其库存信息
     * @return
     */
    EffMerSet loadEffMers();

    /**
     * 获取有效的商品列表及其规格信息
     * @return
     */
    List<CacGoods> loadEffGoods();
}
