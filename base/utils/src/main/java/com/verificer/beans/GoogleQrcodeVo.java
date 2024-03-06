package com.verificer.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by 35336 on 2020/12/26.
 */
@ApiModel
public class GoogleQrcodeVo implements Serializable{
    @ApiModelProperty(value = "二维码",required = true)
    private String qrCode;
    @ApiModelProperty(value = "密钥",required = true)
    private String secret;

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
