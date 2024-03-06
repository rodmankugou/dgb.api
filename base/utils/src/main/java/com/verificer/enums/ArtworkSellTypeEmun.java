package com.verificer.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum ArtworkSellTypeEmun {
    SELL(1), //售卖
    AUCTION (2); //拍卖




    private int value;


    public int getValue() {
        return value;
    }

    ArtworkSellTypeEmun(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (ArtworkSellTypeEmun c : ArtworkSellTypeEmun.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


}
