package com.verificer.biz.biz.service.common;

import com.verificer.ErrCode;
import com.verificer.biz.biz.entity.Stage;
import com.verificer.biz.biz.mapper.StageMapper;
import com.verificer.common.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StageCommon {
    @Autowired
    StageMapper mapper;

    public Stage getById(String id){
        return mapper.selectByPrimaryKey(id);
    }

    public String getName(String id){
        Stage stage = mapper.selectByPrimaryKey(id);
        if(stage == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        return stage.getName();
    }
}
