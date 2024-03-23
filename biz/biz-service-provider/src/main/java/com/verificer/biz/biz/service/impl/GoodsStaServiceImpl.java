package com.verificer.biz.biz.service.impl;

import com.verificer.biz.beans.enums.MerType;
import com.verificer.biz.biz.entity.DbgOrder;
import com.verificer.biz.biz.entity.GoodsSta;
import com.verificer.biz.biz.entity.OrderDetail;
import com.verificer.biz.biz.entity.StockLog;
import com.verificer.biz.biz.mapper.GoodsStaMapper;
import com.verificer.biz.biz.service.GoodsStaService;
import com.verificer.biz.biz.service.common.OrdCommon;
import com.verificer.biz.biz.service.core.order.OrdCoreService;
import com.verificer.biz.biz.service.core.order.notify.IOrdListener;
import com.verificer.biz.biz.service.core.order.notify.events.OrdEvent;
import com.verificer.biz.biz.service.core.order.notify.events.OrdSucFinishEvent;
import com.verificer.biz.biz.service.core.stock.StockCoreService;
import com.verificer.biz.biz.service.core.stock.entity.StockIdVo;
import com.verificer.biz.biz.service.core.stock.notify.events.IStockListener;
import com.verificer.biz.biz.service.core.stock.notify.events.StockEvent;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsStaServiceImpl implements GoodsStaService {

    @Autowired
    GoodsStaMapper goodsStaMapper;

    @Autowired
    StockCoreService stockCoreService;

    @Autowired
    OrdCoreService ordCoreService;

    @Autowired
    OrdCommon ordCommon;


    @PostConstruct
    void init(){
        ordCoreService.addListener(new IOrdListener() {
            @Override
            public void onOrdEvent(OrdEvent e) {
                handleOrdEvent(e);
            }
        });
        stockCoreService.addListener(new IStockListener() {
            @Override
            public void onStockChange(StockEvent event) {
                handleStockEvent(event);
            }
        });
    }

    private void handleStockEvent(StockEvent event){
        StockLog log = event.getStockLog();
        GoodsSta gSta = goodsStaMapper.getAndLockByGoodsId(log.getGoodsId());
        GoodsSta sSta = goodsStaMapper.getAndLockBySpecId(log.getSpecId());

        BigDecimal count = event.isAdd() ? log.getCount() : BigDecimal.ZERO.subtract(log.getCount());
        if(event.isStageFlag()){
            gSta.setPlaStageCount(gSta.getPlaStageCount().add(count));
            sSta.setPlaStageCount(sSta.getPlaStageCount().add(count));

        }else {
            gSta.setShopStageCount(gSta.getShopStageCount().add(count));
            sSta.setShopStageCount(sSta.getShopStageCount().add(count));
        }
        goodsStaMapper.updateByPrimaryKey(gSta);
        goodsStaMapper.updateByPrimaryKey(sSta);
    }

    private void handleOrdEvent(OrdEvent event){
        if(event instanceof OrdSucFinishEvent){
            OrdSucFinishEvent re = (OrdSucFinishEvent) event;
            updateSaleCount(false,re.getOrdId());
        }
    }

    private void updateSaleCount(boolean isAdd,Long ordId){
        DbgOrder o = ordCommon.getOrd(ordId);
        List<OrderDetail> odList = ordCommon.getOrdItems(ordId);

        //对库存枷锁
        List<StockIdVo> stockIdVoList = new LinkedList<>();
        for(OrderDetail od : odList){
            stockIdVoList.add(new StockIdVo(o.getRelId(),od.getSpecId()));
        }
        stockCoreService.lockStocks(stockIdVoList);


        boolean stageFlag = false;
        if(MerType.STAGE.getValue() == o.getRelType()){
            stageFlag = true;
        }else if(MerType.SHOP.getValue() == o.getRelType()){
            stageFlag = false;
        }else{
            throw new RuntimeException("Illegal order relType ,type =  "+o.getRelType());
        }

        Map<Long,GoodsSta> staMap  = new HashMap<>();
        for(OrderDetail od : odList){
                GoodsSta gSta = goodsStaMapper.getAndLockByGoodsId(od.getGoodsId());
            GoodsSta sSta = goodsStaMapper.getAndLockBySpecId(od.getSpecId());
            if(staMap.containsKey(gSta.getId()))
                gSta = staMap.get(gSta.getId());
            if(staMap.containsKey(sSta.getId()))
                sSta = staMap.get(sSta.getId());

            BigDecimal count = isAdd ? od.getCount() : BigDecimal.ZERO.subtract(od.getCount());

            if(o.getRelType() == MerType.STAGE.getValue()){
                gSta.setPlaSaleCount(gSta.getPlaSaleCount().add(count));
                sSta.setPlaSaleCount(sSta.getPlaSaleCount().add(count));
            }else if(o.getRelType() == MerType.SHOP.getValue()){
                gSta.setShopSaleCount(gSta.getShopSaleCount().add(count));
                sSta.setShopSaleCount(sSta.getShopSaleCount().add(count));
            }
        }

        for(Long key : staMap.keySet()){
            GoodsSta sta = staMap.get(key);
            goodsStaMapper.updateByPrimaryKey(sta);
        }
    }


    public GoodsStaMapper getGoodsStaMapper() {
        return goodsStaMapper;
    }

    public void setGoodsStaMapper(GoodsStaMapper goodsStaMapper) {
        this.goodsStaMapper = goodsStaMapper;
    }


    private void mCheck(GoodsSta m){
        SCheckUtil.notEmpty(m.getGoodsId(),"GoodsSta.GoodsId");
        SCheckUtil.notEmpty(m.getSumStaFlag(),"GoodsSta.SumStaFlag");
        if(!m.getSumStaFlag())
            SCheckUtil.notEmpty(m.getSpecId(),"GoodsSta.SpecId");

        SCheckUtil.notEmpty(m.getPlaSaleCount(),"GoodsSta.PlaSaleCount");
        SCheckUtil.notEmpty(m.getPlaStageCount(),"GoodsSta.PlaStageCount");
        SCheckUtil.notEmpty(m.getShopSaleCount(),"GoodsSta.ShopSaleCount");
        SCheckUtil.notEmpty(m.getShopStageCount(),"GoodsSta.ShopStageCount");
        SCheckUtil.notEmpty(m.getEvaluateCount(),"GoodsSta.EvaluateCount");
    }

    @Override
    public void add(Long goodsId) {
        add(goodsId,null);
    }


    @Override
    public void add(Long goodsId,Long specId) {
        GoodsSta gs = new GoodsSta();
        gs.setGoodsId(goodsId);
        gs.setSpecId(specId);
        gs.setSumStaFlag(specId == null ? true : false);
        gs.setPlaSaleCount(BigDecimal.ZERO);
        gs.setPlaStageCount(BigDecimal.ZERO);
        gs.setShopSaleCount(BigDecimal.ZERO);
        gs.setShopStageCount(BigDecimal.ZERO);
        gs.setEvaluateCount(0);

        mCheck(gs);
        goodsStaMapper.insertSelective(gs);

    }

    @Override
    public GoodsSta getBySpecId(Long specId) {
        return goodsStaMapper.selectBySpecId(specId);
    }
}
