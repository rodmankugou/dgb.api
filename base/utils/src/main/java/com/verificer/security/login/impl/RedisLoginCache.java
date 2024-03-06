package com.verificer.security.login.impl;

import com.verificer.security.login.ILoginCache;
import com.verificer.utils.RedisUtil;

import java.io.Serializable;

/**
 * Created by 35336 on 2020/12/29.
 */
public class RedisLoginCache implements ILoginCache{
    private RedisUtil redisUtil;

    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }


    /**
     * 考虑到redis的key过期策略，在此加上过期时间，但没有考虑服务器间的时间差异
     */
    class ObjectWrapper implements Serializable{
        String value;
        long expireTime;


        public long getExpireTime() {
            return expireTime;
        }


        public String serialize(){
            StringBuffer sb = new StringBuffer();
            return sb.append(expireTime).append(":").append(value).toString();
        }
        public void unSerialize(String text){
            String[] ss = text.split(":",2);
            this.expireTime = Long.parseLong(ss[0]);
            this.value = ss[1];
        }
    }


    @Override
    public void put(String key, String value, long saveTime) {
        ObjectWrapper obj = new ObjectWrapper();
        obj.value = value;
        obj.expireTime = System.currentTimeMillis() + saveTime*1000;
        redisUtil.set(key,obj.serialize(),saveTime);
    }

    @Override
    public String get(String key) {
        String text = (String) redisUtil.get(key);
        if(text == null){
            return null;
        }

        ObjectWrapper obj = new ObjectWrapper();
        obj.unSerialize(text);
        if(obj.expireTime < System.currentTimeMillis()){
            return null;
        }
        return obj.value;
    }

    @Override
    public void remove(String key) {
        redisUtil.remove(key);
    }


    @Override
    public void makeMoreTime(String key, Long second) {
        redisUtil.makeMoreTime(key,second.intValue());
    }
}
