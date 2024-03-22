package com.verificer.biz.biz.service.settle;

import com.verificer.biz.beans.vo.settle.SettleStaVo;
import com.verificer.biz.beans.vo.settle.req.SettleStaQryVo;
import com.verificer.biz.beans.vo.user.member.MemberStaVo;
import com.verificer.biz.biz.entity.Settle;
import com.verificer.biz.biz.entity.SettleItem;

import java.util.List;

public interface SettleService {
    /**
     * 结算概况
     * @return
     */
    SettleStaVo settleSta(SettleStaQryVo reqVo);

    /**
     * 获取就绪的settle
     * @return
     */
    Settle getReadySettle(Long now ) ;

    /**
     * 获取匹配的结算
     * @param shopId
     * @param year
     * @param month
     * @return
     */
    Settle getTargetSettle(String shopId, Integer year, Integer month);


    void afterCalSettle(Settle settle);

    void afterSettle(List<SettleItem> items);

}
