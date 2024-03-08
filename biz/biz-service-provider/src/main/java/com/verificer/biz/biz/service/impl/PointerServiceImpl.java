package com.verificer.biz.biz.service.impl;

import com.verificer.biz.biz.entity.Pointer;
import com.verificer.biz.biz.mapper.PointerMapper;
import com.verificer.biz.biz.service.PointerService;
import com.verificer.utils.check.SCheckUtil;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class PointerServiceImpl implements PointerService {

    @Autowired
    private PointerMapper mapper;

    @Override
    public Pointer getAndLock(String code) {
        SCheckUtil.notEmpty(code,"Code");

        Pointer pointer = mapper.selectByPrimaryKeyForUpd(code);
        return pointer;
    }

    @Override
    public void upd(Pointer pointer) {
        SCheckUtil.notEmpty(pointer.getCode(),"Code");
        SCheckUtil.notEmpty(pointer.getPointer(),"Pointer");
        SCheckUtil.notEmpty(pointer.getUpdTime(),"Upd Time");

        mapper.updateByPrimaryKey(pointer);
    }
}
