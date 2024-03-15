package com.verificer.biz.biz.service.pay;

import com.verificer.biz.biz.service.pay.entity.PayRefundVo;

import java.math.BigDecimal;

public interface PayService {

    void refund(PayRefundVo rvo);
}
