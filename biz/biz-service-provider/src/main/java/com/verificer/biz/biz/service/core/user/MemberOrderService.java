package com.verificer.biz.biz.service.core.user;

import com.verificer.beans.pay.PayReqVo;
import com.verificer.beans.pay.PaySucVo;
import com.verificer.biz.beans.vo.member.req.MemberChargeVo;
import com.verificer.biz.biz.entity.MemberOrder;
import com.verificer.biz.biz.service.core.user.notify.IMemberListener;

public interface MemberOrderService {

    void addListener(IMemberListener listener);
    /**
     * 会员充值
     * @param reqVo
     * @return
     */
    PayReqVo memberCharge(MemberChargeVo reqVo);

    void onPaySuc(PaySucVo paySucVo);

    MemberOrder getById(Long memberOrdId);
}
