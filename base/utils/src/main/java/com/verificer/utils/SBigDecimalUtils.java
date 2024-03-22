package com.verificer.utils;

import java.math.BigDecimal;

public class SBigDecimalUtils {
    /**
     * 获取真实精度，如2.100,返回1
     * @param number
     * @return
     */
    public static int getRealScale(String number){
        if(number.contains(".")){
            //去掉无效的精度
            while (number.endsWith("0")){
                number = number.substring(0,number.length()-1);
            }
            if(number.endsWith(".")){
                number = number.substring(0,number.length()-1);
            }
        }
        BigDecimal bigDecimal = new BigDecimal(number);
        return bigDecimal.scale();
    }

    public static int getRealScale(BigDecimal number){
        return getRealScale(number.toPlainString());
    }

    /**
     * 设置真实的精度，如1.100000的精度将被设置为1(值1.1)
     * @param decimal
     * @return
     */
    public static BigDecimal setRealScale(BigDecimal decimal){
        int realScale = getRealScale(decimal.toPlainString());
        return decimal.setScale(realScale);
    }

    public static String formatPercent(BigDecimal value,int precision){
        if(value == null )
            return null;

        BigDecimal val2 = value.multiply(new BigDecimal(100)).setScale(precision);
        if(val2.compareTo(BigDecimal.ZERO) == 0)
            return "0%";
        else
            return val2.toPlainString()+"%";
    }

    public static void main(String args[]){
        String a = "0.0100";
        System.out.println(getRealScale(a));
    }
}
