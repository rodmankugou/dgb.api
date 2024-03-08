package com.verificer.biz.cron.task;

import com.verificer.biz.cron.config.CronConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PosSyncTask {

    @Autowired
    CronConfig cronConfig;

    @PostConstruct
    private void run(){
        System.out.println("isDebug:" + cronConfig.isDebug());
    }
}
