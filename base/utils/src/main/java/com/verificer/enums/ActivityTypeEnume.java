package com.verificer.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum ActivityTypeEnume {
    CREATE(1), //收藏中
    BUY(2),
    SELL(3),
    TRANSFER(4), //站外转移
    LISTED(4); // 上架，将NFT上架售卖

    private int value;


    public int getValue() {
        return value;
    }

    ActivityTypeEnume(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (ActivityTypeEnume c : ActivityTypeEnume.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


}
