package com.verificer.base.auth.entity;

public class RAuthRes {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column r_auth_res.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column r_auth_res.auth_code
     *
     * @mbg.generated
     */
    private String authCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column r_auth_res.res_code
     *
     * @mbg.generated
     */
    private String resCode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column r_auth_res.id
     *
     * @return the value of r_auth_res.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column r_auth_res.id
     *
     * @param id the value for r_auth_res.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column r_auth_res.auth_code
     *
     * @return the value of r_auth_res.auth_code
     *
     * @mbg.generated
     */
    public String getAuthCode() {
        return authCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column r_auth_res.auth_code
     *
     * @param authCode the value for r_auth_res.auth_code
     *
     * @mbg.generated
     */
    public void setAuthCode(String authCode) {
        this.authCode = authCode == null ? null : authCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column r_auth_res.res_code
     *
     * @return the value of r_auth_res.res_code
     *
     * @mbg.generated
     */
    public String getResCode() {
        return resCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column r_auth_res.res_code
     *
     * @param resCode the value for r_auth_res.res_code
     *
     * @mbg.generated
     */
    public void setResCode(String resCode) {
        this.resCode = resCode == null ? null : resCode.trim();
    }
}