package com.verificer.enums;

import com.verificer.I18nCode;
import com.verificer.beans.IdCardType;

/**
 * 注册类型
 * 19.
 */
public enum CustomerVerifiedStatus {

	UNDER_REVIEW(I18nCode.CUS_VER_STATUS_UNDER_REVIEW,1,"等待审核"),
	PASS(I18nCode.CUS_VER_STATUS_PASS,2,"审核通过"),
	NOT_PASS(I18nCode.CUS_VER_STATUS_NOT_PASS,3,"审核不通过"),
	MANUAL_REVIEW(I18nCode.CUS_VER_STATUS_MANUAL_REVIEW,4,"需人工审核");

	// 成员变量
	private String code;
	private String name;
	private int value;

	private CustomerVerifiedStatus(String code,int value, String name) {
		this.code = code;
		this.name = name;
		this.value = value;
	}
	// 普通方法
	public static String getName(int value) {
		for (CustomerVerifiedStatus c : CustomerVerifiedStatus.values()) {
			if (c.getValue() == value) {
				return c.name;
			}
		}
		return null;
	}

	public static String getCode(Integer status) {
		for (CustomerVerifiedStatus c : CustomerVerifiedStatus.values()) {
			if (c.getValue() == status) {
				return c.code;
			}
		}
		return null;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}


}
