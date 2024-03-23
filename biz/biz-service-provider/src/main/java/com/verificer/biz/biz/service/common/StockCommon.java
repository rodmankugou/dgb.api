package com.verificer.biz.biz.service.common;

import com.verificer.GlobalConfig;
import com.verificer.biz.beans.vo.GoodsVo;
import com.verificer.biz.beans.vo.order.PayVo;
import com.verificer.biz.biz.entity.GoodsSta;
import com.verificer.biz.biz.entity.Stock;
import com.verificer.biz.biz.service.GoodsService;
import com.verificer.biz.biz.service.GoodsStaService;
import com.verificer.biz.biz.service.core.stock.StockCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

//TODO 缓存优化
@Component
public class StockCommon {
    @Autowired
    GoodsStaService goodsStaService;

    @Autowired
    StockCoreService stockCoreService;

    public BigDecimal getPlaStockCount(Long specId){
        GoodsSta sta = goodsStaService.getBySpecId(specId);
        return sta.getPlaStageCount();
    }


    public BigDecimal getShopStockCount(String shopId, Long specId){
        Stock stock = stockCoreService.getStock(shopId,specId);
        return stock.getCount();
    }

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
