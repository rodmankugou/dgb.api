package com.verificer.beans;


import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 26.
 */
public class NationalVo implements Serializable{
	private static final long serialVersionUID = 6197359005195548125L;

	private Long id;

	private Long createTime;

	private Long updateTime;
	/**
	 * 国家
	 */
	@ApiModelProperty("国家")
	private String nationality;
	/**
	 * 国家语言缩写
	 */
	@ApiModelProperty("国家语言缩写")
	private String shortName;
	/**
	 * 排序
	 */
	@ApiModelProperty("排序")
	private Integer sortParam;

	/**
	 * 英文地区名
	 */
	@ApiModelProperty("英文地区名")
	private String nationalityEn;

	/**
	 * 繁体地区名
	 */
	@ApiModelProperty("繁体地区名")
	private String nationalityTw;

	/**
	 * 地区编号
	 */
	@ApiModelProperty("地区编号")
	private String countryCode;

	/**
	 * 地区编号
	 */
	@ApiModelProperty("国家代码，如中国CHN")
	private String nationCode;

	/**
	 * 是否可用
	 */
	private Boolean enable;


	public String getNationCode() {
		return nationCode;
	}

	public void setNationCode(String nationCode) {
		this.nationCode = nationCode;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Integer getSortParam() {
		return sortParam;
	}

	public void setSortParam(Integer sortParam) {
		this.sortParam = sortParam;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getNationalityEn() {
		return nationalityEn;
	}

	public void setNationalityEn(String nationalityEn) {
		this.nationalityEn = nationalityEn;
	}

	public String getNationalityTw() {
		return nationalityTw;
	}

	public void setNationalityTw(String nationalityTw) {
		this.nationalityTw = nationalityTw;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}


}
