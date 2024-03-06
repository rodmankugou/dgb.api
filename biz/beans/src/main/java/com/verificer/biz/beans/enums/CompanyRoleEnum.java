package com.verificer.biz.beans.enums;

import com.verificer.I18nCode;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum CompanyRoleEnum {
    BANK(I18nCode.COMPANY_ROLE_BANK,0),   //发展d商
    OWNER(I18nCode.COMPANY_ROLE_OWNER,1),   //发展d商
    MAIN_CONTRACTOR(I18nCode.COMPANY_ROLE_MAIN_CONTRACTOR,2),  //建筑商
    SUPPLIER(I18nCode.COMPANY_ROLE_SUPPLIER,3);  //供应商

    private String code;
    private int value;

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    CompanyRoleEnum(String code, int value) {
        this.code = code;
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (CompanyRoleEnum c : CompanyRoleEnum.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }

    public static String getCode(Integer status) {
        for (CompanyRoleEnum c : CompanyRoleEnum.values()) {
            if (c.getValue() == status) {
                return c.getCode();
            }
        }
        return null;
    }

    public static CompanyRoleEnum get(Integer status) {
        for (CompanyRoleEnum c : CompanyRoleEnum.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
