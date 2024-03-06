package com.verificer.utils;

import com.verificer.ErrCode;
import com.verificer.common.exception.BaseException;

import java.math.BigDecimal;

public class SMathUtils {
    /**
     * 计算环比赠跌幅
     */
    private static BigDecimal calRingRate(BigDecimal before, BigDecimal after,Integer precision){
        if(precision == null)
            throw new BaseException(ErrCode.PARAMS_ERR);
        if(before == null || after == null)
            return null;

        if(BigDecimal.ZERO.equals(before.stripTrailingZeros()))
            return null;

        BigDecimal rst = (after.subtract(before)).divide(before);
        rst.setScale(precision+2);
        return rst;
    }

    /**
     * 计算环比赠跌幅，+24.5%格式
     * @param before
     * @param after
     * @param precision
     * @return
     */
    public static String calRingRateClassic(BigDecimal before, BigDecimal after,Integer precision){
        BigDecimal rst = calRingRate(before,after,precision);
        if(rst == null)
            return null;

        if(rst.compareTo(BigDecimal.ZERO) >= 0)
            return new StringBuffer().append("+").append(rst.toPlainString()).append("%").toString();
        else
            return new StringBuffer().append("-").append(rst.toPlainString()).append("%").toString();

    }
}
