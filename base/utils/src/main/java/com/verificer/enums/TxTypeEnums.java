package com.verificer.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum TxTypeEnums {
    MINT(1),
    CREATE_SELL(2),
    CREATE_AUCTION(3),
    OFFER(4),
    BUY(5),
    BID(6),
    ACCEPT(7), //接受报价
    AUCTION_CONFIRM(8); //确认拍卖订单




    private int value;


    public int getValue() {
        return value;
    }

    TxTypeEnums(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (TxTypeEnums c : TxTypeEnums.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


}
