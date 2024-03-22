package com.verificer.biz.beans.vo.user.req;

import com.verificer.beans.PageQueryVo;
import io.swagger.annotations.ApiModelProperty;

public class RefereeBaseVo extends PageQueryVo {
    @ApiModelProperty("ID")
    private Long userId;

    private Long accountId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
