package com.verificer.base.auth.mapper;

import com.verificer.base.auth.entity.Res;

public interface ResMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table res
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table res
     *
     * @mbg.generated
     */
    int insert(Res record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table res
     *
     * @mbg.generated
     */
    int insertSelective(Res record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table res
     *
     * @mbg.generated
     */
    Res selectByPrimaryKey(String code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table res
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Res record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table res
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Res record);
}