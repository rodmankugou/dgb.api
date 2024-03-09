package com.verificer.biz.beans.vo.req;

import com.verificer.beans.PageQueryVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

@ApiModel
public class AdjustPageVo extends PageQueryVo {
    @ApiModelProperty("仓库ID")
    private String merId;

    @ApiModelProperty("商品ID")
    private Long goodsId;

    @ApiModelProperty("规格ID")
    private Long specId;

    @ApiModelProperty("商品名")
    private String goodsName;



    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
