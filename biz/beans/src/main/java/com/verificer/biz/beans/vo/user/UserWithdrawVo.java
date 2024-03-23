package com.verificer.biz.beans.vo.user;

import com.verificer.utils.decimal.PrcLimit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class UserWithdrawVo implements Serializable {
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty("手机号码")
    private String mobile;
    @ApiModelProperty("UID")
    private String uid;
    @ApiModelProperty("金额")
    @PrcLimit(2)
    private String amount;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
