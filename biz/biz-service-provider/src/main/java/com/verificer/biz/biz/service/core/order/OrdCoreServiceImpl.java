package com.verificer.biz.biz.service.core.order;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.verificer.ErrCode;
import com.verificer.GlobalConfig;
import com.verificer.biz.beans.enums.OrdSta;
import com.verificer.biz.beans.enums.OrdType;
import com.verificer.biz.beans.vo.OrdFlowFormVo;
import com.verificer.biz.beans.vo.order.OrdFormVo;
import com.verificer.biz.beans.vo.req.OrdItemFormVo;
import com.verificer.biz.biz.entity.DbgOrder;
import com.verificer.biz.biz.entity.Goods;
import com.verificer.biz.biz.entity.OrderDetail;
import com.verificer.biz.biz.entity.Spec;
import com.verificer.biz.biz.mapper.DbgOrderMapper;
import com.verificer.biz.biz.mapper.OrderDetailMapper;
import com.verificer.biz.biz.service.common.OrdCommon;
import com.verificer.biz.biz.service.common.UserCommon;
import com.verificer.biz.biz.service.core.order.notify.IOrdListener;
import com.verificer.biz.biz.service.core.order.notify.OrdNotifier;
import com.verificer.biz.biz.service.core.order.vo.OrdCalItemVo;
import com.verificer.biz.biz.service.core.order.vo.OrdCalVo;
import com.verificer.biz.biz.service.core.order.vo.OrdVo;
import com.verificer.biz.biz.service.core.stock.StockCoreService;
import com.verificer.biz.biz.service.common.GoodsCommon;
import com.verificer.biz.biz.service.core.order.flow.IOrderFlow;
import com.verificer.biz.biz.service.core.order.flow.impl.PosOrdFlow;
import com.verificer.biz.biz.service.core.order.flow.impl.SelfTakeOrderFlow;
import com.verificer.biz.biz.service.core.order.flow.impl.StageOrdFlow;
import com.verificer.common.exception.BaseException;
import com.verificer.common.exception.BizErrMsgException;
import com.verificer.utils.SDateUtil;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrdCoreServiceImpl implements OrdCoreService {
    private OrdNotifier notifier = new OrdNotifier();

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

    @Autowired
    OrdCommon ordCommon;


    @Override
    public void addListener(IOrdListener listener) {
        this.notifier.addListener(listener);
    }

    @Override
    public Long create(OrdFormVo ofo) {

        Long now = System.currentTimeMillis();
        SCheckUtil.notEmpty(ofo, "Order Form");
        DbgOrder o = new DbgOrder();
        o.setOrderType(ofo.getOrderType());
        o.setRelType(ofo.getRelType());
        o.setRelId(ofo.getRelId());
        o.setBuyerRemark(ofo.getBuyerRemark());
        o.setCreateTime(now);
        o.setWriteToDbTime(now);
        o.setAmount(ofo.getAmount());
        IOrderFlow flow = getOrdFlow(o);

        if (ofo.getUserId() == null && !flow.canUserIdNull())
            throw new BizErrMsgException("Parameter userId can not be null");
        o.setUserId(ofo.getUserId());
        if (o.getUserId() != null)
            o.setUserName(userCommon.getNickName(o.getUserId()));

        SCheckUtil.notEmpty(o.getOrderType(), "Order Type");
        SCheckUtil.notEmpty(o.getRelType(), "Rel Type");
        SCheckUtil.notEmpty(o.getRelId(), "Rel Id");
        SCheckUtil.lgThanAndNotNull(o.getAmount(), true, BigDecimal.ZERO, "Amount");

        List<OrderDetail> details = new LinkedList<>();


        if(flow.isCheckAmount())
            checkAmount(o.getAmount(),details);

        SCheckUtil.notEmpty(ofo.getDetails(), "Details");
        for (OrdItemFormVo item : ofo.getDetails()) {
            //检查商品是否已下架
            Goods goods = goodsCommon.getGoodsIgnoreDel(item.getGoodsId());
            if (goods == null)
                throw new BizErrMsgException("商品不存在,id" + goods.getId());

            if (!goodsCommon.isGoodsOnSale(goods))
                throw new BaseException(ErrCode.GOODS_NOT_IN_SALE, new Object[]{goodsCommon.getSpecFullName(item.getSpecId())});

            Spec spec = goodsCommon.getSpecIgnoreDel(item.getSpecId());
            if (spec == null)
                throw new BizErrMsgException("规格不存在，id=" + spec.getId());
            if (spec.getDelFlag())
                throw new BaseException(ErrCode.GOODS_NOT_IN_SALE, new Object[]{goodsCommon.getSpecFullName(item.getSpecId())});

            if (!spec.getPrice().equals(item.getPrice()))
                throw new BaseException(ErrCode.CREATE_ORD_GOODS_PRICE_CHANGE, new Object[]{goodsCommon.getSpecFullName(item.getSpecId())});

            if(mapper.selectByUserIdAndStatus(ofo.getUserId(), OrdSta.WAIT_PAY.getValue()).size() > 0)
                throw new BaseException(ErrCode.NEED_FINISH_PAY_PREV_ORDER);



            OrderDetail od = new OrderDetail();
            od.setGoodsId(item.getGoodsId());
            od.setSpecId(item.getSpecId());
            od.setGoodsName(goodsCommon.getGoodsName(item.getGoodsId()));
            od.setSpecName(goodsCommon.getSpecName(item.getSpecId()));
            od.setSpecImg(goodsCommon.getSpecImg(item.getSpecId()));
            od.setPrice(spec.getPrice());
            od.setCount(item.getCount());
            od.setAmount(item.getCount().multiply(od.getPrice()));

            SCheckUtil.notEmpty(od.getGoodsId(), "item.goodsId");
            SCheckUtil.notEmpty(od.getSpecId(), "item.specId");
            SCheckUtil.notEmpty(od.getGoodsName(), "item.goodsName");
            SCheckUtil.notEmpty(od.getSpecName(), "item.specName");
            SCheckUtil.notEmpty(od.getSpecImg(), "item.specImg");
            SCheckUtil.notEmpty(od.getPrice(), "item.price");
            SCheckUtil.notEmpty(od.getCount(), "item.count");

            details.add(od);

        }


        OrdVo ordVo = new OrdVo(o, details);
        flow.beforeCreate(ordVo, ofo,notifier);

        //检查起购量
        Map<Long,BigDecimal> goodsCountMap = getGoodsCountMap(details);
        for(Long goodsId : goodsCountMap.keySet()){
            Goods goods = goodsCommon.getGoodsIgnoreDel(goodsId);
            BigDecimal minLimitCount = goods.getMinLimitCount();
            if(minLimitCount == null)
                continue;

            BigDecimal count = goodsCountMap.get(goodsId);
            if(minLimitCount.compareTo(count) > 0){
                String mc = minLimitCount.setScale(GlobalConfig.W_PREC,BigDecimal.ROUND_UP).toPlainString();
                throw new BaseException(ErrCode.GOODS_MIN_COUNT_LIMIT_EXCEEDED,new Object[]{goods.getName(),mc});
            }
        }


        //检查限购，不能提前放置该段代码
        if(flow.isCheckBuyCountLimit()){
            if(!SStringUtils.isEmpty(o.getUserId())){
                Map<Long,BigDecimal> todayBuyCountMap = getTodayBuyTotal(o.getUserId(),flow.getOrdTime(o));
                for(OrderDetail od : details)  {
                    Long goodsId = od.getGoodsId();
                    Goods goods = goodsCommon.getGoodsIgnoreDel(goodsId);
                    if(goods.getMaxLimitCount() == null)
                        continue;

                    BigDecimal buyCount = todayBuyCountMap.containsKey(goodsId) ? todayBuyCountMap.get(goodsId) : BigDecimal.ZERO;
                    BigDecimal restCount = goods.getMaxLimitCount().subtract(buyCount).setScale(GlobalConfig.W_PREC,BigDecimal.ROUND_DOWN);

                    if(od.getCount().compareTo(restCount) > 0){
                        String lc = goods.getMaxLimitCount().setScale(GlobalConfig.W_PREC).toPlainString();
                        String rc = restCount.setScale(GlobalConfig.W_PREC).toPlainString();
                        throw new BaseException(ErrCode.GOODS_DAYS_LIMIT_EXCEEDED,new Object[]{goods.getName(),lc,rc});
                    }
                    todayBuyCountMap.put(goodsId,todayBuyCountMap.get(goodsId).add(od.getCount()));
                }

            }
        }


        mapper.insertSelective(o);
        for (OrderDetail od : details) {
            od.setOrderId(o.getId());
            orderDetailMapper.insertSelective(od);
        }

        flow.afterCreate(ordVo, ofo,notifier);
        mapper.updateByPrimaryKeySelective(o);
        for (OrderDetail od : details) {
            orderDetailMapper.updateByPrimaryKeySelective(od);
        }


        return o.getId();
    }

    private void checkAmount(BigDecimal amount, List<OrderDetail> details) {
        OrdCalVo calVo = new OrdCalVo();
        List<OrdCalItemVo> itemVos = new LinkedList<>();
        for(OrderDetail od : details){
            OrdCalItemVo cv = new OrdCalItemVo();
            cv.setGoodsId(od.getGoodsId());
            cv.setSpecId(od.getSpecId());
            cv.setCount(od.getCount());
            itemVos.add(cv);
        }
        BigDecimal realAmount = ordCommon.calOrdAmount(calVo);
        if(realAmount.compareTo(amount) != 0)
            throw new BaseException(ErrCode.ORD_AMOUNT_ERROR);

    }

    @Override
    public void toNextStatus(OrdFlowFormVo flowFormVo) {
        SCheckUtil.notEmpty(flowFormVo.getId(),"ID");
        DbgOrder order = ordCommon.getAndLock(flowFormVo.getId());

        IOrderFlow flow = getOrdFlow(order);
        List<OrderDetail> odList = ordCommon.getOrdItems(order.getId());
        flow.toNextStatus(new OrdVo(order,odList),flowFormVo,notifier);

    }

    private Map<Long,BigDecimal> getGoodsCountMap(List<OrderDetail> odList){
        Map<Long,BigDecimal> map = new HashMap<>();
        for(OrderDetail od : odList){
            Long goodsId = od.getGoodsId();
            if(!map.containsKey(goodsId)){
                map.put(goodsId,BigDecimal.ZERO);
            }
            map.put(goodsId,map.get(goodsId).add(od.getCount()));
        }
        return map;
    }

    private Map<Long,BigDecimal> getTodayBuyTotal(String userId,Long ordTime){
        if(userId != null)
            return new HashMap<>();


        List<DbgOrder> ordList = mapper.selectTodayUserOrders(
                userId,
                SDateUtil.getDayStartTime(ordTime),
                SDateUtil.getDayEndTime(ordTime)
        );

        Map<Long,BigDecimal> map = new HashMap<>();
        for(DbgOrder ord : ordList){
            //忽略被取消/退款等无效订单
            if(!OrdSta.isValidOrd(ord.getStatus()))
                continue;

            //忽略售后补货单
            if(OrdType.isSafeTakeOrd(ord.getOrderType()))
                continue;

            List<OrderDetail> odList = ordCommon.getOrdItems(ord.getId());
            for(OrderDetail od : odList){
                Long goodsId = od.getGoodsId();
                if(!map.containsKey(goodsId)){
                    map.put(goodsId,BigDecimal.ZERO);
                }
                map.put(goodsId,map.get(goodsId).add(od.getCount()));
            }
        }

        return map;
    }

    private IOrderFlow getOrdFlow(DbgOrder o) {
        SCheckUtil.notEmpty(o.getOrderType(), "Order Type");
        if (OrdType.STAGE.getValue() == o.getOrderType()) {
            return stageOrdFlow;
        } else if (OrdType.POS.getValue() == o.getOrderType()) {
            return posOrdFlow;
        } else if (OrdType.SELF_TAKE.getValue() == o.getOrderType()) {
            return selfTakeOrderFlow;
        } else {
            throw new BizErrMsgException("Err orderType, orderType can not be " + o.getOrderType());
        }


    }

}
