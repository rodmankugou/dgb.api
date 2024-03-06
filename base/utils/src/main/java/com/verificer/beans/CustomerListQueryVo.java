package com.verificer.beans;

import java.io.Serializable;

/**
 * Created by 35336 on 2021/2/24.
 */
public class CustomerListQueryVo extends PageQueryVo{
    private String username;
    private String nickname;
    private String roleName;
    private String mobile;
    private String email;
    private Long nationalId;
    private String realName;
    private String idCardNum;
    private Integer idInfoStatus;
    private Integer enable;
    private Long registerStartTime;
    private Long registerEndTime;
    private Long loginStartTime;
    private Long loginEndTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getNationalId() {
        return nationalId;
    }

    public void setNationalId(Long nationalId) {
        this.nationalId = nationalId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public Integer getIdInfoStatus() {
        return idInfoStatus;
    }

    public void setIdInfoStatus(Integer idInfoStatus) {
        this.idInfoStatus = idInfoStatus;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Long getRegisterStartTime() {
        return registerStartTime;
    }

    public void setRegisterStartTime(Long registerStartTime) {
        this.registerStartTime = registerStartTime;
    }

    public Long getRegisterEndTime() {
        return registerEndTime;
    }

    public void setRegisterEndTime(Long registerEndTime) {
        this.registerEndTime = registerEndTime;
    }

    public Long getLoginStartTime() {
        return loginStartTime;
    }

    public void setLoginStartTime(Long loginStartTime) {
        this.loginStartTime = loginStartTime;
    }

    public Long getLoginEndTime() {
        return loginEndTime;
    }

    public void setLoginEndTime(Long loginEndTime) {
        this.loginEndTime = loginEndTime;
    }
}
