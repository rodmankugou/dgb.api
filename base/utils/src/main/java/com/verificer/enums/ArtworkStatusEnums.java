package com.verificer.enums;

import com.verificer.I18nCode;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum ArtworkStatusEnums {
    CRETE_TIME_ILLEGAL("",-4), //关闭拍卖期间创建的拍卖
    START_PRICE_TOO_LARGE("",-3),
    START_PRICE_ILLEGAL("",-2),
    REPEAT_AUCTION("",-1),
    IDLE(I18nCode.USER_TYPE_ADMIN ,1), //闲置中
    WAIT_SELL(I18nCode.USER_TYPE_WEB ,2), //待售卖
    ON_SELL(I18nCode.USER_TYPE_WEB ,3), //售卖中
    WAIT_AUCTION(I18nCode.USER_TYPE_BANK ,4), //待拍卖
    ON_AUCTION(I18nCode.USER_TYPE_BANK ,5), //拍卖中
    CREATING_SELL(I18nCode.USER_TYPE_BANK ,21), //拍卖中
    CREATING_AUCTION(I18nCode.USER_TYPE_BANK ,22), //拍卖中
    SELL_TRADING(I18nCode.USER_TYPE_BANK ,31), //拍卖中
    AUCTION_CONFIRMING(I18nCode.USER_TYPE_BANK ,32), //拍卖时间到，待确认
    AUCTION_CANCELING(I18nCode.USER_TYPE_BANK ,34), //流拍，待取消
    REPEAT_AUCTION_REPAIRING(I18nCode.USER_TYPE_BANK ,40), //流拍，待取消
    ON_AUDIT(I18nCode.USER_TYPE_BANK ,101), //审核中
    AUDIT_FAILED(I18nCode.USER_TYPE_BANK ,102), //审核不通过
    AUDIT_SUCCESS(I18nCode.USER_TYPE_BANK ,200), //待上链
    MINT_FAILED(I18nCode.USER_TYPE_BANK ,201); //上链失败

    private String code;
    private int value;

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    ArtworkStatusEnums(String code, int value) {
        this.code = code;
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (ArtworkStatusEnums c : ArtworkStatusEnums.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }

    public static String getCode(Integer status) {
        for (ArtworkStatusEnums c : ArtworkStatusEnums.values()) {
            if (c.getValue() == status) {
                return c.getCode();
            }
        }
        return null;
    }

    public static ArtworkStatusEnums get(Integer status) {
        for (ArtworkStatusEnums c : ArtworkStatusEnums.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
