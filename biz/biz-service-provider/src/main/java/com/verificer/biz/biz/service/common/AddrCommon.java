package com.verificer.biz.biz.service.common;

import com.verificer.biz.biz.entity.Addr;
import com.verificer.biz.biz.mapper.AddrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddrCommon {
    @Autowired
    AddrMapper mapper;

    public Addr getAddr(Long id){
        Addr addr =  mapper.selectByPrimaryKey(id);
        if(addr == null)
            throw new RuntimeException("Address [id="+id+"] not exist");
        return addr;
    }
}
