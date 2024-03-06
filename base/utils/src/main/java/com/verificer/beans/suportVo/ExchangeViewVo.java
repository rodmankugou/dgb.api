package com.verificer.beans.suportVo;


import java.io.Serializable;
import java.util.Date;

/**
 * 首页显示交易对的行情
 */
public class ExchangeViewVo implements Serializable {
    private static final long serialVersionUID = -4207597436583167057L;

    //private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;

    /**
     * 交易对id
     */
    private Integer exchangeId;

    /**
     * 交易对
     */
    private String symbol;

    /**
     * 是否可用
     */
    private boolean enable = false;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 排序参数
     */
    private Integer sortParameter;


    public ExchangeViewVo() {
    }

    public ExchangeViewVo(Integer exchangeId, String symbol, boolean enable, Date createTime, Date updateTime, Integer sortParameter) {
        this.exchangeId = exchangeId;
        this.symbol = symbol;
        this.enable = enable;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.sortParameter = sortParameter;
    }

    @Override
    public String toString() {
        return "ExchangeView{" +
                "id=" + id +
                ", exchangeId=" + exchangeId +
                ", symbol='" + symbol + '\'' +
                ", enable=" + enable +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", sortParameter=" + sortParameter +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(Integer exchangeId) {
        this.exchangeId = exchangeId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getSortParameter() {
        return sortParameter;
    }

    public void setSortParameter(Integer sortParameter) {
        this.sortParameter = sortParameter;
    }
}
