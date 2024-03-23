package com.verificer.biz.beans.vo.order.req;

import io.swagger.annotations.ApiModel;

@ApiModel
public class EvaluateFormVo {
    /**
     * 是否用户评价
     */
    private boolean userFlag;

    public boolean isUserFlag() {
        return userFlag;
    }

    public void setUserFlag(boolean userFlag) {
        this.userFlag = userFlag;
    }
}
