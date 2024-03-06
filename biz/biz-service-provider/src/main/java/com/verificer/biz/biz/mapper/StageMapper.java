package com.verificer.biz.biz.mapper;

import com.verificer.biz.beans.vo.req.StagePageVo;
import com.verificer.biz.biz.entity.Brand;
import com.verificer.biz.biz.entity.Stage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stage
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stage
     *
     * @mbg.generated
     */
    int insert(Stage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stage
     *
     * @mbg.generated
     */
    int insertSelective(Stage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stage
     *
     * @mbg.generated
     */
    Stage selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stage
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Stage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stage
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Stage record);

    List<Stage> page(StagePageVo qryVo);

    int count(StagePageVo qryVo);

    Stage selectByNameLimit1(@Param("name") String name);

    List<Stage> list();
}
