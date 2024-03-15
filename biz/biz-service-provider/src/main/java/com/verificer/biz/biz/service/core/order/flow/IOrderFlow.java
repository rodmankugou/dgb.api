package com.verificer.biz.biz.service.core.order.flow;

import com.verificer.biz.beans.vo.req.OrdFormVo;
import com.verificer.biz.biz.entity.DbgOrder;
import com.verificer.biz.biz.entity.OrderDetail;
import com.verificer.biz.biz.service.core.order.vo.OrdVo;

import java.util.List;

public interface IOrderFlow {

    /**
     * 订单创建前，此时的ovo.ord和items和还没有ID
     * @param ovo 订单及详情
     * @param ofo
     */
    void beforeCreate(OrdVo ovo, OrdFormVo ofo);

    /**
     * 订单创建前，此时的ovo.ord和items已经有ID
     * @param ovo ovo订单及详情
     * @param ofo
     */
    void afterCreate(OrdVo ovo, OrdFormVo ofo);


    /**
     * 订单状态流转
     * @param ovo ovo订单及详情
     * @param ofo
     * @param formVo
     */
    void toNextStatus(OrdVo ovo, OrdFormVo ofo,Object formVo);

    boolean canUserIdNull();




}
