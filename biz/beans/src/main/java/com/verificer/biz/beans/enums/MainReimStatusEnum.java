package com.verificer.biz.beans.enums;

import com.verificer.I18nCode;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum MainReimStatusEnum {
    WAIT_OWNER_REVIEW(I18nCode.MAIN_REIN_STATUS_WAIT_OWNER_REVIEW,1),  //待发展商审核
    WAIT_BANK_REVIEW(I18nCode.MAIN_REIN_STATUS_WAIT_BANK_REVIEW ,2),   //待银行审核
    OWNER_REJECT(I18nCode.MAIN_REIN_STATUS_OWNER_REJECT ,3),           //发展商驳回
    PASS(I18nCode.MAIN_REIN_STATUS_PASS ,4),                           //通过
    BANK_REJECT(I18nCode.MAIN_REIN_STATUS_BANK_REJECT ,5);             //银行驳回

    private String code;
    private int value;

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    MainReimStatusEnum(String code, int value) {
        this.code = code;
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (MainReimStatusEnum c : MainReimStatusEnum.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }

    public static String getCode(Integer status) {
        for (MainReimStatusEnum c : MainReimStatusEnum.values()) {
            if (c.getValue() == status) {
                return c.getCode();
            }
        }
        return null;
    }

    public static MainReimStatusEnum get(Integer status) {
        for (MainReimStatusEnum c : MainReimStatusEnum.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
