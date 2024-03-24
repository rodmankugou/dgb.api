package com.verificer.biz.biz.service;

import com.verificer.biz.beans.vo.stock.MerStockStaVo;
import com.verificer.biz.beans.vo.stock.MerStockVo;
import com.verificer.biz.beans.vo.stock.req.StockMerQryVo;

import java.util.List;

public interface StockService {

    /**
     * 库存概况
     * @param qryVo
     * @return
     */
    List<MerStockStaVo> merStockStaPage(StockMerQryVo qryVo);

    /**
     * 库存概况-count
     * @param qryVo
     * @return
     */
    int merStockStaCount(StockMerQryVo qryVo);

    /**
     * 获取某个仓库/店铺所有的商品库存
     * @param qryVo
     * @return
     */
    List<MerStockVo> merStockList(StockMerQryVo qryVo);

    /**
     * 查询某个仓库/店铺的商品库存-分页
     * @param qryVo
     * @return
     */
    List<MerStockVo>  merStockPage(StockMerQryVo qryVo);

    /**
     * 某个仓库/店铺的商品库存-条目数
     * @param qryVo
     * @return
     */
    int merStockCount(StockMerQryVo qryVo);
}
