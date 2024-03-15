package com.verificer.biz.biz.service.core.order.flow.impl;

import com.verificer.biz.beans.enums.OpEntry;
import com.verificer.biz.beans.enums.OrdOpType;
import com.verificer.biz.beans.enums.OrdSta;
import com.verificer.biz.beans.enums.RefundBizType;
import com.verificer.biz.beans.vo.req.OrdFormVo;
import com.verificer.biz.biz.entity.DbgOrder;
import com.verificer.biz.biz.mapper.DbgOrderMapper;
import com.verificer.biz.biz.service.common.OrdCommon;
import com.verificer.biz.biz.service.core.order.flow.IOrderFlow;
import com.verificer.biz.biz.service.core.order.vo.EvaluateVo;
import com.verificer.biz.biz.service.core.order.vo.OrdVo;
import com.verificer.biz.biz.service.core.order.vo.PayVo;
import com.verificer.biz.biz.service.core.order.vo.selftake.SelfTakeRefundVo;
import com.verificer.biz.biz.service.core.order.vo.selftake.SelfTakeTakeVo;
import com.verificer.biz.biz.service.pay.PayService;
import com.verificer.biz.biz.service.pay.entity.PayRefundVo;
import com.verificer.utils.SStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 门店自提订单
 */
@Service
public class SelfTakeOrderFlow implements IOrderFlow {

    @Autowired
    OrdCommon ordCommon;

    @Autowired
    DbgOrderMapper mapper;

    @Autowired
    PayService payService;


    @Override
    public void beforeCreate(OrdVo ovo, OrdFormVo ofo){

    }

    @Override
    public void afterCreate(OrdVo ovo, OrdFormVo ofo) {
        DbgOrder o = ovo.getOrd();
        ordCommon.writeLog(o,OrdOpType.Create_Order.getValue(), OpEntry.App.getValue(), o.getUserId(),null,System.currentTimeMillis());
    }

    @Override
    public void toNextStatus(OrdVo ovo, OrdFormVo ofo, Object formVo) {
        DbgOrder o = ovo.getOrd();
        if(OrdSta.WAIT_PAY.getValue() == o.getStatus()){
            if(!(formVo instanceof PayVo))
                throw new RuntimeException("OrdSta.WAIT_PAY状态下只接收PayVo类型参数");

            PayVo form = (PayVo) formVo;
            afterPay(o,form);
            ordCommon.writeLog( o, OrdOpType.Pay.getValue(), OpEntry.App.getValue(),o.getUserId(),null,System.currentTimeMillis());

        }else if(OrdSta.WaitSelfTake.getValue() == o.getStatus()){
            if((formVo instanceof SelfTakeRefundVo)){
                o.setStatus(OrdSta.Refunded.getValue());
                PayRefundVo vo = new PayRefundVo();
                vo.setPayId(o.getPayId());
                vo.setBizType(RefundBizType.SELF_TAKE_ORD_REFUND.getValue());
                vo.setRelId(o.getId().toString());
                vo.setRemark("退款类型：订单退款，订单号[ "+o.getOrderNum()+" ]");
                vo.setAmount(o.getAmount());
                payService.refund(vo);
                ordCommon.writeLog( o, OrdOpType.Refund.getValue(), OpEntry.Merchant.getValue(),o.getRelId(),null,System.currentTimeMillis());

            }else if(formVo instanceof SelfTakeTakeVo){
                o.setStatus(OrdSta.Received.getValue());
                o.setTakeFlag(true);
                o.setTakeTime(System.currentTimeMillis());
                ordCommon.writeLog( o, OrdOpType.Refund.getValue(), OpEntry.App.getValue(),o.getUserId(),null,System.currentTimeMillis());

            }else {
                throw new RuntimeException("OrdSta.WaitSelfTake下只接收SelfTakeRefundVo或者SelfTakeTakeVo类型参数");
            }
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
        o.setStatus(OrdSta.WaitSelfTake.getValue());

        //生成提货码

        String takeCode ;
        while (true){
            takeCode = SStringUtils.generateRandomNumSequence(6);
            DbgOrder order = mapper.selectByRelIdAndTakeCodeAndStatus(o.getRelId(),o.getTakeCode(),o.getStatus());
            if(order == null)
                break;
        }
        o.setTakeCode(takeCode);
        o.setTakeFlag(false);

    }



}
