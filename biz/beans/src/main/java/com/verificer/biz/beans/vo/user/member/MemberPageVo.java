package com.verificer.biz.beans.vo.user.member;

import com.verificer.beans.PageQueryVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class MemberPageVo extends PageQueryVo {
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty("手机号码")
    private String mobile;
    @ApiModelProperty("查询时间-开始时间")
    private Long sTime;
    @ApiModelProperty("查询时间-结束时间")
    private Long eTime;

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
