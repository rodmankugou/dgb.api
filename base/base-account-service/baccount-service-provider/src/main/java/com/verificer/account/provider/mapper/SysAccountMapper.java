package com.verificer.account.provider.mapper;

import com.verificer.account.provider.entity.SysAccount;
import com.verificer.beans.SysAccountQueryVo;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SysAccountMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_account
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_account
     *
     * @mbg.generated
     */
    int insert(SysAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_account
     *
     * @mbg.generated
     */
    int insertSelective(SysAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_account
     *
     * @mbg.generated
     */
    SysAccount selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_account
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SysAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_account
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SysAccount record);

    SysAccount selectByPrimaryKeyForUpdate(@Param("id") Long id);

    List<SysAccount> list(SysAccountQueryVo queryVo);

    SysAccount selectBySubName(@Param("subName") String subName);

    List<SysAccount> selectByIdForUpdate(List<Long> idList);
}