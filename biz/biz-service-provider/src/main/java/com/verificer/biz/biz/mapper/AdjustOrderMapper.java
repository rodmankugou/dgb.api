package com.verificer.biz.biz.mapper;

import com.verificer.biz.beans.vo.adjust.req.AdjOrderQryVo;
import com.verificer.biz.biz.entity.AdjustOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdjustOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table adjust_order
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table adjust_order
     *
     * @mbg.generated
     */
    int insert(AdjustOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table adjust_order
     *
     * @mbg.generated
     */
    int insertSelective(AdjustOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table adjust_order
     *
     * @mbg.generated
     */
    AdjustOrder selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table adjust_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AdjustOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table adjust_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(AdjustOrder record);

    List<AdjustOrder> page(AdjOrderQryVo qryVo);

    int count(AdjOrderQryVo qryVo);

    AdjustOrder selectByOrdNum(@Param("ordNum") String ordNum);

    AdjustOrder getAndLock(@Param("id") Long id);
}
