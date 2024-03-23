package com.verificer.biz.beans.vo.user.withdraw;

import com.verificer.utils.decimal.PriceDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class ReferrerWithdrawVo implements Serializable {
    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("UID")
    private String uid;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("手机号码")
    private String mobile;

    @ApiModelProperty("订单号")
    private String ordNum;

    @ApiModelProperty("申请时间")
    private Long createTime;

    @ApiModelProperty("提现金额")
    @PriceDecimal
    private BigDecimal amount;

    @ApiModelProperty("状态 1-待审批 2-审批不通过 3-审批通过 4-已打款")
    private Integer status;

    @ApiModelProperty("备注")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrdNum() {
        return ordNum;
    }

    public void setOrdNum(String ordNum) {
        this.ordNum = ordNum;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
