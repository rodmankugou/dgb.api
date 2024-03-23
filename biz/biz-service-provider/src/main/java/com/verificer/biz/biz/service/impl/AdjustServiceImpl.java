package com.verificer.biz.biz.service.impl;

import com.verificer.ErrCode;
import com.verificer.biz.beans.enums.AdjShortType;
import com.verificer.biz.beans.enums.AdjStatus;
import com.verificer.biz.beans.enums.AdjType;
import com.verificer.biz.beans.enums.StockOpType;
import com.verificer.biz.beans.exceptions.StockInsufficientException;
import com.verificer.biz.beans.vo.AdjustVo;
import com.verificer.biz.beans.vo.req.*;
import com.verificer.biz.beans.vo.req.adjust.AdjFormVo;
import com.verificer.biz.biz.entity.*;
import com.verificer.biz.biz.mapper.AdjustMapper;
import com.verificer.biz.biz.service.*;
import com.verificer.biz.biz.service.core.stock.StockCoreService;
import com.verificer.common.exception.BaseException;
import com.verificer.common.exception.BizErrMsgException;
import com.verificer.utils.SBeanUtils;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdjustServiceImpl implements AdjustService {

    @Autowired
    AdjustMapper mapper;

    @Autowired
    StockCoreService stockCoreService;

    @Autowired
    StageService stageService;

    @Autowired
    ShopService shopService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    SpecService specService;

    @Autowired
    ShopGoodsService shopGoodsService;

    @Override
    public List<AdjustVo> adjustPage(AdjustPageVo qryVo) {
        List<Adjust> list = mapper.page(qryVo);
        List<AdjustVo> voList = new LinkedList<>();
        for(Adjust adj : list){
            AdjustVo vo = new AdjustVo();
            SBeanUtils.copyProperties2(adj,vo);
            if(adj.getType() == AdjType.STAGE_OUT.getValue()
                    || adj.getType() == AdjType.SHOP_IN.getValue()
            ){
                vo.setStageName(adj.getFromName());
                vo.setStageId(adj.getFromId());
                vo.setShopName(adj.getToName());
                vo.setShopId(adj.getToId());
            }else if(adj.getType() == AdjType.STAGE_IN.getValue()
                    || adj.getType() == AdjType.SHOP_OUT.getValue()){
                vo.setStageName(adj.getToName());
                vo.setStageId(adj.getToId());
                vo.setShopName(adj.getFromName());
                vo.setShopId(adj.getFromId());
            }else if(adj.getType() == AdjType.STAGE_SUPPLY.getValue()){
                vo.setStageName(adj.getToName());
                vo.setStageId(adj.getToId());
            }
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public int adjustCount(AdjustPageVo qryVo) {
        return mapper.count(qryVo);
    }

    @Override
    public void adjustBatch(List<AdjFormVo> list) {

        for(AdjFormVo vo : list){
            if(!stockCoreService.isStockExist(vo.getToId(),vo.getGoodsId(),vo.getSpecId())){
                shopGoodsService.addGoodsIfNotExist(vo.getToId(),vo.getGoodsId(),vo.getSpecId());
            }
            stockCoreService.addShopStockIfNotExist(vo.getToId(),vo.getGoodsId(),vo.getSpecId());
            adjust(vo);

        }
    }

    @Override
    public void adjust(AdjFormVo formVo) {
        SCheckUtil.notEmpty(formVo.getShortType(),"Shop Type");
        if(formVo.getShortType() != AdjShortType.STAGE_SUPPLY.getValue())
            SCheckUtil.notEmpty(formVo.getFromId(),"From Id");
        SCheckUtil.notEmpty(formVo.getToId(),"To Id");

        SCheckUtil.notEmpty(formVo.getGoodsId(),"Goods Id");
        SCheckUtil.notEmpty(formVo.getSpecId(),"Spec Id");
        SCheckUtil.lgThanAndNotNull(formVo.getCount(),true, BigDecimal.ZERO,"Count");

        if(formVo.getCount().compareTo(BigDecimal.ZERO) == 0)
            return;

        if(isFinish(formVo)){
            List<StockUpdVo> updVos = new LinkedList<>();
            if(formVo.getShortType() == AdjShortType.STAGE_SUPPLY.getValue()){
                updVos.add(new StockUpdVo(true,formVo.getToId(),formVo.getSpecId(),formVo.getRealCount(), StockOpType.ADJUST_IN.getValue(),"调货"));
            }else if(formVo.getShortType() == AdjShortType.SHOP_TO_STAGE.getValue()
                    || formVo.getShortType() == AdjShortType.STAGE_TO_SHOP.getValue()
            ){
                updVos.add(new StockUpdVo(true,formVo.getToId(),formVo.getSpecId(),formVo.getRealCount() ,StockOpType.ADJUST_IN.getValue(),"调货"));
                updVos.add(new StockUpdVo(false,formVo.getFromId(),formVo.getSpecId(),formVo.getRealCount(), StockOpType.ADJUST_OUT.getValue(),"调货"));

            }else {
                throw new BizErrMsgException("error short type");
            }

            try {
                stockCoreService.modifyStock(updVos);
            } catch (StockInsufficientException e) {
                throw new BaseException(ErrCode.OP_ADJUST_STAGE_STOCK_NOT_ENOUGH,
                        new Object[]{
                                e.getRefName(),
                                e.getGoodsName(),
                                e.getSpecName(),
                                e.getStockCount()
                        });
            }
        }

        String fromName = null;
        String toName = null;
        int shortType = formVo.getShortType();

        if(shortType == AdjShortType.STAGE_TO_SHOP.getValue()) {
            Stage stage = stageService.getById(formVo.getFromId());
            Shop shop = shopService.getById(formVo.getToId());
            fromName = stage.getName();
            toName = shop.getName();
        }else if(shortType == AdjShortType.SHOP_TO_STAGE.getValue()){
            Stage stage = stageService.getById(formVo.getToId());
            Shop shop = shopService.getById(formVo.getFromId());
            fromName = shop.getName();
            toName = stage.getName();
        }else if(shortType == AdjShortType.STAGE_SUPPLY.getValue()){
            Stage stage = stageService.getById(formVo.getToId());
            toName = stage.getName();
        }else {
            throw new BizErrMsgException("Error short type");
        }

        if(!SStringUtils.isEmpty(formVo.getFromId())){
            Adjust from = createAdj(formVo,fromName,toName);
            fillFrom(from,formVo,fromName,toName);
            mapper.insert(from);
        }

        Adjust to = createAdj(formVo,fromName,toName);
        fillTo(to,formVo,fromName,toName);
        mapper.insert(to);
        return;
    }

    private Adjust createAdj(AdjFormVo formVo,String fromName,String toName){
        Adjust adj = new Adjust();
        adj.setStatus(isFinish(formVo) ? AdjStatus.FINISH.getValue() : AdjStatus.WAIT_CONFIRM.getValue() );

        adj.setFromId(formVo.getFromId());
        adj.setFromName(fromName);
        adj.setToId(formVo.getToId());
        adj.setToName(toName);
        adj.setGoodsId(formVo.getGoodsId());
        adj.setSpecId(formVo.getSpecId());
        adj.setCount(formVo.getCount());
        adj.setRealCount(formVo.getRealCount());
        adj.setRemark(formVo.getRemark());
        adj.setCreateTime(System.currentTimeMillis());

        Goods goods = goodsService.getById(adj.getGoodsId());
        Spec spec  = specService.getById(adj.getSpecId());
        adj.setGoodsName(goods.getName());
        adj.setSpecName(spec.getName());
        return adj;
    }

    /**
     * 入方记录
     * @return
     */
    private void fillTo(Adjust adj,AdjFormVo formVo,String fromName,String toName){
        adj.setMerName(toName);
        adj.setMerId(formVo.getToId());
        int shortType = formVo.getShortType();

        if(shortType == AdjShortType.STAGE_TO_SHOP.getValue()) {
            adj.setType(AdjType.SHOP_IN.getValue());
        }else if(shortType == AdjShortType.SHOP_TO_STAGE.getValue()){
            adj.setType(AdjType.STAGE_IN.getValue());
        }else if(shortType == AdjShortType.STAGE_SUPPLY.getValue()){
            adj.setType(AdjType.STAGE_SUPPLY.getValue());
        }else {
            throw new BizErrMsgException("Error short type");
        }

        adj.setStatus(isFinish(formVo) ? AdjStatus.FINISH.getValue() : AdjStatus.WAIT_CONFIRM.getValue() );
    }

    /**
     * 出方记录
     */
    private void fillFrom(Adjust adj,AdjFormVo formVo,String fromName,String toName){
        adj.setMerName(fromName);
        adj.setMerId(formVo.getFromId());

        int shortType = formVo.getShortType();

        if(shortType == AdjShortType.STAGE_TO_SHOP.getValue()) {
            adj.setType(AdjType.STAGE_OUT.getValue());
        }else if(shortType == AdjShortType.SHOP_TO_STAGE.getValue()){
            adj.setType(AdjType.SHOP_OUT.getValue());
        }else if(shortType == AdjShortType.STAGE_SUPPLY.getValue()){
            throw new BizErrMsgException("Error short type");
        }else {
            throw new BizErrMsgException("Error short type");
        }
        adj.setStatus(isFinish(formVo) ? AdjStatus.FINISH.getValue() : AdjStatus.WAIT_CONFIRM.getValue() );
    }

    private boolean isFinish(AdjFormVo formVo){
        return true;
    }
}
