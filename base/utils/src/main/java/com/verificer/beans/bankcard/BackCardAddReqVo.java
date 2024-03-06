package com.verificer.beans.bankcard;

import java.io.Serializable;

/**
 * Created by 35336 on 2020/12/27.
 */
public class BackCardAddReqVo implements Serializable{
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 开户地址
     */
    private String subBankName;
    /**
     * 卡号
     */
    private String cardNumber;
    private String routingNumber;
    private String swift;
    private String cardOwner;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSubBankName() {
        return subBankName;
    }

    public void setSubBankName(String subBankName) {
        this.subBankName = subBankName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    public String getCardOwner() {
        return cardOwner;
    }

    public void setCardOwner(String cardOwner) {
        this.cardOwner = cardOwner;
    }
}
