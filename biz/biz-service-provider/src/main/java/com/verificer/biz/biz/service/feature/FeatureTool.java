package com.verificer.biz.biz.service.feature;

import com.amazonaws.services.dlm.model.Schedule;
import com.verificer.ErrCode;
import com.verificer.biz.beans.vo.feature.FeatureSpec;
import com.verificer.biz.beans.vo.feature.FeatureSpecReq;
import com.verificer.biz.biz.entity.Goods;
import com.verificer.biz.biz.entity.Spec;
import com.verificer.biz.biz.service.common.GoodsCommon;
import com.verificer.common.exception.BaseException;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeatureTool {

    @Autowired
    GoodsCommon goodsCommon;

    public  void fill(FeatureSpec entity, FeatureSpecReq req){
        SCheckUtil.notEmpty(req.getSpecId(),"specId");
        Spec spec = goodsCommon.getSpecIgnoreDel(req.getSpecId());
        Goods goods = goodsCommon.getGoodsIgnoreDel(spec.getGoodsId());

        if(spec == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        if(goods == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        entity.setSpecId(req.getSpecId());
        entity.setSpecName(spec.getName());
        entity.setSpecImg(spec.getImg());
        entity.setGoodsId(spec.getGoodsId());
        entity.setGoodsName(goods.getName());

    }


}
