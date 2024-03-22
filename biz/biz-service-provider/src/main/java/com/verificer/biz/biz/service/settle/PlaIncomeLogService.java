package com.verificer.biz.biz.service.settle;

import com.verificer.biz.beans.vo.settle.PlaIncomeLogVo;
import com.verificer.biz.beans.vo.settle.req.PlaIncomeLogQryVo;

import java.util.List;

public interface PlaIncomeLogService {

    /**
     * 平台收支日志列表（分页）
     * @param reqVo
     * @return
     */
    List<PlaIncomeLogVo> plaIncomeLogPage(PlaIncomeLogQryVo reqVo);

    /**
     * 平台收支日志数
     * @param reqVo
     * @return
     */
    int plaIncomeLogCount(PlaIncomeLogQryVo reqVo);
}
