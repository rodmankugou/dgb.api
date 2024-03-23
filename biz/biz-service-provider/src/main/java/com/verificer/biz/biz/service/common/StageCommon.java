package com.verificer.biz.biz.service.common;

import com.verificer.biz.biz.entity.Stage;
import com.verificer.biz.biz.mapper.StageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StageCommon {
    @Autowired
    StageMapper mapper;

    public Stage getById(String id){
        return mapper.selectByPrimaryKey(id);
    }
}
