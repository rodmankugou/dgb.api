package com.verificer.biz.biz.service.impl;

import com.verificer.biz.biz.entity.DbgOrderLog;
import com.verificer.biz.biz.mapper.DbgOrderLogMapper;
import com.verificer.biz.biz.service.DbgOrderLogService;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class DbgOrderLogServiceImpl implements DbgOrderLogService {
    @Autowired
    DbgOrderLogMapper mapper;

    public void add(DbgOrderLog log){
        SCheckUtil.notEmpty(log.getOrderId(),"log.OrderId");
        SCheckUtil.notEmpty(log.getOprName(),"log.opName");
        SCheckUtil.notEmpty(log.getOprName(),"log.oprName");
        SCheckUtil.notEmpty(log.getOprId(),"log.oprId");
        SCheckUtil.notEmpty(log.getOpEntry(),"log.opEntry");
        SCheckUtil.notEmpty(log.getOrderStatus(),"log.orderStatus");
        SCheckUtil.notEmpty(log.getCreateTime(),"log.opTime");

        mapper.insertSelective(log);
    }
}
