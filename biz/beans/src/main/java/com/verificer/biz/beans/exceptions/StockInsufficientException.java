package com.verificer.biz.beans.exceptions;

import java.math.BigDecimal;

/**
 * 库存不足异常
 */
public class StockInsufficientException extends Exception{
    /**
     * 是否仓库
     */
    private boolean isStage;

    /**
     * 仓库或者店铺ID
     */
    private String refId;

    /**
     * 仓库或者店铺名称
     */
    private String refName;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 规格ID
     */
    private Long specId;

    /**
     * 规格名称
     */
    private String specName;

    /**
     * 当前库存
     */
    private String stockCount;

    public StockInsufficientException(boolean isStage, String refId, String refName, Long goodsId, String goodsName, Long specId, String specName, String stockCount) {
        super(isStage? "库存":"店铺"+"[Id="+refId+", name="+refName+"]库存不足,商品[id="+goodsId+", specId="+specId+",goodsName="+goodsName+",specName="+specName+"],当前剩余库存量为"+stockCount);
        this.isStage = isStage;
        this.refId = refId;
        this.refName = refName;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.specId = specId;
        this.specName = specName;
        this.stockCount = stockCount;
    }

    public boolean isStage() {
        return isStage;
    }

    public String getRefId() {
        return refId;
    }

    public String getRefName() {
        return refName;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public Long getSpecId() {
        return specId;
    }

    public String getSpecName() {
        return specName;
    }

    public String getStockCount() {
        return stockCount;
    }
}
