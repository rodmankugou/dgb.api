package com.verificer.biz.biz.service.cache.gache.filter.impl;

import com.verificer.biz.biz.service.cache.gache.filter.IGFilter;
import com.verificer.biz.biz.service.cache.vo.CacGoods;
import com.verificer.biz.biz.service.cache.vo.SortableGoods;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IdsFilter implements IGFilter {
    private Set<Long> ids = new HashSet<>();

    public IdsFilter(List<Long> ids) {
        this.ids.addAll(ids);
    }

    @Override
    public boolean isMatch(CacGoods goods) {
        return ids.contains(goods.getId());
    }

    @Override
    public boolean isMatch(SortableGoods goods) {
        return true;
    }

}
