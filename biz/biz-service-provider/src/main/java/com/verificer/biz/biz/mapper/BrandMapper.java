package com.verificer.biz.biz.mapper;

import com.verificer.biz.beans.vo.req.BrandListQryVo;
import com.verificer.biz.beans.vo.req.BrandPageQryVo;
import com.verificer.biz.biz.entity.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table brand
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table brand
     *
     * @mbg.generated
     */
    int insert(Brand record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table brand
     *
     * @mbg.generated
     */
    int insertSelective(Brand record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table brand
     *
     * @mbg.generated
     */
    Brand selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table brand
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Brand record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table brand
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Brand record);

    List<Brand> page(BrandPageQryVo qryVo);

    int count(BrandPageQryVo qryVo);

    List<Brand> list(BrandListQryVo qryVo);

    Brand selectByNameLimit1(@Param("name") String name);

    void del(@Param("id") Long id);

    List<Brand> selectAll();
}
