package com.verificer.biz.beans.vo.shop;

import com.verificer.beans.PageQueryVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ShopQueryVo extends PageQueryVo {
    @ApiModelProperty("搜索关键字")
    private String sKey;

    public String getsKey() {
        return sKey;
    }

    public void setsKey(String sKey) {
        this.sKey = sKey;
    }
}
