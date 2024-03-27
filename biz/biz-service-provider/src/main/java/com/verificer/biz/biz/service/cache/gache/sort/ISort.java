package com.verificer.biz.biz.service.cache.gache.sort;


import com.verificer.biz.biz.service.cache.vo.CacGoods;
import com.verificer.biz.biz.service.cache.vo.SortableGoods;

import java.util.List;

public interface ISort {

    void sort(List<SortableGoods> list);

}
