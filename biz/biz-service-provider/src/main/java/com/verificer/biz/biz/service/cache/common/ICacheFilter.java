package com.verificer.biz.biz.service.cache.common;

public interface ICacheFilter<K,V> {

    /**
     * 目标item是否匹配
     * @param key
     * @param val
     * @return
     */
    public boolean isMatch(K key,V val);
}
