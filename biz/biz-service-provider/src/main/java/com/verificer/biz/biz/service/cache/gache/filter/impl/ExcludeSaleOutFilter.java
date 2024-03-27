package com.verificer.biz.biz.service.cache.gache.filter.impl;

import com.verificer.biz.biz.service.cache.gache.filter.IGFilter;
import com.verificer.biz.biz.service.cache.vo.CacGoods;
import com.verificer.biz.biz.service.cache.vo.SortableGoods;

import java.math.BigDecimal;

public class ExcludeSaleOutFilter implements IGFilter {
    @Override
    public boolean isMatch(CacGoods goods) {
        return true;
    }

    @Override
    public boolean isMatch(SortableGoods goods) {
        return goods.getMatchedStock().compareTo(BigDecimal.ZERO) > 0;
    }
}
