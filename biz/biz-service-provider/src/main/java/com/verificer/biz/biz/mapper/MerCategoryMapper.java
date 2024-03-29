package com.verificer.biz.biz.mapper;

import com.verificer.biz.biz.entity.MerCategory;
import org.apache.ibatis.annotations.Param;

public interface MerCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mer_category
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mer_category
     *
     * @mbg.generated
     */
    int insert(MerCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mer_category
     *
     * @mbg.generated
     */
    int insertSelective(MerCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mer_category
     *
     * @mbg.generated
     */
    MerCategory selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mer_category
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MerCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mer_category
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(MerCategory record);

    MerCategory selectByMerIdAndCatId(@Param("merId") String merId,@Param("categoryId") Long catId);
}
