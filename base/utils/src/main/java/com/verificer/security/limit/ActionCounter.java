package com.verificer.security.limit;

/**
 * 事件记录，记录了acton第一次出现的时间和出现次数
 * Created by 35336 on 2020/12/29.
 */
public class ActionCounter {
    private String actionId;
    /**
     * 某区间内action第一次出现的时间
     */
    private long beginTime;

    /**
     * 在本区间事件出现的次数
     */
    private int count;

    public ActionCounter(String actionId, long beginTime, int count) {
        this.actionId = actionId;
        this.beginTime = beginTime;
        this.count = count;
    }

    public String getActionId() {
        return actionId;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public int getCount() {
        return count;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
