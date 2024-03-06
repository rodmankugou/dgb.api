package com.verificer.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class JumioTransactionVo implements Serializable {

    @ApiModelProperty("tranId")
    private Long id;

    @ApiModelProperty("outCustomerId,对应jumio上传kyc资料的customerId参数")
    private String outCustomerId;

    @ApiModelProperty("transactionId，本平台内部生成的jumio事务id")
    private String innerTransactionId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOutCustomerId() {
        return outCustomerId;
    }

    public void setOutCustomerId(String outCustomerId) {
        this.outCustomerId = outCustomerId;
    }

    public String getInnerTransactionId() {
        return innerTransactionId;
    }

    public void setInnerTransactionId(String innerTransactionId) {
        this.innerTransactionId = innerTransactionId;
    }
}
