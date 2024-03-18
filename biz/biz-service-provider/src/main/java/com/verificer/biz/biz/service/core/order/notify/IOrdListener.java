package com.verificer.biz.biz.service.core.order.notify;

import com.verificer.biz.biz.service.core.order.notify.events.OrdEvent;

public interface IOrdListener {
    public void onOrdEvent(OrdEvent e);
}
