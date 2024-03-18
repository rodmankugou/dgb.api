package com.verificer.biz.biz.service.core.order.flow;

import com.verificer.biz.beans.vo.order.OrdFormVo;
import com.verificer.biz.biz.entity.DbgOrder;
import com.verificer.biz.biz.service.core.order.notify.OrdNotifier;
import com.verificer.biz.biz.service.core.order.vo.OrdVo;

public interface IOrderFlow {

    /**
     * 订单创建前，此时的ovo.ord和items和还没有ID
     * @param ovo 订单及详情
     * @param ofo
     */
    void beforeCreate(OrdVo ovo, OrdFormVo ofo, OrdNotifier notifier);

    /**
     * 订单创建前，此时的ovo.ord和items已经有ID
     * @param ovo ovo订单及详情
     * @param ofo
     */
    void afterCreate(OrdVo ovo, OrdFormVo ofo, OrdNotifier notifier);


    /**
     * 订单状态流转
     * @param ovo ovo订单及详情
     * @param formVo
     */
    void toNextStatus(OrdVo ovo, Object formVo, OrdNotifier notifier);

    boolean canUserIdNull();


    /**
     * 是否需要限购检查
     * @return
     */
    boolean isCheckBuyCountLimit();

    boolean isAfterSale();

    /**
     * 获取订单时间
     * @param o
     * @return
     */
    Long getOrdTime(DbgOrder o);

    boolean isCheckAmount();
}
