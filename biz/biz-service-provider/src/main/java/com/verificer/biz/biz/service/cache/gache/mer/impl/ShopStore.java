package com.verificer.biz.biz.service.cache.gache.mer.impl;

import com.verificer.biz.biz.service.cache.gache.mer.MerMatchReqVo;
import com.verificer.biz.biz.service.cache.vo.CacMer;
import com.verificer.biz.biz.service.cache.vo.CacShop;
import com.verificer.utils.SStringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShopStore extends BaseMerStore {

    @Override
    boolean isShop() {
        return true;
    }

    @Override
    List<? extends CacMer> filter(MerMatchReqVo reqVo,List<? extends CacMer> merList) {

        if(SStringUtils.isEmpty(reqVo.getShopId())){
            return merList;
        }

        List<CacShop> list = new ArrayList<>();
        for(CacMer mer : merList){
            CacShop shop = (CacShop) mer;
            if(mer.getId().equals(reqVo.getShopId())){
                list.add(shop);
                return list;
            }
        }
        return list;
    }


}
