package com.verificer.utils.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class RetryExecuteTask {

    public static   <T> T retryExecuteTask(int maxRetryCount, Callable<T> task, String taskName,Logger logger) {
        int count = 0;
        while (true){
            if(count >= maxRetryCount)
                throw new RuntimeException("重试了"+maxRetryCount+"次执行"+taskName+"任务，仍无法获取期望的结果");
            count++;
            try {
                T resp = task.call();
                return  resp;
            } catch (Exception e) {
                logger.error("执行"+taskName+"任务时发生异常，异常信息："+e.getMessage(),e);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException interruptedException) {

                }

            }
        }
    }
}
