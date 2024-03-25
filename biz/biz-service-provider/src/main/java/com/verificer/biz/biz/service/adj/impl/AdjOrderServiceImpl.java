package com.verificer.biz.biz.service.adj.impl;

import com.verificer.ErrCode;
import com.verificer.beans.num.NumGenerator;
import com.verificer.biz.beans.constants.BizConst;
import com.verificer.biz.beans.enums.*;
import com.verificer.biz.beans.exceptions.StockInsufficientException;
import com.verificer.biz.beans.vo.adjust.AdjOrderVo;
import com.verificer.biz.beans.vo.adjust.req.*;
import com.verificer.biz.beans.vo.req.StockUpdVo;
import com.verificer.biz.biz.entity.AdjustItem;
import com.verificer.biz.biz.entity.AdjustOrder;
import com.verificer.biz.biz.entity.Shop;
import com.verificer.biz.biz.entity.Stage;
import com.verificer.biz.biz.mapper.AdjustOrderMapper;
import com.verificer.biz.biz.service.adj.AdjItemService;
import com.verificer.biz.biz.service.adj.AdjOrderService;
import com.verificer.biz.biz.service.adj.notify.IAdjustListener;
import com.verificer.biz.biz.service.adj.notify.events.AdjEvent;
import com.verificer.biz.biz.service.common.ShopCommon;
import com.verificer.biz.biz.service.common.StageCommon;
import com.verificer.biz.biz.service.core.stock.StockCoreService;
import com.verificer.common.exception.BaseException;
import com.verificer.common.exception.BizErrMsgException;
import com.verificer.designpatterns.listener.ConcurrentNotifier;
import com.verificer.utils.reflect.SBeanUtils;
import com.verificer.utils.UuidUtils;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdjOrderServiceImpl implements AdjOrderService {

    @Autowired
    AdjustOrderMapper mapper;

    @Autowired
    ShopCommon shopCommon;

    @Autowired
    StageCommon stageCommon;

    @Autowired
    AdjItemService adjItemService;

    @Autowired
    StockCoreService stockCoreService;

    private ConcurrentNotifier<IAdjustListener, AdjEvent> notifier = new ConcurrentNotifier<IAdjustListener, AdjEvent>() {
        @Override
        protected void trigger(IAdjustListener listener, AdjEvent event) {
            listener.onEvent(event);
        }
    };

    @Override
    public void addListener(IAdjustListener listener){
        notifier.addListener(listener);
    }

    private NumGenerator ordNumGenerator = new NumGenerator() {
        @Override
        public boolean isNumExist(String num) {
            return mapper.selectByOrdNum(num) != null;
        }
    };

    private AdjOrderVo toVo(AdjustOrder e){
        if(e == null)
            return  null;
        AdjOrderVo vo = new AdjOrderVo();
        SBeanUtils.copyProperties2(e,vo);
        return vo;
    }

    private List<AdjOrderVo> toVoList(List<AdjustOrder> list){

        List<AdjOrderVo> voList = new LinkedList<>();
        for(AdjustOrder e : list){
            voList.add(toVo(e));
        }
        return voList;
    }

    @Override
    public List<AdjOrderVo> adjOrdPage(AdjOrderQryVo qryVo) {
        List<AdjustOrder> list = mapper.page(qryVo);
        return toVoList(list);
    }

    @Override
    public int adjOrdCount(AdjOrderQryVo qryVo) {
        return mapper.count(qryVo);
    }



    @Override
    public void adjOrdCreate(AdjOrdFormVo reqVo) {
        SCheckUtil.notEmpty(reqVo.getFromId(),"From ID");
        SCheckUtil.notEmpty(reqVo.getToId(),"To ID");
        SCheckUtil.notEmpty(reqVo.getToType(),"To Type");
        adjItemService.checkParams(reqVo.getItems());

        //过滤数量为0的item
        List<AdjItemFormVo> items = new LinkedList<>();
        for(AdjItemFormVo item : reqVo.getItems()){
            if(item.getCount().compareTo(BigDecimal.ZERO) > 0)
                items.add(item);
        }
        reqVo.setItems(items);

        if(isOthers(reqVo.getToId()))
                throw new BizErrMsgException("Can not adjust goods to OTHERS");

        String fromName = getName(true,reqVo.getFromType(),reqVo.getFromId());
        String toName = getName(false, reqVo.getToType(),reqVo.getToId());
        AdjustOrder o = new AdjustOrder();
        o.setOrdNum(UuidUtils.newUuid());
        o.setSpecCount(reqVo.getItems().size());
        o.setStatus(AdjOrdSta.WAIT_CONFIRM.getValue());
        o.setType(getType(reqVo.getFromType(),reqVo.getFromId(),reqVo.getToType(),reqVo.getToId()).getValue());
        o.setFromId(reqVo.getFromId());
        o.setFromName(fromName);
        o.setToId(reqVo.getToId());
        o.setToName(toName);
        o.setCount(sumCount(reqVo.getItems()));
        o.setCreateTime(System.currentTimeMillis());

        mapper.insertSelective(o);

        o.setOrdNum(ordNumGenerator.genNum(BizConst.ADJUST_ORD_NUM_PRFIX,18,o.getId()));
        mapper.updateByPrimaryKeySelective(o);

        //减库存
        if(!isOthers(reqVo.getFromId())){
            List<StockUpdVo> suvList = new LinkedList<>();
            for(AdjItemFormVo item : reqVo.getItems()){
                suvList.add(new StockUpdVo(
                        false,
                        o.getFromId(),
                        item.getSpecId(),
                        item.getCount(),
                        StockOpType.ADJUST_OUT.getValue(),
                        "配货出货, " + fromName +" -> " +toName
                ));
            }
            try {
                stockCoreService.modifyStock(suvList);
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


        adjItemService.create(o,reqVo.getItems());


        //其他发至仓库 / 仓库发仓库，默认收货
        if(AdjOrdType.OTHER_2_STAGE.equals(o.getType()) || AdjOrdType.STAGE_2_STAGE.equals(o.getType())){
            AdjOrdConfirmVo vo = new AdjOrdConfirmVo();
            vo.setId(o.getId());
            List<AdjustItem> itemList = adjItemService.getByOrdId(o.getId());
            List<AdjOrdConfirmItemVo> ciList = new LinkedList<>();
            vo.setItems(ciList);
            for(AdjustItem item : itemList){
                AdjOrdConfirmItemVo ci = new AdjOrdConfirmItemVo();
                ci.setId(item.getId());
                ci.setRealCount(item.getCount());
            }
            adjOrdConfirm(vo);
        }
    }



    private BigDecimal sumCount(List<AdjItemFormVo> items){
        BigDecimal sum = BigDecimal.ZERO;
        for(AdjItemFormVo item : items){
            sum = sum.add(item.getCount());
        }
        return sum;
    }

    private AdjOrdType getType(int fromType,String fromId,int toType, String toId){
        if(isOthers(fromId)){
            if(isOthers(toId))
                throw new BizErrMsgException("Can not adjust goods to OTHERS");
            else if(isStage(toType,toId))
                return AdjOrdType.OTHER_2_STAGE;
            else
                return AdjOrdType.OTHER_2_SHOP;
        }else if(isStage(fromType,fromId)){
            if(isOthers(toId))
                throw new BizErrMsgException("Can not adjust goods to OTHERS");
            else if(isStage(toType,toId))
                return AdjOrdType.STAGE_2_STAGE;
            else
                return AdjOrdType.STAGE_2_SHOP;
        }else {
            if(isOthers(toId))
                throw new BizErrMsgException("Can not adjust goods to OTHERS");
            else if(isStage(toType,toId))
                return AdjOrdType.SHOP_2_STAGE;
            else
                return AdjOrdType.SHOP_2_SHOP;
        }
    }

    private boolean isStage(int type,String id){
        return type == MerType.STAGE.getValue() && !isOthers(id);
    }




    private boolean isOthers(String id){
        return BizConst.ADJ_OTHER_ID.equals(id);
    }

    private String getName(boolean isFrom,Integer type ,String id){
        if(isOthers(id)){
            return BizConst.ADJ_OTHER_NAME;
        }

        String parameter = isFrom ? "fromType or fromId" : "toType or toID";
        String errMsg = "Parameter "+parameter+" error";
        if(MerType.STAGE.getValue() == type){
            Stage stage = stageCommon.getById(id);
            if(stage == null)
                throw  new BizErrMsgException(errMsg);
            return stage.getName();
        }else if(MerType.SHOP.getValue() == type){
            Shop shop = shopCommon.getById(id);
            if(shop == null)
                throw  new BizErrMsgException(errMsg);
            return shop.getName();
        }else {
            throw new BizErrMsgException(errMsg);
        }
    }

    @Override
    public boolean isToStage(AdjustOrder o){
        return (AdjOrdType.OTHER_2_STAGE.getValue() == o.getType()
                || AdjOrdType.STAGE_2_STAGE.getValue() == o.getType()
                || AdjOrdType.SHOP_2_STAGE.getValue() == o.getType()
        );
    }

    @Override
    public boolean isToShop(AdjustOrder o){
        return (AdjOrdType.OTHER_2_SHOP.getValue() == o.getType()
                || AdjOrdType.STAGE_2_SHOP.getValue() == o.getType()
                || AdjOrdType.SHOP_2_SHOP.getValue() == o.getType()
        );
    }


    @Override
    public void adjOrdConfirm(AdjOrdConfirmVo reqVo) {
        SCheckUtil.notEmpty(reqVo.getId(),"ID");

        if(reqVo.getItems() == null || reqVo.getItems().size() == 0)
            throw new BizErrMsgException("Parameter items can not be empty");

        AdjustOrder o = mapper.selectByPrimaryKey(reqVo.getId());
        if(o == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        o = mapper.getAndLock(o.getId());

        if(AdjOrdSta.WAIT_CONFIRM.getValue() != o.getStatus())
            throw new BaseException(ErrCode.ADJ_ORD_NOT_IN_WAIT_CONFIRM);

        o.setStatus(AdjOrdSta.SUC.getValue());
        o.setFinishTime(System.currentTimeMillis());
        BigDecimal realSum = BigDecimal.ZERO;
        for(AdjOrdConfirmItemVo  item : reqVo.getItems())
            realSum = realSum.add(item.getRealCount());
        o.setRealCount(realSum);

        mapper.updateByPrimaryKeySelective(o);

        List<AdjustItem> items = adjItemService.preConfirm(o,reqVo.getItems());


        //加库存
        if(!isOthers(o.getToId())){
            List<StockUpdVo> suvList = new LinkedList<>();
            for(AdjustItem item : items){
                if(item.getRealCount().compareTo(BigDecimal.ZERO) <= 0)
                    continue;
                if(isToStage(o))
                    stockCoreService.addStageStockIfNotExist(item.getGoodsId(),item.getSpecId(),o.getToId());
                if(isToShop(o))
                    stockCoreService.addShopStockIfNotExist(o.getToId(),item.getGoodsId(),item.getSpecId());

                suvList.add(new StockUpdVo(
                        true,
                        o.getToId(),
                        item.getSpecId(),
                        item.getRealCount(),
                        StockOpType.ADJUST_IN.getValue(),
                        "配货入货, " + o.getFromName() +" -> " +o.getToName()
                ));
            }
            try {
                stockCoreService.modifyStock(suvList);
            } catch (StockInsufficientException e) {
                throw new RuntimeException(e.getMessage(),e);
            }
        }

        adjItemService.onConfirm(o,reqVo.getItems());


    }
}
