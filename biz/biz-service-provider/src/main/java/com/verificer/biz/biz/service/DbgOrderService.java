package com.verificer.biz.biz.service;

import com.verificer.biz.beans.vo.DbgOrderVo;
import com.verificer.biz.beans.vo.req.OrdFormVo2;
import com.verificer.biz.beans.vo.req.OrderPageVo;

import java.util.List;

public interface DbgOrderService {

    /**
     * 订单列表
     * @param qryVo
     * @return
     */
    List<DbgOrderVo> orderPage(OrderPageVo qryVo);

    /**
     * 统计符合条件的订单数
     * @param qryVo
     * @return
     */
    int orderCount(OrderPageVo qryVo);

    /**
     * 新增订单
     * @param formVo
     * @return
     */
    Long orderAdd(OrdFormVo2 formVo);

    /**
     * 订单详情
     * @return
     */
    DbgOrderVo orderDetail(Long id);
}
