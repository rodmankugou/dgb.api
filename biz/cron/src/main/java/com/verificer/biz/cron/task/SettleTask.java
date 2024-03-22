package com.verificer.biz.cron.task;

import com.verificer.biz.biz.service.BizService;
import com.verificer.biz.cron.config.CronConfig;
import com.verificer.utils.SDateUtil;
import com.verificer.utils.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 触发店铺结算单的生成
 */
@Component
public class SettleTask {
    private static final Logger logger = LoggerFactory.getLogger(SettleTask.class);

    @Autowired
    CronConfig cronConfig;

    @Autowired
    BizService bizService;

    @PostConstruct
    private void run(){

        logger.info("启动Settle Task");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        triggerSettles();
                    } catch (Exception e) {
                        logger.error("触发生成结算单时出现异常，错误信息："+e.getMessage(),e);
                        ThreadUtils.sleep(300* SDateUtil.MS_PER_SEC);
                    }finally {
                        ThreadUtils.sleep(10* SDateUtil.MS_PER_SEC);
                    }
                }
            }
        }).start();
        logger.info("启动Settle Task完毕");



    }

    private void triggerSettles(){
        while (true){
            Long ordId = bizService.trySettle();
            if(ordId == null)
                return ;

            triggerSettle(ordId);
        }

    }

    private void triggerSettle(Long ordId){
        while (true){
            int count = bizService.settle(ordId);
            if(count == 0)
                return ;
        }
    }
}
