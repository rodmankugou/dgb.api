package com.verificer.beans.bankcard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by 35336 on 2020/12/27.
 */
@ApiModel
public class BankcardStatRespVo {
    @ApiModelProperty(value = "用户身份证信息状态")
    private String idInfoStatus;
    @ApiModelProperty(value = "真实姓名")
    private String realName;
    @ApiModelProperty(value = "是否设置交易密码",required = true)
    private Boolean hasPayPassword;
    @ApiModelProperty(value = "是否启用",required = true)
    private Boolean enable;
    @ApiModelProperty(value = "是否已绑定有银行卡",required = true)
    private Boolean result;
    @ApiModelProperty(value = "银行卡列表")
    private List<BankcardVo> list;
    @ApiModelProperty(value = "checkPayPassword")
    private Boolean checkPayPassword;

    public String getIdInfoStatus() {
        return idInfoStatus;
    }

    public void setIdInfoStatus(String idInfoStatus) {
        this.idInfoStatus = idInfoStatus;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Boolean getHasPayPassword() {
        return hasPayPassword;
    }

    public void setHasPayPassword(Boolean hasPayPassword) {
        this.hasPayPassword = hasPayPassword;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public List<BankcardVo> getList() {
        return list;
    }

    public void setList(List<BankcardVo> list) {
        this.list = list;
    }

    public Boolean getCheckPayPassword() {
        return checkPayPassword;
    }

    public void setCheckPayPassword(Boolean checkPayPassword) {
        this.checkPayPassword = checkPayPassword;
    }
}
