package com.verificer.biz.beans.vo.addr.req;

import com.verificer.beans.AppPageQueryVo;
import com.verificer.beans.PageQueryVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AddrQryVo extends AppPageQueryVo {
    @ApiModelProperty(hidden = true)
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
