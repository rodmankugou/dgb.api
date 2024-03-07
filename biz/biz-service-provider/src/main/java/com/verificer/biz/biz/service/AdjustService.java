package com.verificer.biz.biz.service;

import com.verificer.biz.beans.vo.AdjustVo;
import com.verificer.biz.beans.vo.req.AdjustBatchVo;
import com.verificer.biz.beans.vo.req.AdjustFormVo;
import com.verificer.biz.beans.vo.req.AdjustPageVo;

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
     * 批量调货
     * @param formVo
     */
    void adjustBatch(AdjustBatchVo formVo);

    /**
     * 调货
     * @param formVo
     */
    void adjust(AdjustFormVo formVo);


}
