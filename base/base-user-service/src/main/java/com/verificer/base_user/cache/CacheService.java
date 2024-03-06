package com.verificer.base_user.cache;

import com.verificer.utils.RedisUtil;
import com.verificer.utils.SStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 35336 on 2020/12/24.
 */
@Service
public class CacheService {
    @Autowired
    RedisUtil redisUtil;

    public static final String LOGIN_LIMIT_TIME = "loginLimitTime_";
    public static final String ERROR_COUNT_DOWN = "errorCountDown_";
    public static final String ERROR_COUNT = "errorCount_";

    public boolean existLoginTimeLimit(String account){
        return redisUtil.exists(LOGIN_LIMIT_TIME+account);
    }
    public void setLoginTimeLimit(String account,String value,Long expireTime){
        redisUtil.set(LOGIN_LIMIT_TIME+account,value,expireTime);
    }

    public void removeLoginTimeLimit(String account){
        redisUtil.remove(LOGIN_LIMIT_TIME+account);
    }

    public boolean existErrorCountDown(String account){
        return redisUtil.exists(ERROR_COUNT_DOWN+account);
    }
    public void removeErrorCountDown(String account){
        redisUtil.remove(ERROR_COUNT_DOWN+account);
    }

    public void setErrorCountDown(String account,String value, Long expireTime){
        redisUtil.set(ERROR_COUNT_DOWN+account,value,expireTime);
    }

    public boolean existErrorCount(String account){
        return redisUtil.exists(ERROR_COUNT+account);
    }

    public void removeErrorCount(String account){
        redisUtil.remove(ERROR_COUNT+account);
    }

    public Integer getErrorCount(String account){
        String str = (String) redisUtil.get(ERROR_COUNT+account);
        if(str == null){
            return 0;
        }
        if(SStringUtils.isEmptyRedisValue(str.toString())){
            return 0;
        }
        return Integer.parseInt(str);
    }

    public void setErrorCount(String account,String value, Long expireTime){
        redisUtil.set(ERROR_COUNT+account,value,expireTime);
    }

}
