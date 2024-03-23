package com.verificer.biz.biz.service.core.stock;

import com.verificer.biz.beans.vo.revise.req.ReviseFromVo;

public interface ReviseService {
    /**
     * 增/减库存
     * @param reqVo
     */
    void reviseCreate(ReviseFromVo reqVo);
}
