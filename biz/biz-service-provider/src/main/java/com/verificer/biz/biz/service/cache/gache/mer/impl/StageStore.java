package com.verificer.biz.biz.service.cache.gache.mer.impl;

import com.verificer.biz.biz.service.cache.gache.mer.MerMatchReqVo;
import com.verificer.biz.biz.service.cache.vo.CacMer;

import java.util.List;

public class StageStore extends BaseMerStore  {

    @Override
    boolean isShop() {
        return false;
    }

    @Override
    List<? extends CacMer> filter(MerMatchReqVo reqVo, List<? extends CacMer> merList) {
        return merList;
    }


}
