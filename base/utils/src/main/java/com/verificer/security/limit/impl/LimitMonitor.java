package com.verificer.security.limit.impl;

import com.verificer.ErrCode;
import com.verificer.common.exception.BaseException;
import com.verificer.security.limit.*;

/**
 * Created by 35336 on 2020/12/29.
 */
public class LimitMonitor implements ILimitMonitor{
    /**
     * 用于保存action的受限记录
     */
    private ILimitMember member;

    public LimitMonitor(ILimitMember member) {
        this.member = member;
    }

    /**
     * 当发生受限事件时调用
     * @param action
     * @param curTime 当前时间
     */
    @Override
    public void onAction(LimitedAction action,Long curTime) {
        ActionCounter actionCounter = member.load(action.getActionId());
        if(actionCounter == null){
            ActionCounter counter = new ActionCounter(action.getActionId(),curTime,1);
            member.save(counter,action.getLimit().getPeriod());
            return;
        }

        //该actionCounter已过期
        if(curTime - action.getLimit().getPeriod()*1000 > actionCounter.getBeginTime()){
            actionCounter.setCount(1);
            actionCounter.setBeginTime(curTime);
        }else {
            actionCounter.setCount(actionCounter.getCount() + 1);
        }
        member.save(actionCounter,action.getLimit().getPeriod());
    }


    /**
     * 返回该事件的剩余不受限次数
     * @param action 如果该action已经处于受限状态，返回0，否则到达触发受限的剩余次数
     * @param curTime 当前时间
     */
    @Override
    public int restUnLimitCount(LimitedAction action,Long curTime) {
        ActionCounter actionCounter = member.load(action.getActionId());
        if(actionCounter == null){
            return action.getLimit().getCount();
        }

        if(curTime - action.getLimit().getPeriod()*1000 > actionCounter.getBeginTime()){
            //该actionCounter已过期
            return action.getLimit().getCount();
        }else {
            int restCount = action.getLimit().getCount() - actionCounter.getCount();
            return  restCount > 0 ? restCount : 0;
        }
    }

    @Override
    public void assertAction(LimitedAction action, Long curTime, String assertFalseMsgCode,Object[] errParams) {
        int restCount = this.restUnLimitCount(action,curTime);
        if(restCount <= 0){
           throw new BaseException(assertFalseMsgCode,errParams);
        }
    }
}
