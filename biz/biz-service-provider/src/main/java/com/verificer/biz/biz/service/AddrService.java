package com.verificer.biz.biz.service;

import com.verificer.biz.biz.entity.Addr;

public interface AddrService{
    Addr getById(Long addrId, Long userId);
}
