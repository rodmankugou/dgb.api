package com.verificer.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 35336 on 2021/2/25.
 */
@ApiModel
public class BannerQueryVo extends PageQueryVo {
//    @ApiModelProperty("名称，搜索条件")
    private String name;
//    @ApiModelProperty("类型")
    private Integer terminalType;
//    @ApiModelProperty("是否启用")
    private Boolean enable;
    private String internationalType;



    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(Integer terminalType) {
        this.terminalType = terminalType;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getInternationalType() {
        return internationalType;
    }

    public void setInternationalType(String internationalType) {
        this.internationalType = internationalType;
    }

}
