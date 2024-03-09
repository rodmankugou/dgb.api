package com.verificer.biz.beans.vo.req;

import com.verificer.beans.PageQueryVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GoodsQryVo extends PageQueryVo {
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("品牌ID")
    private String brandId;
    @ApiModelProperty("分类ID")
    private String categoryId;
    @ApiModelProperty("最小销售量")
    private Integer sSaleCount;
    @ApiModelProperty("最大销售量")
    private Integer eSaleCount;
    @ApiModelProperty("是否查回收站。true-是 false-否")
    private Boolean rubbishFlag;
//    @ApiModelProperty("搜索关键词")
//    private String searchKey;
    @ApiModelProperty("是否上架。true-是；false-否")
    private Boolean saleFlag;
//    @ApiModelProperty("是否有货。true-是；false-否")
//    private Boolean inStockFlag;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getsSaleCount() {
        return sSaleCount;
    }

    public void setsSaleCount(Integer sSaleCount) {
        this.sSaleCount = sSaleCount;
    }

    public Integer geteSaleCount() {
        return eSaleCount;
    }

    public void seteSaleCount(Integer eSaleCount) {
        this.eSaleCount = eSaleCount;
    }


    public Boolean getRubbishFlag() {
        return rubbishFlag;
    }

    public void setRubbishFlag(Boolean rubbishFlag) {
        this.rubbishFlag = rubbishFlag;
    }

    public Boolean getSaleFlag() {
        return saleFlag;
    }

    public void setSaleFlag(Boolean saleFlag) {
        this.saleFlag = saleFlag;
    }
}
