package com.verificer.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SChainAmountUtils {

    public static BigInteger toChainAmount(String amount,int precision){

        return toChainAmount(new BigDecimal(amount),precision);
    }

    public static BigInteger toChainAmount(BigDecimal amount,int precision){
        if(SBigDecimalUtils.getRealScale(amount.toPlainString()) > precision)
            throw new RuntimeException("精度超过限制");

        BigDecimal cAmount = amount.multiply(BigDecimal.TEN.pow(precision)).setScale(0);
        return new BigInteger(cAmount.toPlainString());
    }

    public static BigDecimal toBizAmount(BigInteger chainAmount,int precision){
        BigDecimal amount = new BigDecimal(chainAmount.toString());
        BigDecimal bizAmount = amount.divide(BigDecimal.TEN.pow(precision)) ;

        return bizAmount;
    }

    public static void main(String args[]){
        String amount = "1.2";
        BigInteger chaAmount = toChainAmount(amount,6);
        System.out.println(chaAmount);
        System.out.println(toBizAmount(chaAmount,6));
    }
}
