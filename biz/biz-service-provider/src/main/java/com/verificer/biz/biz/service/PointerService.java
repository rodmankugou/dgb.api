package com.verificer.biz.biz.service;

import com.verificer.biz.biz.entity.Pointer;

public interface PointerService {
    Pointer getAndLock(String code);
    void upd(Pointer pointer);
}
