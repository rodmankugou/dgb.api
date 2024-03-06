package com.verificer.enums;

import com.verificer.I18nCode;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum SexEnums {
    MALE(I18nCode.SEX_MALE ,0), //男性 
    FEMALE(I18nCode.SEX_FEMALE ,1); //女性

    private String code;
    private int value;

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    SexEnums(String code, int value) {
        this.code = code;
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (SexEnums c : SexEnums.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }

    public static String getCode(Integer status) {
        for (SexEnums c : SexEnums.values()) {
            if (c.getValue() == status) {
                return c.getCode();
            }
        }
        return null;
    }

    public static SexEnums get(Integer status) {
        for (SexEnums c : SexEnums.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
