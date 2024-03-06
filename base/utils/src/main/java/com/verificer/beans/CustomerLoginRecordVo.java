package com.verificer.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * @author choufengleilei
 * @desc
 * @date 2018\12\3 0003 11:42
 */
public class CustomerLoginRecordVo implements Serializable {

    private Integer id;

    /**
     * 用户id
     */
    private Integer customerId;

    /**
     * 登录时间
     */
    private Date loginTime;


    /**
     * 登录IP
     */
    private String loginIP;

    /**
     * 登录地点
     */
    private String loginAddress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public String getLoginAddress() {
        return loginAddress;
    }

    public void setLoginAddress(String loginAddress) {
        this.loginAddress = loginAddress;
    }

    @Override
    public String toString() {
        return "CustomerLoginRecord{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", loginTime=" + loginTime +
                ", loginIP='" + loginIP + '\'' +
                ", loginAddress='" + loginAddress + '\'' +
                '}';
    }
}
