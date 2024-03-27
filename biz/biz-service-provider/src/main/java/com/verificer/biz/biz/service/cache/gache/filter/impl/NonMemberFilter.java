package com.verificer.biz.biz.service.cache.gache.filter.impl;

import com.verificer.biz.biz.service.cache.gache.filter.IGFilter;
import com.verificer.biz.biz.service.cache.vo.CacGoods;
import com.verificer.biz.biz.service.cache.vo.SortableGoods;

public class NonMemberFilter implements IGFilter {
    @Override
    public boolean isMatch(CacGoods goods) {

        return goods.getNonMemberBuyFlag();
    }

    @Override
    public boolean isMatch(SortableGoods goods) {
        return true;
    }
}
