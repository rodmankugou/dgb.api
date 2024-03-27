package com.verificer.biz.biz.service.cache.common;

import com.verificer.biz.biz.service.cache.exception.CacheInitFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 可定时更新并且线程安全的cache
 */
public abstract class RefreshableCache<K,V> extends ConcurrentCache<K,V> {
    private static final Logger logger = LoggerFactory.getLogger(RefreshableCache.class);
    /**
     * 用于执行更新缓存任务的线程池
     */
    private ScheduledExecutorService threadPool;

    /**
     * 刷新周期，单位毫秒
     */
    private long refreshPeriodMs;


    public RefreshableCache(long refreshPeriodMs,ScheduledExecutorService refreshThreadPool) throws CacheInitFailedException {
        this.refreshPeriodMs = refreshPeriodMs;
        this.threadPool = refreshThreadPool;
        if(this.refreshPeriodMs < 0)
            throw new CacheInitFailedException("Parameter refreshPeriodMs can not less than 0 ");

        if(this.threadPool == null)
            throw new CacheInitFailedException("Parameter refreshThreadPool can not be null ");



        threadPool.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    refresh();
                } catch (Exception e) {
                    logger.error("Cache refresh failed [ID="+getIdentity()+"],errMsg :"+e.getMessage(),e);
                }
            };
        },refreshPeriodMs,refreshPeriodMs, TimeUnit.MILLISECONDS);
    }



    /**
     * 获取缓存标识
     * @return
     */
    protected abstract String getIdentity();

}
