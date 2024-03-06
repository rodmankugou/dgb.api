package com.verificer.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum OfferStatusEnums {
    PENDING(1), //
    SUCCESS (2), //
    FAILED(3), //
    CANCELED(4), //
    CHAINING(103), //上链中
    CHAIN_FAILED(201); //上链成功



    private int value;


    public int getValue() {
        return value;
    }

    OfferStatusEnums(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (OfferStatusEnums c : OfferStatusEnums.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


}
