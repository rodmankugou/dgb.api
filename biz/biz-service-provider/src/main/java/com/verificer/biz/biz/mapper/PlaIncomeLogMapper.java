package com.verificer.biz.biz.mapper;

import com.verificer.biz.beans.vo.settle.PlaIncomeLogVo;
import com.verificer.biz.beans.vo.settle.req.PlaIncomeLogQryVo;
import com.verificer.biz.biz.entity.PlaIncomeLog;

import java.util.List;

public interface PlaIncomeLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pla_income_log
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pla_income_log
     *
     * @mbg.generated
     */
    int insert(PlaIncomeLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pla_income_log
     *
     * @mbg.generated
     */
    int insertSelective(PlaIncomeLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pla_income_log
     *
     * @mbg.generated
     */
    PlaIncomeLog selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pla_income_log
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PlaIncomeLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pla_income_log
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PlaIncomeLog record);

    List<PlaIncomeLogVo> page(PlaIncomeLogQryVo reqVo);

    int count(PlaIncomeLogQryVo reqVo);
}
