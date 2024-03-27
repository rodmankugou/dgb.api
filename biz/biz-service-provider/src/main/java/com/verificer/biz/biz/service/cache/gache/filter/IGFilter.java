package com.verificer.biz.biz.service.cache.gache.filter;

import com.verificer.biz.biz.service.cache.vo.CacGoods;

public interface IGFilter {
    boolean isMatch(CacGoods goods);
}
