package com.verificer.beans.customer.req;

import com.verificer.beans.PageQueryVo;

public class CustomerPageVo extends PageQueryVo {
    private Long id;
    private Integer userType;
    private Long projectId;
    private Long excludeProjectId;
    private Integer[] roles;
    private Integer companyReviewStatus;  //企业的KYC审核状态
    private Integer role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Integer[] getRoles() {
        return roles;
    }

    public void setRoles(Integer[] roles) {
        this.roles = roles;
    }

    public Integer getCompanyReviewStatus() {
        return companyReviewStatus;
    }

    public void setCompanyReviewStatus(Integer companyReviewStatus) {
        this.companyReviewStatus = companyReviewStatus;
    }

    public Long getExcludeProjectId() {
        return excludeProjectId;
    }

    public void setExcludeProjectId(Long excludeProjectId) {
        this.excludeProjectId = excludeProjectId;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
