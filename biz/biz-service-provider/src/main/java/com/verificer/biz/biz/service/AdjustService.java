package com.verificer.biz.biz.service;

import com.verificer.biz.beans.vo.AdjustVo;
import com.verificer.biz.beans.vo.req.AdjustPageVo;
import com.verificer.biz.beans.vo.req.adjust.AdjFormVo;

import java.util.List;

public interface AdjustService {

    /**
     * 调货列表（分页）
     * @param qryVo
     * @return
     */
    List<AdjustVo> adjustPage(AdjustPageVo qryVo);

    /**
     * 统计符合条件的调货记录数
     * @param qryVo
     * @return
     */
    int adjustCount(AdjustPageVo qryVo);


    /**
     * 调货
     * @param form
     */
    void adjust(AdjFormVo form);

    /**
     * 批量调货
     * @param formList
     */
    void adjustBatch(List<AdjFormVo> formList);
}
