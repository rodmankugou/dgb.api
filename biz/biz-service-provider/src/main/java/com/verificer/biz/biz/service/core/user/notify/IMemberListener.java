package com.verificer.biz.biz.service.core.user.notify;

import com.verificer.biz.biz.service.core.user.notify.event.MemberEvent;

public interface IMemberListener {
    public void onMemberEvent(MemberEvent e);
}
