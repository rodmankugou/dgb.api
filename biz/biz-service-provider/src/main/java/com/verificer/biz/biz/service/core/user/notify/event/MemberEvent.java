package com.verificer.biz.biz.service.core.user.notify.event;

import com.verificer.biz.biz.entity.MemberOrder;

public class MemberEvent {
    private MemberOrder ord;

    public MemberEvent(MemberOrder ord) {
        this.ord = ord;
    }

    public MemberOrder getOrd() {
        return ord;
    }

}
