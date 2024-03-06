package com.verificer.security.limit;

/**
 * 表示一个受限的事件，该事件在一个时间区间最多只能出现几次
 * Created by 35336 on 2020/12/29.
 */
public class LimitedAction {
    private String actionId;
    private Limit limit;

    public LimitedAction(String actionId, Limit limit) {
        this.actionId = actionId;
        this.limit = limit;
    }

    /**
     * 获取受限描述
     * @return
     */
    public Limit getLimit() {
        return limit;
    }

    /**
     * 获取action标识
     * @return
     */
    public String getActionId(){
        return actionId;
    }

}
