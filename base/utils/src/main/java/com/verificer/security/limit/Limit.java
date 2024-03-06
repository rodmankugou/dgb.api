package com.verificer.security.limit;

import java.io.Serializable;

/**
 * 用于描述某个动作的限制，在某个时间段内最多只能发生几次
 * Created by 35336 on 2020/12/29.
 */
public class Limit implements Serializable {
    long period;
    int count;

    public Limit(long period, int count) {
        this.period = period;
        this.count = count;
    }

    public long getPeriod() {
        return period;
    }


    public int getCount() {
        return count;
    }


}
