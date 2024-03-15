package com.verificer.biz.biz.service.pay.impl;

import com.verificer.GlobalConfig;
import com.verificer.biz.biz.service.pay.PayService;
import com.verificer.biz.biz.service.pay.entity.PayRefundVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class PayServiceImpl implements PayService {


    @Override
    public void refund(PayRefundVo rvo) {
        //退款标题
        String remark = rvo.getRemark() == null ? "" : rvo.getRemark();
        remark = GlobalConfig.APP_NAME+"退款，"+remark;

        //TODO

    }
}
