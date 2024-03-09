package com.verificer.biz.beans.vo.req.adjust;

import java.io.Serializable;

public class AdjFormVo implements Serializable {
    private Integer shortType;
    private String fromId;
    private String toId;
    private Long goodsId;
    private Long specId;
    private Integer count;
    private Integer realCount;
    private String remark;


    public Integer getShortType() {
        return shortType;
    }

    public void setShortType(Integer shortType) {
        this.shortType = shortType;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getRealCount() {
        return realCount;
    }

    public void setRealCount(Integer realCount) {
        this.realCount = realCount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
