package com.verificer.enums;

/**
 * 语言类型
 * 19.
 */
public enum LanguageType {

	CN_ZH("cn_ZH"), CN_TW("cn_TW"), EN_US("en_US");

	private String name;
	private LanguageType( String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
