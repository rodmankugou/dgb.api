package com.verificer.biz.biz.service.core.user.notify.event;

import com.verificer.biz.biz.entity.MemberOrder;

public class MemberOrdSucEvent extends MemberEvent {
    public MemberOrdSucEvent(MemberOrder ord) {
        super(ord);
    }
}
