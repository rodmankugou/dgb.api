package com.verificer.enums;

/**
 * 注册类型
 * 19.
 */
public enum RegisterType {

	MOBILE(1,"手机"), EMAIL(2,"邮箱");

	// 成员变量
	private String name;
	private int value;

	private RegisterType(int value,String name) {
		this.name = name;
		this.value = value;
	}
	// 普通方法
	public static String getName(int value) {
		for (RegisterType c : RegisterType.values()) {
			if (c.getValue() == value) {
				return c.name;
			}
		}
		return null;
	}

	// 普通方法
	public static RegisterType getByValue(Integer value) {
		for (RegisterType c : RegisterType.values()) {
			if (c.getValue() == value) {
				return c;
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
