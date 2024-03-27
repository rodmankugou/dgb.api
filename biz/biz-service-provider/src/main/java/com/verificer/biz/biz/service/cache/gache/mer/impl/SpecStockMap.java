package com.verificer.biz.biz.service.cache.gache.mer.impl;

import com.verificer.biz.biz.service.cache.vo.SpecStock;

import java.util.Map;

public class SpecStockMap {
    /**
     * id为specId
     */
    private Map<Long, SpecStock> map ;

    public SpecStockMap(Map<Long, SpecStock> map) {
        this.map = map;
    }

    public SpecStock get(Long specId){
        return this.map.get(specId);
    }
}
