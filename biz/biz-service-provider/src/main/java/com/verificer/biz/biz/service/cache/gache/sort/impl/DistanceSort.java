package com.verificer.biz.biz.service.cache.gache.sort.impl;

import com.verificer.biz.biz.service.cache.gache.sort.BaseSort;
import com.verificer.biz.biz.service.cache.vo.CacGoods;
import com.verificer.biz.biz.service.cache.vo.SortableGoods;

import java.util.Comparator;

public class DistanceSort extends BaseSort {
    private  static Comparator<SortableGoods> comparator = new Comparator<SortableGoods>(){
        @Override
        public int compare(SortableGoods o1, SortableGoods o2) {

            return DistanceSort.compare(o1,o2);
        }
    };


    public DistanceSort() {
        super(comparator);
    }


    public static int compare(SortableGoods g1, SortableGoods g2){
        Long d1 = g1.getDistance() == null ? Long.MAX_VALUE : g1.getDistance();
        Long d2 = g2.getDistance() == null ? Long.MAX_VALUE : g2.getDistance();

        return d1.compareTo(d2);

    }
}
