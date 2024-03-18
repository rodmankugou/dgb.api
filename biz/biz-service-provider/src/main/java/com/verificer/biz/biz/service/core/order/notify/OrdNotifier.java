package com.verificer.biz.biz.service.core.order.notify;

import com.verificer.biz.biz.service.core.order.notify.events.OrdEvent;
import com.verificer.designpatterns.listener.ConcurrentNotifier;

public class OrdNotifier extends ConcurrentNotifier<IOrdListener, OrdEvent> {
    @Override
    protected void trigger(IOrdListener listener, OrdEvent event)  {
        listener.onOrdEvent(event);
    }
}
