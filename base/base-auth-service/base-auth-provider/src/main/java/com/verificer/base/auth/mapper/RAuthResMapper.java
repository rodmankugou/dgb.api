package com.verificer.base.auth.mapper;

import com.verificer.base.auth.entity.RAuthRes;

public interface RAuthResMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table r_auth_res
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table r_auth_res
     *
     * @mbg.generated
     */
    int insert(RAuthRes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table r_auth_res
     *
     * @mbg.generated
     */
    int insertSelective(RAuthRes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table r_auth_res
     *
     * @mbg.generated
     */
    RAuthRes selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table r_auth_res
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RAuthRes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table r_auth_res
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RAuthRes record);
}