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
}
