package com.verificer.utils;

import com.verificer.GlobalConfig;

import java.math.BigDecimal;

public class PriceUtils {
    public static final String format(BigDecimal price){
        if(price == null)
            return "";
        return "¥"+price.setScale(GlobalConfig.PREC).toPlainString();
    }
}
