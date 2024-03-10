package com.verificer.biz.cron.task;

import com.verificer.biz.biz.service.BizService;
import com.verificer.biz.cron.config.CronConfig;
import com.verificer.utils.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class YbPosSyncTask {
    private static final Logger logger = LoggerFactory.getLogger(YbPosSyncTask.class);

    @Autowired
    CronConfig cronConfig;

    @Autowired
    BizService bizService;

    @PostConstruct
    private void run(){
        logger.info("启动Pos机商品数据同步触发器");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        int count = bizService.handleSyncTask();
                        if(count == 0){
                            ThreadUtils.sleep(100);
                        }
                    } catch (Exception e) {
                        logger.error("同步银豹Pos机器的定时任务调用失败，错误信息："+e.getMessage(),e);
                        ThreadUtils.sleep(1000);
                    }
                }
            }
        }).start();
        logger.info("启动Pos机商品数据同步触发器");

        logger.info("启动Pos订单数据同步触发器");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        int count = bizService.handleSyncTask();
                        if(count == 0){
                            ThreadUtils.sleep(100);
                        }
                    } catch (Exception e) {
                        logger.error("同步银豹Pos机器的定时任务调用失败，错误信息："+e.getMessage(),e);
                        ThreadUtils.sleep(1000);
                    }
                }
            }
        }).start();
        logger.info("启动Pos订单数据同步触发器");

    }
}
