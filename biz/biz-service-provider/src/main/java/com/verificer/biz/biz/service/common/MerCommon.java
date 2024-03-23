package com.verificer.biz.biz.service.common;

import com.verificer.biz.beans.enums.MerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MerCommon {
    @Autowired
    ShopCommon shopCommon;
    @Autowired
    StageCommon stageCommon;

    public  String getMerName(boolean stageFlag,String relId){
        if(stageFlag){
            return stageCommon.getName(relId);
        }else {
            return shopCommon.getName(relId);
        }
    }

    public String getMerName(Integer merType,String relId){

        return getMerName(MerType.STAGE.getValue() == merType ? true : false, relId);
    }
}
