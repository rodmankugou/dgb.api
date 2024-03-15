package com.verificer.biz.biz.service.core.order;

import com.verificer.ErrCode;
import com.verificer.biz.beans.enums.OrdType;
import com.verificer.biz.beans.exceptions.StockInsufficientException;
import com.verificer.biz.beans.vo.req.OrdFormVo;
import com.verificer.biz.beans.vo.req.OrdItemFormVo;
import com.verificer.biz.beans.vo.req.StockUpdVo;
import com.verificer.biz.biz.entity.DbgOrder;
import com.verificer.biz.biz.entity.Goods;
import com.verificer.biz.biz.entity.OrderDetail;
import com.verificer.biz.biz.entity.Spec;
import com.verificer.biz.biz.mapper.DbgOrderMapper;
import com.verificer.biz.biz.mapper.OrderDetailMapper;
import com.verificer.biz.biz.service.common.UserCommon;
import com.verificer.biz.biz.service.core.order.vo.OrdVo;
import com.verificer.biz.biz.service.core.stock.StockCoreService;
import com.verificer.biz.biz.service.common.GoodsCommon;
import com.verificer.biz.biz.service.core.order.flow.IOrderFlow;
import com.verificer.biz.biz.service.core.order.flow.impl.PosOrdFlow;
import com.verificer.biz.biz.service.core.order.flow.impl.SelfTakeOrderFlow;
import com.verificer.biz.biz.service.core.order.flow.impl.StageOrdFlow;
import com.verificer.common.exception.BizErrMsgException;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrdServiceImpl implements OrdService{
    @Autowired
    DbgOrderMapper mapper;

    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Autowired
    SelfTakeOrderFlow selfTakeOrderFlow;

    @Autowired
    StageOrdFlow stageOrdFlow;

    @Autowired
    PosOrdFlow posOrdFlow;

    @Autowired
    GoodsCommon goodsCommon;

    @Autowired
    StockCoreService stockCoreService;

    @Autowired
    UserCommon userCommon;


    @Override
    public Long create(OrdFormVo ofo) {

        SCheckUtil.notEmpty(ofo,"Order Form");
        DbgOrder o = new DbgOrder();
        o.setOrderType(ofo.getOrderType());
        o.setRelType(ofo.getRelType());
        o.setRelId(ofo.getRelId());
        o.setBuyerRemark(ofo.getBuyerRemark());
        IOrderFlow flow = getOrdFlow(o);

        if(ofo.getUserId() == null && !flow.canUserIdNull())
            throw new BizErrMsgException("Parameter userId can not be null");
        o.setUserId(ofo.getUserId());
        if(o.getUserId() != null)
            o.setUserName(userCommon.getNickName(o.getUserId()));

        SCheckUtil.notEmpty(o.getOrderType(),"Order Type");
        SCheckUtil.notEmpty(o.getRelType(), "Rel Type");
        SCheckUtil.notEmpty(o.getRelId(),"Rel Id");
        SCheckUtil.lgThanAndNotNull(o.getAmount(),true, BigDecimal.ZERO,"Amount");

        List<OrderDetail> details = new LinkedList<>();

        //插入订单
        try {
            SCheckUtil.notEmpty(ofo.getDetails(),"Details");
            for(OrdItemFormVo item : ofo.getDetails()){
                //检查商品是否已下架
                Goods goods = goodsCommon.getGoodsIgnoreDel(item.getGoodsId());
                if(goods == null)
                    throw new BizErrMsgException("商品不存在,id"+goods.getId());
                if(goods.getDelFlag() ||  goods.getRubbishFlag() || !goods.getSaleFlag())
                    throw new BizErrMsgException(ErrCode.GOODS_NOT_IN_SALE,new Object[]{goodsCommon.getSpecFullName(item.getSpecId())});

                //减库存
                StockUpdVo stockUpdVo = new StockUpdVo(false,o.getRelId(),item.getSpecId(),item.getCount());
                stockCoreService.modifyStock(stockUpdVo);

                Spec spec = goodsCommon.getSpecIgnoreDel(item.getSpecId());
                if(spec == null)
                    throw new BizErrMsgException("规格不存在，id="+spec.getId());
                if(spec.getDelFlag())
                    throw new BizErrMsgException(ErrCode.GOODS_NOT_IN_SALE,new Object[]{goodsCommon.getSpecFullName(item.getSpecId())});

                if(!spec.getPrice().equals(item.getPrice()))
                    throw new BizErrMsgException(ErrCode.CREATE_ORD_GOODS_PRICE_CHANGE,new Object[]{goodsCommon.getSpecFullName(item.getSpecId())});

                OrderDetail od = new OrderDetail();
                od.setGoodsId(item.getGoodsId());
                od.setSpecId(item.getSpecId());
                od.setGoodsName(goodsCommon.getGoodsName(item.getGoodsId()));
                od.setSpecName(goodsCommon.getSpecName(item.getSpecId()));
                od.setSpecImg(goodsCommon.getSpecImg(item.getSpecId()));
                od.setPrice(spec.getPrice());
                od.setCount(item.getCount());
                od.setAmount(item.getCount().multiply(od.getPrice()));

                SCheckUtil.notEmpty(od.getGoodsId(),"item.goodsId");
                SCheckUtil.notEmpty(od.getSpecId(),"item.specId");
                SCheckUtil.notEmpty(od.getGoodsName(),"item.goodsName");
                SCheckUtil.notEmpty(od.getSpecName(),"item.specName");
                SCheckUtil.notEmpty(od.getSpecImg(),"item.specImg");
                SCheckUtil.notEmpty(od.getPrice(),"item.price");
                SCheckUtil.notEmpty(od.getCount(),"item.count");
                //TODO 后续处理补发状态

                details.add(od);

            }
        } catch (StockInsufficientException e) {
            throw new BizErrMsgException(ErrCode.CREATE_ORD_STOCK_NOT_ENOUGH,new Object[]{goodsCommon.getSpecFullName(e.getSpecId())});
        }

        OrdVo ordVo = new OrdVo(o,details);
        flow.beforeCreate(ordVo,ofo);

        mapper.insertSelective(o);
        for(OrderDetail od : details){
            od.setOrderId(o.getId());
            orderDetailMapper.insertSelective(od);
        }

        flow.afterCreate(ordVo,ofo);
        mapper.updateByPrimaryKeySelective(o);
        for(OrderDetail od : details){
            orderDetailMapper.updateByPrimaryKeySelective(od);
        }


        return null;
    }

    private IOrderFlow getOrdFlow(DbgOrder o){
        SCheckUtil.notEmpty(o.getOrderType(),"Order Type");
        if(OrdType.STAGE.getValue() == o.getOrderType()){
            return stageOrdFlow;
        }else if(OrdType.POS.getValue() == o.getOrderType()){
            return posOrdFlow;
        }else if(OrdType.SELF_TAKE.getValue() == o.getOrderType()){
            return selfTakeOrderFlow;
        }else {
            throw new BizErrMsgException("Err orderType, orderType can not be "+o.getOrderType());
        }


    }

}
