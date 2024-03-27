package com.verificer.biz.biz.service.cache.gache.sort.impl;

import com.verificer.biz.biz.service.cache.gache.sort.BaseSort;
import com.verificer.biz.biz.service.cache.vo.SortableGoods;

import java.util.Comparator;

public class MarketTimeSort extends BaseSort {
    private  static Comparator<SortableGoods> comparator = new Comparator<SortableGoods>(){
        @Override
        public int compare(SortableGoods o1, SortableGoods o2) {

            return o2.getMarketTime().compareTo(o1.getMarketTime());
        }
    };


    public MarketTimeSort() {
        super(comparator);
    }


}
