package com.verificer.biz.biz.service.adj;


import com.verificer.biz.beans.vo.adjust.AdjOrderVo;
import com.verificer.biz.beans.vo.adjust.req.AdjOrdConfirmVo;
import com.verificer.biz.beans.vo.adjust.req.AdjOrdFormVo;
import com.verificer.biz.beans.vo.adjust.req.AdjOrderQryVo;
import com.verificer.biz.biz.entity.AdjustOrder;
import com.verificer.biz.biz.service.adj.notify.IAdjustListener;

import java.util.List;

public interface AdjOrderService {

    public void addListener(IAdjustListener listener);

    List<AdjOrderVo> adjOrdPage(AdjOrderQryVo qryVo);

    int adjOrdCount(AdjOrderQryVo qryVo);

    /**
     * 创建配货单
     * @param formVo
     */
    void adjOrdCreate(AdjOrdFormVo formVo);

    boolean isToStage(AdjustOrder o);

    boolean isToShop(AdjustOrder o);

    /**
     * 配货单确认收货
     * @param formVo
     */
    void adjOrdConfirm(AdjOrdConfirmVo formVo);
}
