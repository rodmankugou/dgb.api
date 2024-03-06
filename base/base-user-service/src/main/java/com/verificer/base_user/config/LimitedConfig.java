package com.verificer.base_user.config;

/**
 * Created by 35336 on 2020/12/30.
 */
public class LimitedConfig {
    //时间区间
    private long period;
    //最大允许的验证码错误次数
    private int count;

    public LimitedConfig(long period, int count) {
        this.period = period;
        this.count = count;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
