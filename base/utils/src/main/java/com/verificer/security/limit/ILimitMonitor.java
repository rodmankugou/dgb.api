package com.verificer.security.limit;

/**
 * 负责管理某些时段内限制频率的事件
 * Created by 35336 on 2020/12/29.
 */
public interface ILimitMonitor {
    /**
     * 当发生受限事件时调用
     * @param action
     * @param curTime 当前时间
     */
    void onAction(LimitedAction action,Long curTime);

    /**
     * 返回该事件的剩余不受限次数
     * @param action 如果该action已经处于受限状态，返回0，否则到达触发受限的剩余次数
     * @param curTime 当前时间
     */
    int restUnLimitCount(LimitedAction action,Long curTime);

    /**
     * 断言某动作未受限，如果受限则抛出异常
     * @param action 动作
     * @param curTime 当前时间
     * @param assertFalseMsgCode i18nCode
     * @param errParams 错误参数
     */
    void assertAction(LimitedAction action, Long curTime, String assertFalseMsgCode,Object[] errParams);
}
