package com.verificer.biz.biz.service.cache.gache;

import com.verificer.biz.biz.entity.Goods;
import com.verificer.biz.biz.service.cache.gache.filter.IGFilter;
import com.verificer.biz.biz.service.cache.vo.CacGoods;

import java.util.LinkedList;
import java.util.List;

public class GStore {

    private List<CacGoods> list = null;

    public List<CacGoods> select(IGFilter filter){
        List<CacGoods> rst = new LinkedList<>();
        for(CacGoods goods : list){
            if(filter.isMatch(goods))
                rst.add(goods);
        }
        return rst;
    }



    public void refresh(List<CacGoods> gList) {
        this.list = gList;
    }
}
