package com.verificer.biz.biz.mapper;

import com.verificer.biz.beans.vo.SpecVo;
import com.verificer.biz.biz.entity.Spec;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface SpecMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spec
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spec
     *
     * @mbg.generated
     */
    int insert(Spec record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spec
     *
     * @mbg.generated
     */
    int insertSelective(Spec record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spec
     *
     * @mbg.generated
     */
    Spec selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spec
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Spec record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spec
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Spec record);

    List<Spec> selectByGoodsId(@Param("goodsId") Long goodsId);

    List<SpecVo> selectVoByGoodsId(@Param("goodsId") Long goodsId);
}
