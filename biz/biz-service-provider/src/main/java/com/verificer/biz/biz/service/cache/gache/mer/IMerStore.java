package com.verificer.biz.biz.service.cache.gache.mer;

import com.verificer.biz.biz.service.cache.gache.mer.impl.SpecStockMap;
import com.verificer.biz.biz.service.cache.vo.CacMer;

import java.util.List;

public interface IMerStore {

    SpecStockMap getStockMap(MerMatchReqVo reqVo);

    void refresh(List<? extends CacMer> merList);
}
