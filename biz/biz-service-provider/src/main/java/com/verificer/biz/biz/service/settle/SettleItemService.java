package com.verificer.biz.biz.service.settle;

import com.verificer.biz.beans.vo.settle.SettleItemVo;
import com.verificer.biz.beans.vo.settle.req.SettleItemQryVo;
import com.verificer.biz.biz.entity.Settle;
import com.verificer.biz.biz.entity.SettleItem;
import com.verificer.biz.biz.entity.SettleOrder;

import java.util.List;

public interface SettleItemService {
    List<SettleItemVo> settleItemPage(SettleItemQryVo reqVo);

    int settleItemCount(SettleItemQryVo reqVo);

    void createItem(SettleOrder so, Settle settle);

    List<SettleItem> getBySettleOrdId(Long orderId);

    void afterSettle(SettleOrder so);
}
