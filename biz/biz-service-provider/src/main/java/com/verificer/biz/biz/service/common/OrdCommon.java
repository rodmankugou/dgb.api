package com.verificer.biz.biz.service.common;

import com.verificer.ErrCode;
import com.verificer.GlobalConfig;
import com.verificer.biz.beans.enums.OpEntry;
import com.verificer.biz.beans.enums.OrdOpType;
import com.verificer.biz.beans.enums.OrdSta;
import com.verificer.biz.biz.entity.Addr;
import com.verificer.biz.biz.entity.DbgOrder;
import com.verificer.biz.biz.entity.DbgOrderLog;
import com.verificer.biz.biz.entity.Spec;
import com.verificer.biz.biz.service.DbgOrderLogService;
import com.verificer.biz.biz.service.GoodsService;
import com.verificer.biz.biz.service.SpecService;
import com.verificer.biz.biz.service.common.GoodsCommon;
import com.verificer.biz.biz.service.core.order.vo.*;
import com.verificer.biz.biz.service.impl.DbgOrderLogServiceImpl;
import com.verificer.common.exception.BaseException;
import com.verificer.common.exception.BizErrMsgException;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrdCommon {

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

    public void afterPay(DbgOrder o,PayVo payVo){
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

    public void receiveConfirm( DbgOrder o,ConfirmReceiveVo receiveVo){
        o.setTransitRcTime(System.currentTimeMillis());
        o.setStatus(OrdSta.Received.getValue());
        writeLog( o,
                receiveVo.isUserFlag() ? OrdOpType.User_Confirm_Deliver.getValue() : OrdOpType.Courier_Deliver.getValue(),
                receiveVo.isUserFlag() ? OpEntry.App.getValue() : OpEntry.System.getValue(),
                receiveVo.isUserFlag() ? o.getUserId() : null,
                null,
                System.currentTimeMillis());
    }

    /**
     * 插入售后记录
     */
    public void addAfterSale(){

    }
}
