package com.verificer.biz.biz.service.core.stock.impl;

import com.verificer.ErrCode;
import com.verificer.biz.beans.vo.revise.req.ReviseFromVo;
import com.verificer.biz.biz.entity.Revise;
import com.verificer.biz.biz.entity.Stock;
import com.verificer.biz.biz.mapper.ReviseMapper;
import com.verificer.biz.biz.service.common.MerCommon;
import com.verificer.biz.biz.service.core.stock.ReviseService;
import com.verificer.biz.biz.service.core.stock.StockCoreService;
import com.verificer.biz.biz.service.feature.FRevise;
import com.verificer.biz.biz.service.feature.FeatureTool;
import com.verificer.common.exception.BaseException;
import com.verificer.utils.check.SCheckUtil;
import io.swagger.annotations.ApiModelProperty;
import javafx.scene.Scene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional(rollbackFor = Exception.class)
public class ReviseServiceImpl implements ReviseService {

    @Autowired
    ReviseMapper mapper;

    @Autowired
    FeatureTool featureTool;

    @Autowired
    StockCoreService stockCoreService;

    @Autowired
    MerCommon merCommon;

    @Override
    public void reviseCreate(ReviseFromVo reqVo) {
        SCheckUtil.notEmpty(reqVo.getId(),"id");
        SCheckUtil.lgThanAndNotNull(reqVo.getCount(),false, BigDecimal.ZERO,"count");
        SCheckUtil.notEmpty(reqVo.getAddFlag(),"addFlag");

        FRevise revise = new FRevise();

        Stock stock = stockCoreService.getById(reqVo.getId());
        if(stock == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        reqVo.setSpecId(stock.getSpecId());
        featureTool.fill(revise,reqVo);
        revise.setStageFlag(stock.getStageFlag());
        revise.setRelId(stock.getRelId());
        revise.setRelName(merCommon.getMerName(revise.getStageFlag(),revise.getRelId()));
        revise.setAddFlag(reqVo.getAddFlag());
        revise.setCount(reqVo.getCount());
        revise.setCreateTime(System.currentTimeMillis());

        mapper.insertSelective(revise);
    }
}
