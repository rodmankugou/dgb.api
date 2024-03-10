package com.verificer.biz.biz.service;

public interface PosSyncTaskService {
    void addTask(String shopId,Long relId,int type,String reqJson);

    /**
     * 处理银豹数据同步任务
     * @return
     */
    int handlePosGoodsSyncTask();

    /**
     * 处理银豹数据订单同步任务
     * @return
     */
    int handlePosOrderSyncTask(String shopId) throws Exception;
}
