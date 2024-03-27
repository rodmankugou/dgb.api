package com.verificer.biz.biz.service.cache.gache.filter;

import com.verificer.biz.biz.service.cache.vo.CacGoods;
import com.verificer.biz.biz.service.cache.vo.SortableGoods;

import java.util.LinkedList;
import java.util.List;

public class IGFilters implements IGFilter {
    private List<IGFilter> filters;


    public IGFilters() {
        this.filters = new LinkedList<>();
    }

    public IGFilters(List<IGFilter> filters) {
        if(filters == null || filters.size() == 0)
            throw new RuntimeException("Parameter filters can not be empty");

        this.filters = filters;
    }

    public void add(IGFilter filter){
        this.filters.add(filter);
    }

    @Override
    public boolean isMatch(CacGoods goods) {
        for(IGFilter f : filters){
            if(!f.isMatch(goods))
                return false;
        }
        return true;
    }

    @Override
    public boolean isMatch(SortableGoods goods) {
        for(IGFilter f : filters){
            if(!f.isMatch(goods))
                return false;
        }
        return true;
    }
}
