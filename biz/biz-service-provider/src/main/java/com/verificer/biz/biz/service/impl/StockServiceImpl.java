package com.verificer.biz.biz.service.impl;

import com.verificer.biz.biz.entity.Stage;
import com.verificer.biz.biz.entity.Stock;
import com.verificer.biz.biz.mapper.StockMapper;
import com.verificer.biz.biz.service.StageService;
import com.verificer.biz.biz.service.StockService;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class StockServiceImpl implements StockService {

    @Autowired
    StockMapper mapper;

    @Autowired
    StageService stageService;

    @Override
    public void addStageStockIfNotExist(Long goodsId, Long specId, List<Long> stageIds) {
        for(Long stageId : stageIds){
            Stage stage = stageService.getById(stageId);
            if(stage == null)
                throw new RuntimeException("Stage not exist!,id="+stageId);

            addIfNotExist(goodsId,specId,true,stageId,stage.getUuid(),0);
        }
    }

    private void mCheck(Stock e){
        SCheckUtil.notEmpty(e.getGoodsId(),"stock.GoodsId");
        SCheckUtil.notEmpty(e.getSpecId(),"stock.SpecId");
        SCheckUtil.notEmpty(e.getStageFlag(),"stock.StageFlag");
        SCheckUtil.notEmpty(e.getRelId(),"stock.RelId");
        SCheckUtil.notEmpty(e.getRelUuid(),"stock.RelUuid");
        SCheckUtil.lgThan(e.getCount(),true,0,"stock.Count");
    }


    private void addIfNotExist(Long goodsId,Long specId,Boolean stageFlag,Long refId,String refUuid,Integer count ){
        Stock old = mapper.selectByRefUuid(refUuid);
        if(old != null)
            return;
        Stock e = new Stock();
        e.setGoodsId(goodsId);
        e.setSpecId(specId);
        e.setStageFlag(stageFlag);
        e.setStageFlag(stageFlag);
        e.setRelId(refId);
        e.setRelUuid(refUuid);
        e.setCount(count);

        mCheck(e);
    }
}
