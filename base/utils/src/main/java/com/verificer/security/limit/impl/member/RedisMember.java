package com.verificer.security.limit.impl.member;

import com.verificer.security.limit.ActionCounter;
import com.verificer.security.limit.ILimitMember;
import com.verificer.utils.RedisUtil;
import com.verificer.utils.SStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 35336 on 2020/12/29.
 */
public class RedisMember implements ILimitMember{
    String serVerion = "1";
    private RedisUtil redisUtil;
    private String keyPrefix;

    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public String serialize(ActionCounter actionCounter){
        StringBuffer buffer = new StringBuffer();
        buffer.append(serVerion)
                .append(",").append(actionCounter.getCount())
                .append(",").append(actionCounter.getBeginTime())
                .append(",").append(actionCounter.getActionId());
        return buffer.toString();
    }

    public ActionCounter unserialize(String str){
        if(SStringUtils.isEmpty(str)){
            return null;
        }
        String[] ss = str.split(",",4);
        if(ss.length < 3){
            return null;
        }

        if(!serVerion.equals(ss[0])){
            //版本号不对
            return null;
        }

        int count = Integer.parseInt(ss[1]);
        long beginTime = Long.parseLong(ss[2]);
        String actionId = ss[3];
        ActionCounter actionCounter = new ActionCounter(actionId,beginTime,count);
        return actionCounter;
    }

    @Override
    public void save(ActionCounter actionCounter,long saveTime) {
        if(actionCounter == null){
            throw new IllegalArgumentException("parameter action can not be null");
        }

        String redisKey = generateRedisKey(actionCounter.getActionId());
        redisUtil.set(redisKey, serialize(actionCounter),saveTime);
    }



    @Override
    public ActionCounter load(String actionId) {
        if (SStringUtils.isEmpty(actionId)) {
            throw new IllegalArgumentException("parameter actionId can not be empty");
        }

        String str = (String) redisUtil.get(generateRedisKey(actionId));
        return unserialize(str);
    }

    private String generateRedisKey(String actionId){
        if(SStringUtils.isAnyEmpty(keyPrefix,actionId)){
            throw new IllegalArgumentException("parameter keyPrefix and actionId must not be empty");
        }
        return keyPrefix+":"+actionId;
    }
}
