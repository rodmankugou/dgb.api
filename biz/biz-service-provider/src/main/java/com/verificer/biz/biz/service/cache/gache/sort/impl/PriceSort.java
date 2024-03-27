package com.verificer.biz.biz.service.cache.gache.sort.impl;

import com.verificer.biz.biz.service.cache.gache.sort.BaseSort;
import com.verificer.biz.biz.service.cache.vo.CacGoods;
import com.verificer.biz.biz.service.cache.vo.SortableGoods;

import java.util.Comparator;

public class PriceSort extends BaseSort {
    private  static Comparator<SortableGoods> comparator = new Comparator<SortableGoods>(){
        @Override
        public int compare(SortableGoods o1, SortableGoods o2) {

            return o1.getMinPrice().compareTo(o2.getMinPrice());
        }
    };


    public PriceSort() {
        super(comparator);
    }


}
