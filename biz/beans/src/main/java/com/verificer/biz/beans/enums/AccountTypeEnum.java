package com.verificer.biz.beans.enums;

import com.verificer.I18nCode;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum AccountTypeEnum {
    MAIN_REIM_TRANSFER_OUT(I18nCode.ACCOUNT_TYPE_MAIN_REIM_TRANSFER_OUT ,1),
    MAIN_REIM_TRANSFER_IN(I18nCode.ACCOUNT_TYPE_MAIN_REIM_TRANSFER_IN ,2),
    SUPP_REIM_TRANSFER_OUT(I18nCode.ACCOUNT_TYPE_SUPP_REIM_TRANSFER_OUT ,3),
    SUPP_REIM_TRANSFER_IN(I18nCode.ACCOUNT_TYPE_SUPP_REIM_TRANSFER_IN ,4),
    CHARGE(I18nCode.ACCOUNT_TYPE_CHARGE ,5),
    WITHDRAW(I18nCode.ACCOUNT_TYPE_WITHDRAW ,6),
    TRANSFER_OUOT(I18nCode.ACCOUNT_TYPE_TRANSFER_OUT,7),
    TRANSFER_IN(I18nCode.ACCOUNT_TYPE_TRANSFER_IN ,8),
    ISSUE(I18nCode.ACCOUNT_TYPE_ISSUE ,9),
    FEE(I18nCode.ACCOUNT_TYPE_FEE ,10),
    EXCHANGE_OUT_SUB(I18nCode.ACCOUNT_TYPE_EXCHANGE_OUT_SUB ,11),
    EXCHANGE_IN_ADD(I18nCode.ACCOUNT_TYPE_EXCHANGE_IN_ADD ,12),
    EXCHANGE_REJECT_ADD(I18nCode.ACCOUNT_TYPE_EXCHANGE_REJECT_ADD ,13),
    SUB_FROZEN(I18nCode.ACCOUNT_TYPE_SUB_FROZEN,14);

    private String code;
    private int value;

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    AccountTypeEnum(String code, int value) {
        this.code = code;
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (AccountTypeEnum c : AccountTypeEnum.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }

    public static String getCode(Integer status) {
        for (AccountTypeEnum c : AccountTypeEnum.values()) {
            if (c.getValue() == status) {
                return c.getCode();
            }
        }
        return null;
    }

    public static AccountTypeEnum get(Integer status) {
        for (AccountTypeEnum c : AccountTypeEnum.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
