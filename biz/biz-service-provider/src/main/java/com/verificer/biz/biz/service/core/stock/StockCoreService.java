package com.verificer.biz.biz.service.core.stock;

import com.verificer.biz.beans.exceptions.StockInsufficientException;
import com.verificer.biz.beans.vo.req.StockUpdVo;
import com.verificer.biz.biz.entity.Stock;
import com.verificer.biz.biz.service.core.stock.entity.StockIdVo;
import com.verificer.biz.biz.service.core.stock.notify.events.IStockListener;

import java.util.List;

public interface StockCoreService {
    void addListener(IStockListener listener);
    void addStageStockIfNotExist(Long goodsId, Long specId,String stageId);

    void modifyStock(StockUpdVo updVo) throws StockInsufficientException;
    void modifyStock(List<StockUpdVo> asList) throws StockInsufficientException;

    boolean isStockExist(String shopId,Long goodsId, Long specId);

    void addShopStockIfNotExist(String shopId, Long goodsId, Long specId);


    void lockStocks(List<StockIdVo> idVos);

    Stock getStock(String relId, Long specId);

    Stock getById(Long id);
}
