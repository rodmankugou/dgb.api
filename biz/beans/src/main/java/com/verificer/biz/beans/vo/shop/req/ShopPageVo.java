package com.verificer.biz.beans.vo.shop.req;

import com.verificer.beans.PageQueryVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ShopPageVo extends PageQueryVo {
    @ApiModelProperty("店铺名")
    private String name;
    @ApiModelProperty("省编码")
    private String adrArea1;
    @ApiModelProperty("市编码")
    private String adrArea2;
    @ApiModelProperty("区编码")
    private String adrArea3;
    @ApiModelProperty("关键字")
    private String searchKey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdrArea1() {
        return adrArea1;
    }

    public void setAdrArea1(String adrArea1) {
        this.adrArea1 = adrArea1;
    }

    public String getAdrArea2() {
        return adrArea2;
    }

    public void setAdrArea2(String adrArea2) {
        this.adrArea2 = adrArea2;
    }

    public String getAdrArea3() {
        return adrArea3;
    }

    public void setAdrArea3(String adrArea3) {
        this.adrArea3 = adrArea3;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }
}
