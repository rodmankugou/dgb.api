package com.verificer.biz.biz.service;

import com.verificer.biz.beans.vo.CatVo;
import com.verificer.biz.beans.vo.MerStockVo;
import com.verificer.biz.beans.vo.req.StockMerQryVo;

import java.util.List;

public interface StockService {

    /**
     * 获取某个仓库/店铺所有的商品库存
     * @param qryVo
     * @return
     */
    List<MerStockVo> merStockList(StockMerQryVo qryVo);
}
