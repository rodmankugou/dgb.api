package com.verificer.biz.biz.service.cache.common;

import java.util.List;

public interface ICache<K,V> {
    V get(K key);
    List<V> all();
    List<V> filter(ICacheFilter<K,V> filter);
}
