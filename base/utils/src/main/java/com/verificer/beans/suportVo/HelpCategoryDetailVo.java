package com.verificer.beans.suportVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
@ApiModel
public class HelpCategoryDetailVo implements Serializable {
	private static final long serialVersionUID = -2670593488942336057L;
	/**
	 * 主键id
	 */
	@ApiModelProperty(value = "主键id",required = true)
	private Long id;
	/**
	 * 创建时间
	 */
	private Long createTime;
	/**
	 * 更新时间
	 */
	private Long updateTime;
	/**
	 * 版本号
	 */
	private Long version;

	/**
	 * 帮助分类
	 */
	@ApiModelProperty(value = "分类ID",required = true)
	private Long helpCategoryId;
	/**
	 * 分类名称
	 */
	@ApiModelProperty(value = "分类名称",required = true)
	private String category;

	/**
	 * 国际化标记
	 */
	@ApiModelProperty(value = "国际化标记",required = true)
	private String internationalType;
	/**
	 * 是否启用
	 */
	private Boolean enable;
	/**
	 * 标题
	 */
	@ApiModelProperty(value = "标题")
	private String title;
	/**
	 * 内容
	 */
	@ApiModelProperty(value = "内容")
	private String content;
	/**
	 * 排序
	 */
	private Integer sortParam;
	/**
	 * 总人数
	 */
	@ApiModelProperty(value = "总人数")
	private Integer totalNumber;
	/**
	 * 认为有用的人数
	 */
	@ApiModelProperty(value = "认为有用的人数")
	private Integer usefulNumber;

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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getHelpCategoryId() {
		return helpCategoryId;
	}

	public void setHelpCategoryId(Long helpCategoryId) {
		this.helpCategoryId = helpCategoryId;
	}

	public String getInternationalType() {
		return internationalType;
	}

	public void setInternationalType(String internationalType) {
		this.internationalType = internationalType;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getSortParam() {
		return sortParam;
	}

	public void setSortParam(Integer sortParam) {
		this.sortParam = sortParam;
	}

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public Integer getUsefulNumber() {
		return usefulNumber;
	}

	public void setUsefulNumber(Integer usefulNumber) {
		this.usefulNumber = usefulNumber;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
