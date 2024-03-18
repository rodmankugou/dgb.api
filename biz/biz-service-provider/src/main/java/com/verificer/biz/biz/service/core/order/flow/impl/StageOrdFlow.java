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
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 云仓订单
 */
@Service
public class StageOrdFlow extends BaseOrdFlow {
    @Autowired
    OrdCommon ordCommon;


    @Override
    public void beforeCreate(OrdVo ovo, OrdFormVo ofo, OrdNotifier notifier) {
        //填写地址信息
        DbgOrder o = ovo.getOrd();
        SCheckUtil.notEmpty(ofo.getAddrId(),"Addr Id");
        o.setStatus(OrdSta.WAIT_PAY.getValue());
        ordCommon.fillOrdAddr(o,ofo.getAddrId());

    }

    @Override
    public void afterCreate(OrdVo ovo, OrdFormVo ofo, OrdNotifier notifier) {
        DbgOrder o = ovo.getOrd();
        ordCommon.writeLog( o, OrdOpType.Create_Order.getValue(), OpEntry.App.getValue(),o.getUserId(),null,System.currentTimeMillis());
        ordCommon.subtractStock(o, StockOpType.ORD_CREATE.getValue(),"仓库配送单下单后减库存");
    }

    @Override
    public void toNextStatus(OrdVo ovo,  Object formVo, OrdNotifier notifier) {
        DbgOrder o = ovo.getOrd();
        if(OrdSta.WAIT_PAY.getValue() == o.getStatus()){
            if(!(formVo instanceof PayVo))
                throw new RuntimeException("OrdSta.WAIT_PAY状态下只接收PayVo类型参数");

            PayVo form = (PayVo) formVo;
            afterPay(o,form);
            ordCommon.writeLog( o, OrdOpType.Pay.getValue(), OpEntry.App.getValue(),o.getUserId(),null,System.currentTimeMillis());

        }else if(OrdSta.WaitTransit.getValue() == o.getStatus()){
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


    private void afterPay(DbgOrder o, PayVo payVo){
        ordCommon.afterPay(o,payVo);
        o.setStatus(OrdSta.WaitTransit.getValue());

    }




}
