package com.verificer.biz.beans.vo.user.member;

import java.io.Serializable;

public class MemberStaQryVo implements Serializable {
    private Long sTime;
    private Long eTime;
    private Integer status;
    private Boolean firstChargeFlag;
    private String referrerId;

    public Long getsTime() {
        return sTime;
    }

    public void setsTime(Long sTime) {
        this.sTime = sTime;
    }

    public Long geteTime() {
        return eTime;
    }

    public void seteTime(Long eTime) {
        this.eTime = eTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getFirstChargeFlag() {
        return firstChargeFlag;
    }

    public void setFirstChargeFlag(Boolean firstChargeFlag) {
        this.firstChargeFlag = firstChargeFlag;
    }

    public String getReferrerId() {
        return referrerId;
    }

    public void setReferrerId(String referrerId) {
        this.referrerId = referrerId;
    }
}
