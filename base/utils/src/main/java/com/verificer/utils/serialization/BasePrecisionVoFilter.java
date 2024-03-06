package com.verificer.utils.serialization;

import com.alibaba.fastjson.serializer.ValueFilter;

import java.math.BigDecimal;

/**
 */
public class BasePrecisionVoFilter implements ValueFilter {

    protected int precision;

    public BasePrecisionVoFilter(int precision) {
        this.precision = precision;
    }

    @Override
    public Object process(Object o, String s, Object value) {
        if (value != null && value instanceof BigDecimal) {
            if (precision < 0){
                return "0";
            }

            return ((BigDecimal) value).setScale(precision,BigDecimal.ROUND_DOWN).toPlainString();
        }
        return value;
    }
}
