package com.verificer.biz.biz.service.core.user;

import com.verificer.biz.beans.vo.user.withdraw.*;

import java.util.List;

public interface ReferrerWithdrawService {

    List<ReferrerWithdrawVo> referrerWithdrawPage(ReferrerWithdrawPageVo qryVo);

    int referrerWithdrawCount(ReferrerWithdrawPageVo qryVo);

    /**
     * 提现申请
     * @param reqVo
     */
    long referrerWithdrawApply(ReferrerFormVo reqVo);

    /**
     * 引荐人提现审核
     * @param reqVo
     */
    void referrerWithdrawReview(ReferrerReviewVo reqVo);

    /**
     * 引荐人提现确认转账
     * @param reqVo
     */
    void referrerWithdrawTransfer(ReferrerTransferVo reqVo);
}
