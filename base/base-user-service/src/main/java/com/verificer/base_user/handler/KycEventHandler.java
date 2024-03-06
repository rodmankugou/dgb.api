package com.verificer.base_user.handler;

import com.verificer.base_user.entity.National;

public interface KycEventHandler {
    public void onKycPass(Long customerId, National national, String userFullName);

    public void onKycReject(Long customerId,National national,String rejectReason);

}
