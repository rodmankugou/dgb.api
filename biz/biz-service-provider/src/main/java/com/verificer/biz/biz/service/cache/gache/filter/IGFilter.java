package com.verificer.biz.biz.service.cache.gache.filter;

import com.verificer.biz.biz.service.cache.vo.CacGoods;
import com.verificer.biz.biz.service.cache.vo.SortableGoods;

public interface IGFilter {
    boolean isMatch(CacGoods goods);

    public boolean isMatch(SortableGoods goods);

}
