package com.verificer.biz.beans.vo.adjust;

import com.verificer.utils.decimal.CountDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class AdjOrderVo implements Serializable {

    @ApiModelProperty("Id")
    private Long id;

    @ApiModelProperty("订单编号")
    private String ordNum;

    @ApiModelProperty("sku总数")
    private Integer specCount;

    @ApiModelProperty("状态 1-待确认 2-部分确认 3-已确认")
    private Integer status;

    @ApiModelProperty("发货方名称")
    private String fromName;

    @ApiModelProperty("收货方名称")
    private String toName;

    @ApiModelProperty("配货数量")
    @CountDecimal
    private BigDecimal count;

    @ApiModelProperty("实际到货数量")
    private BigDecimal realCount;

    @ApiModelProperty("创建时间")
    private Long createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrdNum() {
        return ordNum;
    }

    public void setOrdNum(String ordNum) {
        this.ordNum = ordNum;
    }

    public Integer getSpecCount() {
        return specCount;
    }

    public void setSpecCount(Integer specCount) {
        this.specCount = specCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getRealCount() {
        return realCount;
    }

    public void setRealCount(BigDecimal realCount) {
        this.realCount = realCount;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
