package com.verificer.biz.biz.service.cache.gache.sort;

import com.verificer.base.sup.itf.BaseSupService;
import com.verificer.base.sup.itf.CfgCodes;
import com.verificer.biz.biz.service.cache.gache.sort.impl.DistanceSort;
import com.verificer.biz.biz.service.cache.gache.sort.impl.NonMemberPriceSort;
import com.verificer.biz.biz.service.cache.gache.sort.impl.PriceSort;
import com.verificer.biz.biz.service.cache.gache.sort.impl.SalesSort;
import com.verificer.biz.biz.service.cache.gache.sort.impl.mult.MulSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


//TODO 此处有些 排序器可以单例话
@Component
public class SortBuilder {
    @Autowired
    BaseSupService baseSupService;


    public ISort priceSort(boolean isMember){
        if(isMember )
            return new PriceSort();
        else
            return new NonMemberPriceSort();
    }



    public SalesSort salesSort(){
        return  new SalesSort();
    }

    public DistanceSort distanceSort(){
        return new DistanceSort();
    }


    public MulSort mulSort(boolean isMember){
        Integer priceWeight = Integer.parseInt(baseSupService.getCfg(CfgCodes.G_SORT_PRICE_WEIGHT));
        Integer salesWeight = Integer.parseInt(baseSupService.getCfg(CfgCodes.G_SORT_SALES_WEIGHT));
        Integer distanceWeight = Integer.parseInt(baseSupService.getCfg(CfgCodes.G_SORT_DISTANCE_WEIGHT));
        Integer shopWeight = Integer.parseInt(baseSupService.getCfg(CfgCodes.G_SORT_SHOP_WEIGHT));
        return  new MulSort(this,isMember,priceWeight,salesWeight,distanceWeight,shopWeight);
    }

}
