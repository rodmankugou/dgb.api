package com.verificer.utils;

import java.math.BigDecimal;

public class ParamsChecker {
    public static boolean isDecimalStr(String param , Integer precision){
        try {
            BigDecimal num = new BigDecimal(param);
            if (precision == null){
                return true;
            }
            BigDecimal num1 = num.setScale(precision,BigDecimal.ROUND_DOWN);
            return num.compareTo(num1) == 0;
        }catch (Throwable t){
            return false;
        }
    }

    public static boolean isDecimalStr(String param ){
        try {
            new BigDecimal(param);
            return true;
        }catch (Throwable t){
            return false;
        }
    }

    public static boolean isPositiveDecimalStr(String param , Integer precision){
        try {
            BigDecimal num = new BigDecimal(param);
            if (precision == null){
                return true;
            }
            BigDecimal num1 = num.setScale(precision,BigDecimal.ROUND_DOWN);
            return num.compareTo(num1) == 0 && num.compareTo(BigDecimal.ZERO) > 0;
        }catch (Throwable t){
            return false;
        }
    }

    public static boolean isPositiveDecimalStr(String param ){
        try {
            BigDecimal num = new BigDecimal(param);
            return num.compareTo(BigDecimal.ZERO) > 0;
        }catch (Throwable t){
            return false;
        }
    }
}
