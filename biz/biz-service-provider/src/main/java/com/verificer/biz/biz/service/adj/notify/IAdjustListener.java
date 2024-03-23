package com.verificer.biz.biz.service.adj.notify;

import com.verificer.biz.biz.service.adj.notify.events.AdjEvent;

public interface IAdjustListener {
    public void onEvent(AdjEvent event);
}
