package com.verificer.biz.biz.service.core.order.flow.impl;

import com.verificer.biz.beans.enums.OpEntry;
import com.verificer.biz.beans.enums.OrdOpType;
import com.verificer.biz.beans.enums.OrdSta;
import com.verificer.biz.beans.enums.StockOpType;
import com.verificer.biz.beans.vo.order.*;
import com.verificer.biz.biz.entity.DbgOrder;
import com.verificer.biz.biz.service.common.OrdCommon;
import com.verificer.biz.biz.service.core.order.notify.OrdNotifier;
import com.verificer.biz.biz.service.core.order.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 补货单流程
 */
@Service
public class SupplyOrdFlow extends BaseOrdFlow {

    @Autowired
    OrdCommon ordCommon;

    @Override
    public void beforeCreate(OrdVo ovo, OrdFormVo ofo, OrdNotifier notifier) {
        if(!(ofo instanceof SupplyFormVo))
            throw new RuntimeException("补货单创建订单时只支持 SupplyFormVo类型的参数");
        SupplyFormVo sfo = (SupplyFormVo) ofo;
        DbgOrder o = ovo.getOrd();
        o.setAmount(BigDecimal.ZERO);
        o.setOrigOrderId(sfo.getOrigOrderId());
        o.setRootOrderId(sfo.getRootOrderId());
        o.setStatus(OrdSta.WaitTransit.getValue());
        ordCommon.fillOrdAddr(o,sfo.getAddrId());


    }

    @Override
    public void afterCreate(OrdVo ovo, OrdFormVo ofo, OrdNotifier notifier) {
        if(!(ofo instanceof SupplyFormVo))
            throw new RuntimeException("补发时请求参数类型有误，必须为SupplyFormVo类型的参数");
        SupplyFormVo sfo = (SupplyFormVo) ofo;
        DbgOrder o = ovo.getOrd();
        ordCommon.writeLog( o, OrdOpType.Create_Order.getValue(), OpEntry.App.getValue(),sfo.getStaffId().toString(),sfo.getStaffName(),System.currentTimeMillis());
        ordCommon.subtractStock(o, StockOpType.ORD_CREATE.getValue(),"补货单下单后减库存");
    }

    @Override
    public void toNextStatus(OrdVo ovo,  Object formVo, OrdNotifier notifier) {
        DbgOrder o = ovo.getOrd();
        if(OrdSta.WaitTransit.getValue() == o.getStatus()){
            if(!(formVo instanceof TransitVo))
                throw new RuntimeException("OrdSta.WaitTransit状态下只接收TransitVo类型参数");
            TransitVo transitVo = (TransitVo) formVo;
            ordCommon.sendTransit(o,transitVo);
        }else if(OrdSta.InTransit.getValue() == o.getStatus()){
            if(!(formVo instanceof ConfirmReceiveVo))
                throw new RuntimeException("OrdSta.InTransit状态下只接收ReceiveVo类型参数");
            ConfirmReceiveVo receiveVo = (ConfirmReceiveVo) formVo;
            ordCommon.receiveConfirm(o,receiveVo,notifier);

        }else if(OrdSta.Received.getValue() == o.getStatus()){
            if(formVo instanceof EvaluateVo){
                EvaluateVo evaluateVo = (EvaluateVo) formVo;
                ordCommon.evaluate(o,evaluateVo);

            }else {
                throw new RuntimeException("OrdSta.Received下只接收EvaluateVo类型参数");

            }
        }
    }

    @Override
    public boolean canUserIdNull() {
        return false;
    }
}
