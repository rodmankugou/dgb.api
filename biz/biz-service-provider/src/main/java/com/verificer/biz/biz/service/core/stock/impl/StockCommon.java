package com.verificer.biz.biz.service.core.stock.impl;

import com.verificer.GlobalConfig;
import com.verificer.biz.biz.entity.Stock;
import com.verificer.biz.biz.service.core.order.vo.PayVo;
import org.springframework.stereotype.Service;

@Service
public class StockCommon {

    public String formatCount(Stock stock){
        return stock.getSkuFlag() ? stock.getCount().setScale(0).toPlainString() : stock.getCount().setScale(GlobalConfig.W_PREC).toPlainString();

    }

    /**
     * 检查支付金额
     * @param payVo
     */
    public void checkPay(PayVo payVo){

    }

}
