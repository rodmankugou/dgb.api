package com.verificer.biz.biz.service.core.order.flow.impl;


import com.verificer.biz.beans.enums.OpEntry;
import com.verificer.biz.beans.enums.OrdOpType;
import com.verificer.biz.beans.enums.OrdSta;
import com.verificer.biz.beans.vo.req.OrdFormVo;
import com.verificer.biz.beans.vo.req.OrdItemFormVo;
import com.verificer.biz.biz.entity.DbgOrder;
import com.verificer.biz.biz.entity.OrderDetail;
import com.verificer.biz.biz.pospay.entity.YbOrdFormVo;
import com.verificer.biz.biz.pospay.entity.YbOrdItemVo;
import com.verificer.biz.biz.service.common.OrdCommon;
import com.verificer.biz.biz.service.core.order.flow.IOrderFlow;
import com.verificer.biz.biz.service.core.order.vo.OrdVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 门店Pos机收银订单
 */
@Service
public class PosOrdFlow  implements IOrderFlow {
    @Autowired
    OrdCommon ordCommon;

    @Override
    public void beforeCreate(OrdVo ovo, OrdFormVo ofo) {
        if(!(ofo instanceof YbOrdFormVo))
            throw new RuntimeException("银豹收银系统的");
        YbOrdFormVo ybo = (YbOrdFormVo) ofo;
        DbgOrder o = ovo.getOrd();
        o.setPosOrdId(ybo.getPosOrdId());
        o.setPosCashierUid(ybo.getPosCashierId());
        o.setPosOrdTime(ybo.getPosOrdTime());
        o.setPosMemberUid(ybo.getPosMemberId());
        o.setUserId(ybo.getUserId());
        o.setStatus(OrdSta.Finish.getValue());
        o.setTakeFlag(true);

        for(OrderDetail od : ovo.getItems()){
            for(OrdItemFormVo ivo : ofo.getDetails()){
                YbOrdItemVo yod = (YbOrdItemVo) ivo;
                if(od.getSpecId().equals(yod.getSpecId())){
                    od.setRealAmount(yod.getAmount());
                    od.setRealPrice(yod.getRealPrice());
                }
            }
        }


    }

    @Override
    public void afterCreate(OrdVo ovo, OrdFormVo ofo) {
        DbgOrder o = ovo.getOrd();
        ordCommon.writeLog(o, OrdOpType.Create_Order.getValue(), OpEntry.Pos.getValue(), o.getRelId(),null,o.getPosOrdTime());

    }

    @Override
    public void toNextStatus(OrdVo ovo, OrdFormVo ofo, Object formVo) {
        throw new UnsupportedOperationException("Pos Order no next status");
    }

    @Override
    public boolean canUserIdNull() {
        //考虑一些非会员单
        return true;
    }

}
