package com.verificer.biz.biz.task;

import com.verificer.biz.biz.config.BizConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PosSyncTask {

    @Autowired
    BizConfig bizConfig;

    @PostConstruct
    private void run(){
        System.out.println("isDebug:" + bizConfig.isDebug());
    }
}
