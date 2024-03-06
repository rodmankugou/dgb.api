package com.verificer.biz.biz.entity;

import java.math.BigDecimal;

public class Stage {
    private Long id;

    private String uuid;

    private String name;

    private String cpName;

    private String cpMobile;

    private String adrArea1;

    private String adrArea1Name;

    private String adrArea2;

    private String adrArea2Name;

    private String adrArea3;

    private String adrArea3Name;

    private String adrDetail;

    private String fullAddr;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private Long createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stage.id
     *
     * @return the value of stage.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stage.id
     *
     * @param id the value for stage.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stage.uuid
     *
     * @return the value of stage.uuid
     *
     * @mbg.generated
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stage.uuid
     *
     * @param uuid the value for stage.uuid
     *
     * @mbg.generated
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stage.name
     *
     * @return the value of stage.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stage.name
     *
     * @param name the value for stage.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stage.cp_name
     *
     * @return the value of stage.cp_name
     *
     * @mbg.generated
     */
    public String getCpName() {
        return cpName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stage.cp_name
     *
     * @param cpName the value for stage.cp_name
     *
     * @mbg.generated
     */
    public void setCpName(String cpName) {
        this.cpName = cpName == null ? null : cpName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stage.cp_mobile
     *
     * @return the value of stage.cp_mobile
     *
     * @mbg.generated
     */
    public String getCpMobile() {
        return cpMobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stage.cp_mobile
     *
     * @param cpMobile the value for stage.cp_mobile
     *
     * @mbg.generated
     */
    public void setCpMobile(String cpMobile) {
        this.cpMobile = cpMobile == null ? null : cpMobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stage.adr_area1
     *
     * @return the value of stage.adr_area1
     *
     * @mbg.generated
     */
    public String getAdrArea1() {
        return adrArea1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stage.adr_area1
     *
     * @param adrArea1 the value for stage.adr_area1
     *
     * @mbg.generated
     */
    public void setAdrArea1(String adrArea1) {
        this.adrArea1 = adrArea1 == null ? null : adrArea1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stage.adr_area1_name
     *
     * @return the value of stage.adr_area1_name
     *
     * @mbg.generated
     */
    public String getAdrArea1Name() {
        return adrArea1Name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stage.adr_area1_name
     *
     * @param adrArea1Name the value for stage.adr_area1_name
     *
     * @mbg.generated
     */
    public void setAdrArea1Name(String adrArea1Name) {
        this.adrArea1Name = adrArea1Name == null ? null : adrArea1Name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stage.adr_area2
     *
     * @return the value of stage.adr_area2
     *
     * @mbg.generated
     */
    public String getAdrArea2() {
        return adrArea2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stage.adr_area2
     *
     * @param adrArea2 the value for stage.adr_area2
     *
     * @mbg.generated
     */
    public void setAdrArea2(String adrArea2) {
        this.adrArea2 = adrArea2 == null ? null : adrArea2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stage.adr_area2_name
     *
     * @return the value of stage.adr_area2_name
     *
     * @mbg.generated
     */
    public String getAdrArea2Name() {
        return adrArea2Name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stage.adr_area2_name
     *
     * @param adrArea2Name the value for stage.adr_area2_name
     *
     * @mbg.generated
     */
    public void setAdrArea2Name(String adrArea2Name) {
        this.adrArea2Name = adrArea2Name == null ? null : adrArea2Name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stage.adr_area3
     *
     * @return the value of stage.adr_area3
     *
     * @mbg.generated
     */
    public String getAdrArea3() {
        return adrArea3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stage.adr_area3
     *
     * @param adrArea3 the value for stage.adr_area3
     *
     * @mbg.generated
     */
    public void setAdrArea3(String adrArea3) {
        this.adrArea3 = adrArea3 == null ? null : adrArea3.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stage.adr_area3_name
     *
     * @return the value of stage.adr_area3_name
     *
     * @mbg.generated
     */
    public String getAdrArea3Name() {
        return adrArea3Name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stage.adr_area3_name
     *
     * @param adrArea3Name the value for stage.adr_area3_name
     *
     * @mbg.generated
     */
    public void setAdrArea3Name(String adrArea3Name) {
        this.adrArea3Name = adrArea3Name == null ? null : adrArea3Name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stage.adr_detail
     *
     * @return the value of stage.adr_detail
     *
     * @mbg.generated
     */
    public String getAdrDetail() {
        return adrDetail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stage.adr_detail
     *
     * @param adrDetail the value for stage.adr_detail
     *
     * @mbg.generated
     */
    public void setAdrDetail(String adrDetail) {
        this.adrDetail = adrDetail == null ? null : adrDetail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stage.full_addr
     *
     * @return the value of stage.full_addr
     *
     * @mbg.generated
     */
    public String getFullAddr() {
        return fullAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stage.full_addr
     *
     * @param fullAddr the value for stage.full_addr
     *
     * @mbg.generated
     */
    public void setFullAddr(String fullAddr) {
        this.fullAddr = fullAddr == null ? null : fullAddr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stage.longitude
     *
     * @return the value of stage.longitude
     *
     * @mbg.generated
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stage.longitude
     *
     * @param longitude the value for stage.longitude
     *
     * @mbg.generated
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stage.latitude
     *
     * @return the value of stage.latitude
     *
     * @mbg.generated
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stage.latitude
     *
     * @param latitude the value for stage.latitude
     *
     * @mbg.generated
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column stage.create_time
     *
     * @return the value of stage.create_time
     *
     * @mbg.generated
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column stage.create_time
     *
     * @param createTime the value for stage.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
