package com.verificer.security.login;

/**
 * Created by 35336 on 2020/12/29.
 */
public interface ILoginCache {
    /**
     * 将数据缓存，指定缓存有效时间
     * @param key
     * @param value
     * @param saveTime
     */
    void put(String key,String value,long saveTime);

    /**
     * 从缓存中加载数据
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 从缓存中删除
     * @param key
     */
    void remove(String key);

    /**
     * 增加缓存的存活时间
     * @param key
     * @param second
     */
    public void makeMoreTime(String key, Long second);
}
