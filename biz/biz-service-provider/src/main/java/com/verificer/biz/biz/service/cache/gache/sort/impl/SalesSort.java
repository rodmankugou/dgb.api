package com.verificer.biz.biz.service.cache.gache.sort.impl;

import com.verificer.biz.biz.service.cache.gache.sort.BaseSort;
import com.verificer.biz.biz.service.cache.vo.CacGoods;
import com.verificer.biz.biz.service.cache.vo.SortableGoods;

import java.util.Comparator;

public class SalesSort extends BaseSort {
    private  static Comparator<SortableGoods> comparator = new Comparator<SortableGoods>(){
        @Override
        public int compare(SortableGoods o1, SortableGoods o2) {

            return o2.getSaleCount().compareTo(o1.getSaleCount());
        }
    };

    
    public SalesSort() {
        super(comparator);
    }
}
