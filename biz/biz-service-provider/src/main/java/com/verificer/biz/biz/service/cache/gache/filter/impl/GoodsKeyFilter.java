package com.verificer.biz.biz.service.cache.gache.filter.impl;

import com.verificer.biz.biz.service.cache.gache.filter.IGFilter;
import com.verificer.biz.biz.service.cache.vo.CacGoods;
import com.verificer.biz.biz.service.cache.vo.SortableGoods;

public class GoodsKeyFilter implements IGFilter {
    private String sKey;

    public GoodsKeyFilter(String sKey) {
        this.sKey = sKey;
    }

    @Override
    public boolean isMatch(CacGoods goods) {

        return goods.getsKey().contains(sKey);
    }

    @Override
    public boolean isMatch(SortableGoods goods) {
        return true;
    }
}
