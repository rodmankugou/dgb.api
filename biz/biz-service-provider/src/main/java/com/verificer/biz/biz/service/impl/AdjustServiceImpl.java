package com.verificer.biz.biz.service.impl;

import com.verificer.ErrCode;
import com.verificer.biz.beans.exceptions.StockInsufficientException;
import com.verificer.biz.beans.vo.AdjustVo;
import com.verificer.biz.beans.vo.req.*;
import com.verificer.biz.biz.mapper.AdjustMapper;
import com.verificer.biz.biz.service.AdjustService;
import com.verificer.biz.biz.service.StockService;
import com.verificer.common.exception.BizErrMsgException;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdjustServiceImpl implements AdjustService {

    @Autowired
    AdjustMapper mapper;

    @Autowired
    StockService stockService;

    @Override
    public List<AdjustVo> adjustPage(AdjustPageVo qryVo) {
        return mapper.page(qryVo);
    }

    @Override
    public int adjustCount(AdjustPageVo qryVo) {
        return mapper.count(qryVo);
    }

    @Override
    public void adjustBatch(AdjustBatchVo formVo) {
        SCheckUtil.notEmpty(formVo.getShopId(),"Shop Id");
        SCheckUtil.notEmpty(formVo.getStageId(),"Stage Id");
        if(formVo.getItems() == null || formVo.getItems().size() == 0)
            return;

        for(AdjustBatchItemVo item : formVo.getItems()){
            SCheckUtil.notEmpty(item.getGoodsId(),"Goods Id");
            SCheckUtil.notEmpty(item.getSpecId(),"Spec Id");
            SCheckUtil.lgThanAndNotNull(item.getCount(),true,0,"Count");
        }

        for(AdjustBatchItemVo item : formVo.getItems()){
            try {
                stockService.addShopStockIfNotExist(formVo.getShopId(),item.getGoodsId(),item.getSpecId());
                if(item.getCount() > 0){
                    stockService.modifyStock(Arrays.asList(new StockUpdVo[]{
                            new StockUpdVo(true,formVo.getShopId(),item.getSpecId(),item.getCount()),
                            new StockUpdVo(false,formVo.getStageId(),item.getSpecId(),item.getCount())
                    }));
                }
            } catch (StockInsufficientException e) {
                throw new RuntimeException(e.getMessage(),e);
            }
        }


    }

    @Override
    public void adjust(AdjustFormVo formVo) {
        SCheckUtil.notEmpty(formVo.getShopId(),"Shop Id");
        SCheckUtil.notEmpty(formVo.getStageId(),"Stage Id");
        SCheckUtil.notEmpty(formVo.getGoodsId(),"Goods Id");
        SCheckUtil.notEmpty(formVo.getSpecId(),"Spec Id");
        SCheckUtil.notEmpty(formVo.getDirection(),"Direction");
        SCheckUtil.lgThanAndNotNull(formVo.getCount(),true,0,"Count");

        if(formVo.getCount() == 0)
            return;

        try {
            stockService.modifyStock(Arrays.asList(new StockUpdVo[]{
                    new StockUpdVo(formVo.getDirection() == 1 ? true : false, formVo.getShopId(),formVo.getSpecId(),formVo.getCount()),
                    new StockUpdVo(formVo.getDirection() == 1 ? false : true,formVo.getStageId(),formVo.getSpecId(),formVo.getCount())
            }));
        } catch (StockInsufficientException e) {
            throw new BizErrMsgException(ErrCode.OP_ADJUST_STAGE_STOCK_NOT_ENOUGH,
                    new Object[]{
                            e.getRefName(),
                            e.getGoodsName(),
                            e.getSpecName(),
                            e.getStockCount()
                    });
        }
    }
}
