package com.verificer.web.common.appversion;


import java.io.Serializable;
import java.util.Date;

/**
 * Created by yaojunbing on 2018/4/20.
 */
public class AppVersionUpdateVo implements Serializable {
	private static final long serialVersionUID = 4970685814555429481L;
	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 最新版本号
	 */
	private Integer newVersion;

	/**
	 * 最低版本号
	 */
	private Integer lowVersion;

	/**
	 *
	 */
	private String osType;

	/**
	 * 更新链接
	 */
	private String url;

	/**
	 * 创建时间
	 */
	private Date createTime;

	private String note;

	private String versionString;

	private String versionInfo;

	public String getVersionInfo() {
		return versionInfo;
	}

	public void setVersionInfo(String versionInfo) {
		this.versionInfo = versionInfo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNewVersion() {
		return newVersion;
	}

	public void setNewVersion(Integer newVersion) {
		this.newVersion = newVersion;
	}

	public Integer getLowVersion() {
		return lowVersion;
	}

	public void setLowVersion(Integer lowVersion) {
		this.lowVersion = lowVersion;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getVersionString() {
		return versionString;
	}

	public void setVersionString(String versionString) {
		this.versionString = versionString;
	}
}
