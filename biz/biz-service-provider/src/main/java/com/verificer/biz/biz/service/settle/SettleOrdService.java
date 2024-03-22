package com.verificer.biz.biz.service.settle;

import com.verificer.biz.beans.vo.settle.SettleOrdVo;
import com.verificer.biz.beans.vo.settle.req.SettleOrdQryVo;
import com.verificer.biz.beans.vo.settle.req.SettleTransferVo;

import java.util.List;

public interface SettleOrdService {

    List<SettleOrdVo> settleOrdPage(SettleOrdQryVo reqVo);

    int settleOrdCount(SettleOrdQryVo reqVo);

    /**
     * 结算转账
     * @param reqVo
     */
    void settleOrdTransfer(SettleTransferVo reqVo);

    /**
     * 判断是否需要结算
     * @param
     * @return 如果存在需要的结算项，则返回结算单ID，否则返回null
     */
    Long trySettle();

    /**
     * 结算
     * @param ordId 结算单ID
     * @return 如果存在结算项，则返回1，否则返回0，返回0时表示该结算单已结算完毕
     */
    int settle(Long ordId);
}
