package com.verificer.enums;

import com.verificer.I18nCode;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum UserTypeEnums {
    ADMIN(I18nCode.USER_TYPE_ADMIN ,1), //管理后台端用户
    WEB(I18nCode.USER_TYPE_WEB ,2), //WEG端用户
    BANK(I18nCode.USER_TYPE_BANK ,3); //银行端用户

    private String code;
    private int value;

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    UserTypeEnums(String code, int value) {
        this.code = code;
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (UserTypeEnums c : UserTypeEnums.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }

    public static String getCode(Integer status) {
        for (UserTypeEnums c : UserTypeEnums.values()) {
            if (c.getValue() == status) {
                return c.getCode();
            }
        }
        return null;
    }

    public static UserTypeEnums get(Integer status) {
        for (UserTypeEnums c : UserTypeEnums.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
