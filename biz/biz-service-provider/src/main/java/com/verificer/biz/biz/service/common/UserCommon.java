package com.verificer.biz.biz.service.common;

import com.verificer.account.itf.AccTools;
import com.verificer.account.itf.BaseAccountService;
import com.verificer.account.itf.IntegralTools;
import com.verificer.beans.account.AccountVo;
import com.verificer.biz.biz.entity.User;
import com.verificer.biz.biz.mapper.UserMapper;
import com.verificer.biz.biz.service.core.user.UserCoreService;
import com.verificer.biz.biz.service.core.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserCommon {

    @Autowired
    UserMapper userMapper;

    @Autowired
    BaseAccountService baseAccountService;

    @Autowired
    UserCoreService userCoreService;

    public String getNickName(Long userId){
        User user = userMapper.selectByPrimaryKey(userId);
        if(user == null)
            throw new RuntimeException("User [id="+userId+"] not exist");
        return user.getNickname();
    }

    public String getNickName(String uid){
        User user = userMapper.selectByUid(uid);
        if(user == null)
            throw new RuntimeException("User [uid="+uid+"] not exist");
        return user.getNickname();
    }

    public String getUid(Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if(user == null)
            throw new RuntimeException("User [id="+userId+"] not exist");
        return user.getUid();
    }


    public User getUserByUid(String uid) {
        return userMapper.selectByUid(uid);
    }

    public User getById(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public AccountVo createIntegralAccIfNeed(User user){
       return baseAccountService.createAccountIfNeed(user.getUid(), AccTools.USR_INTEGRAL,false);
    }

    /**
     * 修改用户积分
     */
    public void updIntegral(User user, boolean isAdd,Integer opType, Long attachId, BigDecimal amount,String title,String subTitle ) {

        String remark = IntegralTools.format(title,subTitle);
        if(user == null)
            throw new RuntimeException("UserCommon.updIntegral, Parameter User can not be null");
        AccountVo accountVo = createIntegralAccIfNeed(user);
        if(isAdd){
            baseAccountService.addAvailable(opType,attachId,accountVo.getId(),amount,remark);
        }else {
            baseAccountService.subAvailable(opType,attachId,accountVo.getId(),amount,remark);
        }

    }

    public void lockByUser(Long userId) {
        userMapper.getAndLock(userId);
    }

    public boolean isMemberUser(Long userId) {
        return userCoreService.userIsMember(userId);
    }
}
