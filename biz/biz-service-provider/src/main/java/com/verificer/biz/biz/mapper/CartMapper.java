package com.verificer.biz.biz.mapper;

import com.verificer.biz.beans.vo.cart.req.CartQryVo;
import com.verificer.biz.biz.entity.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cart
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cart
     *
     * @mbg.generated
     */
    int insert(Cart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cart
     *
     * @mbg.generated
     */
    int insertSelective(Cart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cart
     *
     * @mbg.generated
     */
    Cart selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cart
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Cart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cart
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Cart record);

    List<Cart> list(CartQryVo reqVo);

    Cart selectBySpecIdAndOthers(@Param("userId") Long userId,
                                 @Param("specId") Long specId,
                                 @Param("shopFlag") Boolean shopFlag,
                                 @Param("shopId") String shopId);
}