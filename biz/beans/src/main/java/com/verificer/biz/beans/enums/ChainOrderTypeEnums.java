package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum ChainOrderTypeEnums {
        FIXED_PRICE(1), //售卖
        TIME_AUCTION (2), //限时拍卖
        OPEN_FOR_BIDS(3); //开放式拍卖，没用到



        private int value;


        public int getValue() {
            return value;
        }

        ChainOrderTypeEnums(int value) {
            this.value = value;
        }


        public static boolean isValidStatus(Integer status) {
            if (status == null)
                return false;
            for (ChainOrderTypeEnums c : ChainOrderTypeEnums.values()) {
                if (c.getValue() == status) {
                    return true;
                }
            }
            return false;
        }

        public static ChainOrderTypeEnums  getByValue(int value){
            for (ChainOrderTypeEnums c : ChainOrderTypeEnums.values()) {
                if (c.getValue() == value) {
                    return c;
                }
            }
            return null;
        }


}
