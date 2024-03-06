package com.verificer.beans.suportVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 */
@ApiModel
public class HelpCategoryVo implements Serializable {
	private static final long serialVersionUID = 1253222667397570776L;
	/**
	 * 主键id
	 */
	@ApiModelProperty(value = "主键",required = true)
	private Long id;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间",required = true)
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 分类
	 */
	@ApiModelProperty(value = "分类",required = true)
	private String category;
	/**
	 * 国际化标记
	 */
	@ApiModelProperty(value = "国际化标记",required = true)
	private String internationalType;
	/**
	 * 排序参数
	 */
	@ApiModelProperty(value = "排序参数",required = true)
	private Integer sortParam;

	private Boolean enable;

	private Integer helpTypeId;

	private List<HelpCategoryDetailVo> detailVoList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getInternationalType() {
		return internationalType;
	}

	public void setInternationalType(String internationalType) {
		this.internationalType = internationalType;
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

	public Integer getHelpTypeId() {
		return helpTypeId;
	}

	public void setHelpTypeId(Integer helpTypeId) {
		this.helpTypeId = helpTypeId;
	}

	public List<HelpCategoryDetailVo> getDetailVoList() {
		return detailVoList;
	}

	public void setDetailVoList(List<HelpCategoryDetailVo> detailVoList) {
		this.detailVoList = detailVoList;
	}
}
