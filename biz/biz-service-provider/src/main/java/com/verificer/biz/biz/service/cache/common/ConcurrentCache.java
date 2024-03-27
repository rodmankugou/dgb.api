package com.verificer.biz.biz.service.cache.common;

import com.verificer.biz.biz.service.cache.common.ICache;
import com.verificer.biz.biz.service.cache.common.ICacheFilter;
import com.verificer.biz.biz.service.cache.common.RefreshableCache;
import com.verificer.biz.biz.service.cache.exception.CacheInitFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class ConcurrentCache<K,V> implements ICache<K,V> {
    private static final Logger logger = LoggerFactory.getLogger(ConcurrentCache.class);

    /**
     * 读写锁
     */
    private final ReentrantReadWriteLock L = new ReentrantReadWriteLock();
    private final Lock WLock = L.writeLock();
    private final Lock RLock = L.readLock();


    public ConcurrentCache() throws CacheInitFailedException {
        try {
            refresh();
        } catch (Exception e) {
            throw new CacheInitFailedException(e.getMessage(),e);
        }
    }


    /**
     * 提前准备新的缓存数据
     * @return 返回新的缓存数据，后续该返回值将作为参数调用refresh0(方法)
     * @throws Exception
     */
    protected abstract Map<String,Object> preRefresh() throws Exception;

    /**
     * 替换缓存数据
     * @param preData
     * @throws Exception
     */
    protected abstract void refresh0(Map<String, Object> preData) throws Exception;

    /**
     * 获取缓存标识
     * @return
     */
    abstract String getIdentity();

    protected abstract V get0();

    protected abstract List<V> all0();

    protected abstract  List<V> filter0(ICacheFilter<K, V> filter);


    @Override
    public V get(K key) {
        RLock.lock();
        try {
            return get0();
        } finally {
            RLock.unlock();
        }
    }

    @Override
    public List<V> all() {
        RLock.lock();
        try {
            return all0();
        } finally {
            RLock.unlock();
        }
    }

    @Override
    public List<V> filter(ICacheFilter<K, V> filter) {
        RLock.lock();
        try {
            return filter0(filter);
        } finally {
            RLock.unlock();
        }
    }

    public void refresh() throws Exception{
        WLock.lock();
        try {
            Map<String,Object> data = preRefresh();
            refresh0(data);
        } finally {
            WLock.unlock();
        }
    }
}
