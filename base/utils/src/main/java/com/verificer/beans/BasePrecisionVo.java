package com.verificer.beans;

import java.io.Serializable;

/**
 * 精度vo基类
 */
public class BasePrecisionVo implements Serializable {

    protected int precision;

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }
}
