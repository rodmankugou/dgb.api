package com.verificer.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum TxStatusEnums {
    NotExist(1), //交易池中找不到
    WaitForPack (2), //存在于交易池中，等待被打包
    Failed (3), //已上链，最终还是失败了
    Success (4); //已上链，最终成功了



    private int value;


    public int getValue() {
        return value;
    }

    TxStatusEnums(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (TxStatusEnums c : TxStatusEnums.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


}
