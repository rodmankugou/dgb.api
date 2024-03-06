package com.verificer.security.limit;

/**
 * Created by 35336 on 2020/12/29.
 */
public interface ILimitMember {
    /**
     * 保存action记录
     */
    void save(ActionCounter actionCounter,long saveTime);

    /**
     * 获取action记录
     * @param actionId
     * @return
     */
    ActionCounter load(String actionId);
}
