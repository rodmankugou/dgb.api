package com.verificer.biz.biz.mapper;

import com.verificer.biz.biz.entity.ShopGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopGoodsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_goods
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_goods
     *
     * @mbg.generated
     */
    int insert(ShopGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_goods
     *
     * @mbg.generated
     */
    int insertSelective(ShopGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_goods
     *
     * @mbg.generated
     */
    ShopGoods selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_goods
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ShopGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_goods
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ShopGoods record);

    List<ShopGoods> selectBySpecId(@Param("specId") Long specId);
}
