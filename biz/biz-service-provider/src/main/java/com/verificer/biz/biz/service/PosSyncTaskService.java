package com.verificer.biz.biz.service;

public interface PosSyncTaskService {
    void addTask(String shopId,int type,String reqJson);

    /**
     * 处理银豹数据同步任务
     * @return
     */
    int handleSyncTask();
}
