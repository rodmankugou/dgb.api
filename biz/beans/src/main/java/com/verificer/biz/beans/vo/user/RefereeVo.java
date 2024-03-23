package com.verificer.biz.beans.vo.user;

import com.verificer.utils.decimal.PriceDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class RefereeVo implements Serializable {
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty("手机号码")
    private String mobile;
    @ApiModelProperty("UID")
    private String uid;
    @ApiModelProperty("佣金")
    @PriceDecimal
    private BigDecimal commission;

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

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }
}
