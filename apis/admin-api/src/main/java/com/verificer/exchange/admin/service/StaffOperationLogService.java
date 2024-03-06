package com.verificer.exchange.admin.service;

/**
 * Created by 35336 on 2021/2/1.
 */
public interface StaffOperationLogService {
    public void addStaffOperationLog(String name, String content, Long staffId, String staffRealName);
}
