package com.verificer.biz.beans.vo.user.withdraw;

import com.verificer.beans.IdVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

@ApiModel
public class ReferrerReviewVo extends IdVo {
    @ApiModelProperty("是否通过 true-是 false-否")
    private Boolean passFlag;
    @ApiModelProperty("备注")
    private String remark;

    private Long staffId;
    private String staffName;

    public Boolean getPassFlag() {
        return passFlag;
    }

    public void setPassFlag(Boolean passFlag) {
        this.passFlag = passFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
}
