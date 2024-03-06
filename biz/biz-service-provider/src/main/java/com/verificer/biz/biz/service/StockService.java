package com.verificer.biz.biz.service;

import java.util.List;

public interface StockService {
    void addStageStockIfNotExist(Long goodsId, Long specId, List<Long> stageIds);
}
