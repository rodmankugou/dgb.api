package com.verificer.biz.biz.service.common;

import com.verificer.ErrCode;
import com.verificer.GlobalConfig;
import com.verificer.biz.beans.enums.AfterSaleSta;
import com.verificer.biz.beans.enums.OpEntry;
import com.verificer.biz.beans.enums.OrdOpType;
import com.verificer.biz.beans.enums.OrdSta;
import com.verificer.biz.beans.exceptions.StockInsufficientException;
import com.verificer.biz.beans.vo.order.ConfirmReceiveVo;
import com.verificer.biz.beans.vo.order.EvaluateVo;
import com.verificer.biz.beans.vo.order.PayVo;
import com.verificer.biz.beans.vo.order.TransitVo;
import com.verificer.biz.beans.vo.req.StockUpdVo;
import com.verificer.biz.biz.entity.*;
import com.verificer.biz.biz.mapper.DbgOrderMapper;
import com.verificer.biz.biz.service.*;
import com.verificer.biz.biz.service.core.order.notify.OrdNotifier;
import com.verificer.biz.biz.service.core.order.notify.events.OrdReceivedEvent;
import com.verificer.biz.biz.service.core.order.vo.*;
import com.verificer.biz.biz.service.core.stock.StockCoreService;
import com.verificer.common.exception.BaseException;
import com.verificer.common.exception.BizErrMsgException;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Service
public class OrdCommon {

    @Autowired
    DbgOrderMapper mapper;

    @Autowired
    SpecService specService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    GoodsCommon goodsCommon;

    @Autowired
    AddrCommon addrCommom;

    @Autowired
    UserCommon userCommon;

    @Autowired
    ShopCommon shopCommon;

    @Autowired
    DbgOrderLogService dbgOrderLogService;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    AfterSaleService afterSaleService;

    @Autowired
    StockCoreService stockCoreService;



    /**
     * 计算订单总金额
     * @return
     */
    public BigDecimal calOrdAmount(OrdCalVo calVo){
        BigDecimal amount = BigDecimal.ZERO;

        for(OrdCalItemVo item : calVo.getItems()){
            SCheckUtil.notEmpty(item.getGoodsId(),"item.goodsId");
            SCheckUtil.notEmpty(item.getSpecId(),"item.specId");
            SCheckUtil.lgThanAndNotNull(item.getCount(),false,BigDecimal.ZERO,"item.count");
            Spec spec = specService.getActById(item.getSpecId());
            if(spec == null)
                throw new BaseException(ErrCode.GOODS_SPEC_NOT_EXIST,new Object[]{ goodsCommon.getSpecFullName(item.getSpecId())});
            BigDecimal price = spec.getPrice();
            BigDecimal itemAmount = item.getCount().multiply(price);
            amount = amount.add(itemAmount);
        }

        amount.setScale(GlobalConfig.PREC,BigDecimal.ROUND_HALF_UP);
        return amount;
    }

    public void afterPay(DbgOrder o, PayVo payVo){
        if(payVo.getPayAmount().compareTo(o.getAmount()) != 0){
            throw new RuntimeException("非法支付，应付金额与支付金额不一致，应收金额为："+o.getAmount().toPlainString()+",实收金额为："+payVo.getPayAmount());
        }
        o.setPayId(payVo.getPayId());
        o.setPayType(payVo.getPayType());
    }

    public void fillOrdAddr(DbgOrder o,Long addrId){
        Addr addr = addrCommom.getAddr(addrId);
        if(addr == null)
            throw new BizErrMsgException("Address not exist");
        fillOrdAddr(o,addr);
    }

    public void fillOrdAddr(DbgOrder o,Addr addr){
        o.setRcAddr(addr.getAddr());
        o.setRcAddrDetail(addr.getDetailAddr());
        o.setRcName(addr.getRcName());
        o.setRcMobile(addr.getMobile());
        o.setRcLongitude(addr.getLongitude());
        o.setRcLatitude(addr.getLatitude());
        o.setStatus(OrdSta.WaitTransit.getValue());

    }

    public void sendTransit(DbgOrder o, TransitVo transitVo) {
        o.setTransitTime(System.currentTimeMillis());
        o.setTransitType(transitVo.getType());
        o.setTransitWayCode(transitVo.getWayCode());
        o.setTransitOrderNum(transitVo.getOrderNum());

        //这里是我们内部的ID，后续可能要与物流端做对账
        o.setTransitOrderId(null);
        o.setStatus(OrdSta.InTransit.getValue());
        o.setTransitStaffId(transitVo.getStaffId());
        o.setTransitStaffName(transitVo.getStaffName());
        writeLog( o, OrdOpType.Transit.getValue(), OpEntry.Bo.getValue(),transitVo.getStaffId().toString(),transitVo.getStaffName(),System.currentTimeMillis());

    }

    /**
     * 写订单操作日志
     * @param o
     * @param opType
     * @param opEntry
     * @param oprId
     * @param time
     */
    public void writeLog(DbgOrder o,Integer opType,Integer opEntry,String oprId,String oprName,Long time){
        if(oprName == null){
            if(OpEntry.System.getValue() == opEntry){
                oprName = "系统";
            } else if(OpEntry.App.getValue() == opEntry){
                oprName = userCommon.getNickName(oprId);
            } else if(OpEntry.Merchant.getValue() == opEntry){
                oprName = shopCommon.getOprName(oprId);
            }else if(OpEntry.Pos.getValue() == opEntry){
                oprName = shopCommon.getOprName(oprId);
            }

        }

        DbgOrderLog log = new DbgOrderLog();
        log.setOrderId(o.getId());
        log.setOpType(opType);
        log.setOprId(oprId);
        log.setOprName(oprName);
        log.setOpEntry(opEntry);
        log.setOrderStatus(o.getStatus());
        log.setCreateTime(time);

        dbgOrderLogService.add(log);
    }




    /**
     * 商品评价
     * @param o
     * @param evaluateVo
     */
    public void evaluate(DbgOrder o, EvaluateVo evaluateVo) {
        //TODO 待完成
        o.setStatus(OrdSta.Finish.getValue());
        writeLog( o,
                evaluateVo.isUserFlag() ? OrdOpType.User_Evaluate.getValue() : OrdOpType.Auto_Evaluate.getValue(),
                evaluateVo.isUserFlag() ? OpEntry.App.getValue() : OpEntry.System.getValue(),
                evaluateVo.isUserFlag() ?  o.getUserId() : null,
                null,
                System.currentTimeMillis());
    }

    public void receiveConfirm(DbgOrder o, ConfirmReceiveVo receiveVo, OrdNotifier notifier){
        o.setTransitRcTime(System.currentTimeMillis());
        o.setStatus(OrdSta.Received.getValue());
        writeLog( o,
                receiveVo.isUserFlag() ? OrdOpType.User_Confirm_Deliver.getValue() : OrdOpType.Courier_Deliver.getValue(),
                receiveVo.isUserFlag() ? OpEntry.App.getValue() : OpEntry.System.getValue(),
                receiveVo.isUserFlag() ? o.getUserId() : null,
                null,
                System.currentTimeMillis());
        notifier.triggerAll(new OrdReceivedEvent(o.getId()));

    }

    public List<OrderDetail> getOrdItems(Long orderId){
        return orderDetailService.getByOrdId(orderId);
    }

    /**
     * 插入售后记录
     */
    public void addAfterSale(DbgOrder o){
        if(OrdSta.Received.getValue() != o.getStatus()){
            throw new RuntimeException("只有处于Received状态的订单能创建售后记录");
        }

        List<OrderDetail> odList = getOrdItems(o.getId());
        for(OrderDetail od : odList){
            AfterSale as = new AfterSale();
            Long rootOrderId = o.getRootOrderId() == null ? o.getId() : o.getRootOrderId();
            as.setRootOrderId(rootOrderId);
            as.setOrderId(o.getId());
            as.setStatus(AfterSaleSta.INIT.getValue());

            as.setGoodsId(od.getGoodsId());
            as.setGoodsName(od.getGoodsName());
            as.setSpecId(od.getSpecId());
            as.setSpecName(od.getSpecName());
            as.setCount(od.getCount());

            afterSaleService.add(as);

        }

    }

    public void subtractStock(DbgOrder o,Integer stockOpType,String remark){
        modifyStock(false,o,stockOpType,remark);
    }

    public void increaseStock(DbgOrder o,Integer stockOpType,String remark){
        modifyStock(true,o,stockOpType,remark);
    }

    private void modifyStock(boolean isAdd,DbgOrder o,Integer stockOpType,String remark){
        List<OrderDetail> odList = getOrdItems(o.getId());
        List<StockUpdVo> updList = new LinkedList<>();
        for(OrderDetail od : odList){
            StockUpdVo vo = new StockUpdVo(isAdd,o.getId().toString(),od.getSpecId(),od.getCount(),stockOpType,remark);
            updList.add(vo);
        }
        try {
            stockCoreService.modifyStock(updList);
        } catch (StockInsufficientException e) {
            String specFullName = goodsCommon.getSpecFullName(e.getSpecId());
            throw new BizErrMsgException(ErrCode.GOODS_STOCK_NOT_ENOUGHT,new Object[]{specFullName});
        }
    }

    public DbgOrder getOrd(Long ordId) {
        return mapper.selectByPrimaryKey(ordId);
    }

    public DbgOrder getAndLock(Long id) {
        DbgOrder order = mapper.getAndLock(id);
        if(order == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        return order;
    }
}
