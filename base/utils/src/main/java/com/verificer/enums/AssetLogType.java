package com.verificer.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum AssetLogType {
    MINT(1), //铸币
    DESTROY (2), //销毁
    TRAN (3); //转账


    private int value;


    public int getValue() {
        return value;
    }

    AssetLogType(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (AssetLogType c : AssetLogType.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


}
