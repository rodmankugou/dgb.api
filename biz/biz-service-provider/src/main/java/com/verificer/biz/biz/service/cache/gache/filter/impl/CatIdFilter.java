package com.verificer.biz.biz.service.cache.gache.filter.impl;

import com.verificer.biz.biz.service.cache.gache.filter.IGFilter;
import com.verificer.biz.biz.service.cache.vo.CacGoods;
import com.verificer.biz.biz.service.cache.vo.SortableGoods;

public class CatIdFilter implements IGFilter {
    private Long catId;

    public CatIdFilter(Long catId) {
        this.catId = catId;
    }

    @Override
    public boolean isMatch(CacGoods goods) {
        return goods.getCatId().equals(catId);
    }

    @Override
    public boolean isMatch(SortableGoods goods) {
        return true;
    }
}
