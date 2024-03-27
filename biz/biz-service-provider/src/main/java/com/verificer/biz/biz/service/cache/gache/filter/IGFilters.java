package com.verificer.biz.biz.service.cache.gache.filter;

import com.verificer.biz.biz.service.cache.vo.CacGoods;

import java.util.List;

public class IGFilters implements IGFilter {
    private List<IGFilter> filters;
    @Override
    public boolean isMatch(CacGoods goods) {
        for(IGFilter f : filters){
            if(!f.isMatch(goods))
                return false;
        }
        return true;
    }

    public IGFilters(List<IGFilter> filters) {
        if(filters == null || filters.size() == 0)
            throw new RuntimeException("Parameter filters can not be empty");

        this.filters = filters;
    }
}
