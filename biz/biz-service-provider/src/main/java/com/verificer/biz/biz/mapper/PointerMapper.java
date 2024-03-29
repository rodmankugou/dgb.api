package com.verificer.biz.biz.mapper;

import com.verificer.biz.biz.entity.Pointer;
import org.apache.ibatis.annotations.Param;

public interface PointerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pointer
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pointer
     *
     * @mbg.generated
     */
    int insert(Pointer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pointer
     *
     * @mbg.generated
     */
    int insertSelective(Pointer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pointer
     *
     * @mbg.generated
     */
    Pointer selectByPrimaryKey(String code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pointer
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Pointer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pointer
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Pointer record);

    Pointer selectByPrimaryKeyForUpd(@Param("code") String code);
}
