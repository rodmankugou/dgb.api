package com.verificer.biz.biz.service.settle;

import com.verificer.biz.beans.enums.PlaIncomeLogType;
import com.verificer.biz.beans.vo.settle.PlaIncomeLogVo;
import com.verificer.biz.beans.vo.settle.req.PlaIncomeLogQryVo;
import com.verificer.biz.biz.entity.SettleOrder;

import java.math.BigDecimal;
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

    /**
     * 结算后新增出账
     * @param so
     */
    void afterSettle(SettleOrder so);

    void  createLog(PlaIncomeLogType type, String shopId, Long ordId, String ordNum, BigDecimal amount, Boolean incomeFlag, String remark);
}
