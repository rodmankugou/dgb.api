package com.verificer.biz.biz.service.cache.gache.sort;


import com.verificer.biz.biz.service.cache.vo.CacGoods;
import com.verificer.biz.biz.service.cache.vo.SortableGoods;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BaseSort implements ISort{

    private Comparator<SortableGoods> comparator;

    public BaseSort(Comparator<SortableGoods> comparator) {
        this.comparator = comparator;
    }



    @Override
    public void sort(List<SortableGoods> list) {
        Collections.sort(list,comparator);
    }

}
