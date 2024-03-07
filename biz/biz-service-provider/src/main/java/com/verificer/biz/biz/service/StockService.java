package com.verificer.biz.biz.service;

import com.verificer.biz.beans.exceptions.StockInsufficientException;
import com.verificer.biz.beans.vo.req.StockUpdVo;

import java.util.List;

public interface StockService {
    void addStageStockIfNotExist(Long goodsId, Long specId, List<String> stageIds);

    void modifyStock(StockUpdVo updVo) throws StockInsufficientException;
    void modifyStock(List<StockUpdVo> asList) throws StockInsufficientException;

    void addShopStockIfNotExist(String shopId, Long goodsId, Long specId);
}
