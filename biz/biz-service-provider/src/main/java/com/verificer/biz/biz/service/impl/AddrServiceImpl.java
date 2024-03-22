package com.verificer.biz.biz.service.impl;

import com.verificer.biz.biz.entity.Addr;
import com.verificer.biz.biz.mapper.AddrMapper;
import com.verificer.biz.biz.pospay.entity.req.AddMemberReq;
import com.verificer.biz.biz.service.AddrService;
import com.verificer.common.exception.BizErrMsgException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class AddrServiceImpl implements AddrService {
    @Autowired
    AddrMapper mapper;

    @Override
    public Addr getById(Long addrId, Long userId) {

        Addr addr = mapper.selectByPrimaryKey(addrId);
        if(addr == null)
            throw new BizErrMsgException("Address Not exist");
        if(!addr.getUserId().equals(userId))
            throw new BizErrMsgException("Address Not exist");
        return addr;
    }
}
