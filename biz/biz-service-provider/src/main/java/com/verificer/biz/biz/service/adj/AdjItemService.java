package com.verificer.biz.biz.service.adj;

import com.verificer.biz.beans.vo.adjust.TreeAdjItemVo;
import com.verificer.biz.beans.vo.adjust.req.AdjItemFormVo;
import com.verificer.biz.beans.vo.adjust.req.AdjItemTreeQryVo;
import com.verificer.biz.beans.vo.adjust.req.AdjOrdConfirmItemVo;
import com.verificer.biz.biz.entity.AdjustItem;
import com.verificer.biz.biz.entity.AdjustOrder;

import java.util.List;

public interface AdjItemService {

    List<AdjustItem> getByOrdId(Long ordId);


    /**
     * 配货单明细树
     * @param qryVo
     * @return
     */
    List<TreeAdjItemVo> adjItemTree(AdjItemTreeQryVo qryVo);

    void checkParams(List<AdjItemFormVo> items);

    public void create(AdjustOrder o, List<AdjItemFormVo> items);

    List<AdjustItem> preConfirm(AdjustOrder o, List<AdjOrdConfirmItemVo> items);

    /**
     * 确认
     * @param o
     * @param items
     */
    void onConfirm(AdjustOrder o, List<AdjOrdConfirmItemVo> items);


}
