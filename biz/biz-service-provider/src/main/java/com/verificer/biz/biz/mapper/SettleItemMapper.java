package com.verificer.biz.biz.mapper;

import com.verificer.biz.beans.vo.settle.req.SettleItemQryVo;
import com.verificer.biz.biz.entity.SettleItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SettleItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table settle_item
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table settle_item
     *
     * @mbg.generated
     */
    int insert(SettleItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table settle_item
     *
     * @mbg.generated
     */
    int insertSelective(SettleItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table settle_item
     *
     * @mbg.generated
     */
    SettleItem selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table settle_item
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SettleItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table settle_item
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SettleItem record);

    List<SettleItem> page(SettleItemQryVo reqVo);

    int count(SettleItemQryVo reqVo);

    List<SettleItem> selectByOrderId(@Param("orderId") Long orderId);

    void updSettleFlagByOrderId(@Param("orderId") Long orderId);
}
