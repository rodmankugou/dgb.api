package com.verificer.biz.biz.service.core.stock.notify.events;

import com.verificer.biz.biz.entity.StockLog;

public class StockEvent {
    private boolean isAdd;
    private boolean stageFlag;
    private StockLog stockLog;

    public StockEvent(boolean isAdd,boolean stageFlag,StockLog stockLog) {
        this.isAdd = isAdd;
        this.stageFlag = stageFlag;
        this.stockLog = stockLog;
    }

    public Boolean getAdd() {
        return isAdd;
    }


    public boolean isAdd() {
        return isAdd;
    }

    public boolean isStageFlag() {
        return stageFlag;
    }

    public StockLog getStockLog() {
        return stockLog;
    }
}
