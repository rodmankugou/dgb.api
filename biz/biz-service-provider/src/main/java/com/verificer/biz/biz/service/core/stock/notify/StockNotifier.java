package com.verificer.biz.biz.service.core.stock.notify;

import com.verificer.biz.biz.service.core.stock.notify.events.IStockListener;
import com.verificer.biz.biz.service.core.stock.notify.events.StockEvent;
import com.verificer.designpatterns.listener.ConcurrentNotifier;

public class StockNotifier extends ConcurrentNotifier<IStockListener, StockEvent> {
    @Override
    protected void trigger(IStockListener listener, StockEvent event)  {
        listener.onStockChange(event);
    }
}
