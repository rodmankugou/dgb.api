package com.verificer.base_user.mapper;

import com.verificer.base_user.entity.InviteRecord;

public interface InviteRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invite_record
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invite_record
     *
     * @mbg.generated
     */
    int insert(InviteRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invite_record
     *
     * @mbg.generated
     */
    int insertSelective(InviteRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invite_record
     *
     * @mbg.generated
     */
    InviteRecord selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invite_record
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(InviteRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invite_record
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(InviteRecord record);
}