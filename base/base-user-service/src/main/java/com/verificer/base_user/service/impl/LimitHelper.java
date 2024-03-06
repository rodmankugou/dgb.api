package com.verificer.base_user.service.impl;

import com.verificer.ErrCode;
import com.verificer.base_user.config.ForgetPwdLimitConfig;
import com.verificer.base_user.config.LimitedConfig;
import com.verificer.base_user.config.UsrSrvConfig;
import com.verificer.base_user.constatns.UserConstants;
import com.verificer.common.exception.BaseException;
import com.verificer.security.limit.ILimitMonitor;
import com.verificer.security.limit.Limit;
import com.verificer.security.limit.LimitedAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 35336 on 2020/12/29.
 */
@Service
public class LimitHelper {
    @Autowired
    UsrSrvConfig usrSrvConfig;

    @Autowired
    ILimitMonitor limitMonitor;



    /**
     * 如果忘记密码操作已经受限，则抛出异常，否则返回剩余可重试次数
     * @return
     */
    public int assertForgetPwdOperationNoLimit(String account){
        LimitedConfig cfg = usrSrvConfig.getForgetPwdLimitCfg();
        String actionId = UserConstants.FORGET_PWD_ACTION_PREFIX + account;
        LimitedAction action = new LimitedAction(actionId,new Limit(cfg.getPeriod(),cfg.getCount()));
        int restCount = limitMonitor.restUnLimitCount(action,System.currentTimeMillis());
        if(restCount == 0){
            Object[] errParam = {cfg.getPeriod()/60};
            throw new BaseException(ErrCode.FORGET_PWD_CODE_ERR_LIMITED,errParam);
        }
        return restCount;
    }

    /**
     * 当忘记密码场景发生验证码校验失败时调用，记录错误次数
     * @param account
     */
    public void onForgetPwdCodeVerifyFailed(String account) {
        LimitedConfig cfg = usrSrvConfig.getForgetPwdLimitCfg();
        String actionId = UserConstants.FORGET_PWD_ACTION_PREFIX + account;
        LimitedAction action = new LimitedAction(actionId,new Limit(cfg.getPeriod(),cfg.getCount()));

        limitMonitor.onAction(action,System.currentTimeMillis());
    }





    /**
     * 如果登录操作已经受限，则抛出异常，否则返回剩余可重试次数
     * @return
     */
    public int assertLoginPwdErrorNoLimit(String account){
        LimitedConfig cfg = usrSrvConfig.getLoginPwdErrLmtCfg();
        String actionId = UserConstants.LOGIN_PWD_ERR_ACTION_PREFIX + account;
        LimitedAction action = new LimitedAction(actionId,new Limit(cfg.getPeriod(),cfg.getCount()));
        int restCount = limitMonitor.restUnLimitCount(action,System.currentTimeMillis());
        if(restCount == 0){
            Object[] errParam = {cfg.getPeriod()/60};
            throw new BaseException(ErrCode.LOGIN_PWD_ERR_LIMITED,errParam);
        }
        return restCount;
    }

    /**
     * 密码登录场景发生密码错误时调用，记录错误次数
     * @param account
     */
    public void onLoginPwdError(String account) {
        LimitedConfig cfg = usrSrvConfig.getLoginPwdErrLmtCfg();
        String actionId = UserConstants.LOGIN_PWD_ERR_ACTION_PREFIX + account;
        LimitedAction action = new LimitedAction(actionId,new Limit(cfg.getPeriod(),cfg.getCount()));

        limitMonitor.onAction(action,System.currentTimeMillis());
    }
}
