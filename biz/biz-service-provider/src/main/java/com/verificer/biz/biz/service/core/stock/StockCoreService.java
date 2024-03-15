package com.verificer.biz.biz.service.core.stock;

import com.verificer.biz.beans.exceptions.StockInsufficientException;
import com.verificer.biz.beans.vo.req.StockUpdVo;

import java.util.List;

public interface StockCoreService {
    void addStageStockIfNotExist(Long goodsId, Long specId, List<String> stageIds);

    void modifyStock(StockUpdVo updVo) throws StockInsufficientException;
    void modifyStock(List<StockUpdVo> asList) throws StockInsufficientException;

    boolean isStockExist(String shopId,Long goodsId, Long specId);

    void addShopStockIfNotExist(String shopId, Long goodsId, Long specId);
}
