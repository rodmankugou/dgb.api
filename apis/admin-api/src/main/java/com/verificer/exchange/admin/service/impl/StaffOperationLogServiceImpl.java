package com.verificer.exchange.admin.service.impl;

import com.verificer.ErrCode;
import com.verificer.common.exception.BaseException;
import com.verificer.exchange.admin.entity.StaffOperationLog;
import com.verificer.exchange.admin.mapper.StaffOperationLogMapper;
import com.verificer.exchange.admin.service.StaffOperationLogService;
import com.verificer.utils.SStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 35336 on 2021/2/1.
 */
@Service("staffOperationLogService")
public class StaffOperationLogServiceImpl implements StaffOperationLogService {
    @Autowired
    StaffOperationLogMapper staffOperationLogMapper;
    @Override
    public void addStaffOperationLog(String name, String content, Long staffId, String staffRealName) {
        if(SStringUtils.isEmpty(name)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(SStringUtils.isEmpty(content)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(staffId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(SStringUtils.isEmpty(staffRealName)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        StaffOperationLog staffOperationLog = new StaffOperationLog();
        staffOperationLog.setName(name);
        staffOperationLog.setContent(content);
        staffOperationLog.setStaffId(staffId);
        staffOperationLog.setStaffRealName(staffRealName);
        staffOperationLog.setCreateTime(System.currentTimeMillis());
        staffOperationLogMapper.insertSelective(staffOperationLog);
    }
}
