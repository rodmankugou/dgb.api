package com.verificer.biz.biz.service.core.stock.notify.events;

public interface IStockListener {
    void onStockChange(StockEvent event);
}
