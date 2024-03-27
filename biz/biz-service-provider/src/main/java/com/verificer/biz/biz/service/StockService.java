package com.verificer.biz.biz.service;

import com.verificer.biz.beans.vo.shop.ShopStockVo;
import com.verificer.biz.beans.vo.shop.req.ShopStockQryVo;
import com.verificer.biz.beans.vo.stage.StageStockVo;
import com.verificer.biz.beans.vo.stage.req.StageStockQryVo;
import com.verificer.biz.beans.vo.stock.MerStockStaVo;
import com.verificer.biz.beans.vo.stock.MerStockVo;
import com.verificer.biz.beans.vo.stock.req.StockMerQryVo;
import com.verificer.biz.biz.entity.Stock;

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

    /**
     * 店铺库页列表
     * @param qryVo
     * @return
     */
    List<ShopStockVo> shopStockPage(ShopStockQryVo qryVo);

    /**
     * 店铺库页列表-数据条目
     * @param qryVo
     * @return
     */
    int shopStockCount(ShopStockQryVo qryVo);

    /**
     * 仓库雷表-分页
     * @param qryVo
     * @return
     */
    List<StageStockVo> stageStockPage(StageStockQryVo qryVo);

    /**
     * 符合统计条件的仓库数
     * @param qryVo
     * @return
     */
    int stageStockCount(StageStockQryVo qryVo);

    /**
     * 获取所有库存
     * @return
     */
    List<Stock> getAllStock();
}
