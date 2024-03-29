package com.verificer.biz.biz.entity;

public class ShopGoods {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_goods.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_goods.goods_id
     *
     * @mbg.generated
     */
    private Long goodsId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_goods.spec_id
     *
     * @mbg.generated
     */
    private Long specId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_goods.shop_id
     *
     * @mbg.generated
     */
    private String shopId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_goods.pos_goods_id
     *
     * @mbg.generated
     */
    private Long posGoodsId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_goods.create_time
     *
     * @mbg.generated
     */
    private Long createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_goods.del_flag
     *
     * @mbg.generated
     */
    private Boolean delFlag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_goods.id
     *
     * @return the value of shop_goods.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_goods.id
     *
     * @param id the value for shop_goods.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_goods.goods_id
     *
     * @return the value of shop_goods.goods_id
     *
     * @mbg.generated
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_goods.goods_id
     *
     * @param goodsId the value for shop_goods.goods_id
     *
     * @mbg.generated
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_goods.spec_id
     *
     * @return the value of shop_goods.spec_id
     *
     * @mbg.generated
     */
    public Long getSpecId() {
        return specId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_goods.spec_id
     *
     * @param specId the value for shop_goods.spec_id
     *
     * @mbg.generated
     */
    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_goods.shop_id
     *
     * @return the value of shop_goods.shop_id
     *
     * @mbg.generated
     */
    public String getShopId() {
        return shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_goods.shop_id
     *
     * @param shopId the value for shop_goods.shop_id
     *
     * @mbg.generated
     */
    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_goods.pos_goods_id
     *
     * @return the value of shop_goods.pos_goods_id
     *
     * @mbg.generated
     */
    public Long getPosGoodsId() {
        return posGoodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_goods.pos_goods_id
     *
     * @param posGoodsId the value for shop_goods.pos_goods_id
     *
     * @mbg.generated
     */
    public void setPosGoodsId(Long posGoodsId) {
        this.posGoodsId = posGoodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_goods.create_time
     *
     * @return the value of shop_goods.create_time
     *
     * @mbg.generated
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_goods.create_time
     *
     * @param createTime the value for shop_goods.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_goods.del_flag
     *
     * @return the value of shop_goods.del_flag
     *
     * @mbg.generated
     */
    public Boolean getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_goods.del_flag
     *
     * @param delFlag the value for shop_goods.del_flag
     *
     * @mbg.generated
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }
}
