package com.verificer.biz.beans.enums;

import com.verificer.I18nCode;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum CompanySizeEnum {
    _1_TO_10(I18nCode.COMPANY_SIZE_1_TO_10 ,1),
    _11_TO_50(I18nCode.COMPANY_SIZE_11_TO_50 ,2),
    _51_TO_100(I18nCode.COMPANY_SIZE_51_TO_100 ,3),
    _101_TO_500(I18nCode.COMPANY_SIZE_101_TO_500 ,4),
    _500_TO_MAX(I18nCode.COMPANY_SIZE_500_TO_MAX ,5);

    private String code;
    private int value;

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    CompanySizeEnum(String code, int value) {
        this.code = code;
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (CompanySizeEnum c : CompanySizeEnum.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }

    public static String getCode(Integer status) {
        for (CompanySizeEnum c : CompanySizeEnum.values()) {
            if (c.getValue() == status) {
                return c.getCode();
            }
        }
        return null;
    }

    public static CompanySizeEnum get(Integer status) {
        for (CompanySizeEnum c : CompanySizeEnum.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
