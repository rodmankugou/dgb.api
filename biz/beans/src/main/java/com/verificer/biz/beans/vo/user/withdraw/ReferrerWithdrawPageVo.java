package com.verificer.biz.beans.vo.user.withdraw;

import com.verificer.beans.PageQueryVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

@ApiModel
public class ReferrerWithdrawPageVo extends PageQueryVo {
    @ApiModelProperty("手机号码")
    private String mobile;
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty("下单时间-开始时间")
    private Long sTime;
    @ApiModelProperty("下单时间-结束时间")
    private Long eTime;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

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
}
