package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum WalletAddressTypeEnum {
    ADMIN(1),
    WEB_USER(2),
    SYS_ACCOUNT(3);


    private int value;


    public int getValue() {
        return value;
    }

    WalletAddressTypeEnum(int value) {
        this.value = value;
    }

    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (WalletAddressTypeEnum c : WalletAddressTypeEnum.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }



    public static WalletAddressTypeEnum get(Integer status) {
        for (WalletAddressTypeEnum c : WalletAddressTypeEnum.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
