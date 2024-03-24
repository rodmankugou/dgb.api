package com.verificer.biz.beans.vo.stock;

import com.verificer.utils.decimal.PriceDecimal;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class FromStockVo implements Serializable {
    @ApiModelProperty("商品或者规格ID")
    private Long id;
    @ApiModelProperty("商品或者规格ID")
    private String name;
    @ApiModelProperty("图片")
    private String img;
    @ApiModelProperty("库存数量")
    private String count;
    private List<FromStockVo> specList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<FromStockVo> getSpecList() {
        return specList;
    }

    public void setSpecList(List<FromStockVo> specList) {
        this.specList = specList;
    }
}
