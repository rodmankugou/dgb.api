package com.verificer.enums;

import com.verificer.I18nCode;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum BooleanEnums {
    TRUE(I18nCode.BOOLEAN_TRUE ,1),
    FALSE(I18nCode.B0OLEAN_FALSE ,0);

    private String code;
    private int value;

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    BooleanEnums(String code, int value) {
        this.code = code;
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (BooleanEnums c : BooleanEnums.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }

    public static String getCode(Integer status) {
        for (BooleanEnums c : BooleanEnums.values()) {
            if (c.getValue() == status) {
                return c.getCode();
            }
        }
        return null;
    }

    public static BooleanEnums get(Integer status) {
        for (BooleanEnums c : BooleanEnums.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
