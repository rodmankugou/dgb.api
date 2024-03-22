package com.verificer.biz.biz.mapper;

import com.verificer.biz.beans.vo.user.RefereeVo;
import com.verificer.biz.beans.vo.user.ReferrerStaVo;
import com.verificer.biz.beans.vo.user.UserWithdrawVo;
import com.verificer.biz.beans.vo.user.req.RefereeListReqVo;
import com.verificer.biz.beans.vo.user.req.RefereeStaReqVo;
import com.verificer.biz.beans.vo.user.req.ReferrerWithdrawPageReqVo;
import com.verificer.biz.beans.vo.user.req.SumAccAmountVo;

import java.math.BigDecimal;
import java.util.List;

public interface BizAccLogMapper {
    public List<RefereeVo> pageReferee(RefereeListReqVo vo);
    public int countReferee(RefereeListReqVo vo);

    ReferrerStaVo refereeSta(RefereeStaReqVo reqVo);

    BigDecimal sumAmount(SumAccAmountVo sunAmountVo);

    List<UserWithdrawVo> pageReferrerWithdraw(ReferrerWithdrawPageReqVo reqVo);

    int countReferrerWithdraw(ReferrerWithdrawPageReqVo reqVo);
}
