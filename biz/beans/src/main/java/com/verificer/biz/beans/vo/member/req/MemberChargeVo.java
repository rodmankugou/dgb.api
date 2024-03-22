package com.verificer.biz.beans.vo.member.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class MemberChargeVo implements Serializable {
    private Long userId;
    private String ip;
    @ApiModelProperty("会员ID")
    private Long memberTypeId;

    @ApiModelProperty(value = "引荐方类型：1-店铺 2-用户。如果是扫码进入，参数会在二维码信息中",required = false)
    private Integer referrerType;

    @ApiModelProperty(value = "引荐方ID。如果是扫码进入，参数会在二维码信息中",required = false)
    private String referrerId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMemberTypeId() {
        return memberTypeId;
    }

    public void setMemberTypeId(Long memberTypeId) {
        this.memberTypeId = memberTypeId;
    }

    public Integer getReferrerType() {
        return referrerType;
    }

    public void setReferrerType(Integer referrerType) {
        this.referrerType = referrerType;
    }

    public String getReferrerId() {
        return referrerId;
    }

    public void setReferrerId(String referrerId) {
        this.referrerId = referrerId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
