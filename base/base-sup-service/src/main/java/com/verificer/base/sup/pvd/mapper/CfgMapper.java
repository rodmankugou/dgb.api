package com.verificer.base.sup.pvd.mapper;

import com.verificer.base.sup.pvd.entity.Cfg;

import java.util.List;

public interface CfgMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg
     *
     * @mbg.generated
     */
    int insert(Cfg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg
     *
     * @mbg.generated
     */
    int insertSelective(Cfg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg
     *
     * @mbg.generated
     */
    Cfg selectByPrimaryKey(String code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Cfg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Cfg record);

    List<Cfg> selectAll();
}