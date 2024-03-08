package com.verificer.biz.cron;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ThreadPools {

    public static final ScheduledExecutorService OrderChainExecutor = Executors.newScheduledThreadPool(10);
    public static final ScheduledExecutorService LoadMetaExecutor = Executors.newScheduledThreadPool(10);

}
