package com.verificer.base_user.service;

import com.verificer.base_user.entity.CustomerVerified;
import com.verificer.base_user.entity.JumioTransaction;

public interface CustomerVerifiedHandler {
    /**
     * kyc失败
     * @param customerVerified
     * @param jumioRemark
     */
    public void jumioVerifyFailed(CustomerVerified customerVerified, String jumioRemark, JumioTransaction transaction);

    /**
     * kyc转人工审核
     * @param customerVerified
     * @param jumioRemark
     */
    public void tranManualReview(CustomerVerified customerVerified,String jumioRemark, JumioTransaction transaction);

    /**
     * kyc成功
     * @param customerVerified
     */
    public void kycSuccess(CustomerVerified customerVerified, JumioTransaction transaction);
}
