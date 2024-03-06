package com.verificer.biz.beans.enums;

import com.verificer.I18nCode;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum BusinessCertStatusEnum {
    WAIT_REVIEW(I18nCode.BUSINESS_CERT_STATUS_WAIT_REVIEW ,1),
    PASS(I18nCode.BUSINESS_CERT_STATUS_PASS ,2),
    REJECT(I18nCode.BUSINESS_CERT_STATUS_REJECT ,3);

    private String code;
    private int value;

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    BusinessCertStatusEnum(String code, int value) {
        this.code = code;
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (BusinessCertStatusEnum c : BusinessCertStatusEnum.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }

    public static String getCode(Integer status) {
        for (BusinessCertStatusEnum c : BusinessCertStatusEnum.values()) {
            if (c.getValue() == status) {
                return c.getCode();
            }
        }
        return null;
    }

    public static BusinessCertStatusEnum get(Integer status) {
        for (BusinessCertStatusEnum c : BusinessCertStatusEnum.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
