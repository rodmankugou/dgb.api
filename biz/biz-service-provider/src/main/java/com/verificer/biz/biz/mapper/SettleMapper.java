package com.verificer.biz.biz.mapper;

import com.verificer.biz.beans.vo.settle.SettleStaVo;
import com.verificer.biz.beans.vo.settle.req.SettleStaQryVo;
import com.verificer.biz.beans.vo.user.member.MemberStaVo;
import com.verificer.biz.biz.entity.Settle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SettleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table settle
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table settle
     *
     * @mbg.generated
     */
    int insert(Settle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table settle
     *
     * @mbg.generated
     */
    int insertSelective(Settle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table settle
     *
     * @mbg.generated
     */
    Settle selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table settle
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Settle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table settle
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Settle record);

    SettleStaVo sta(SettleStaQryVo reqVo);

    Settle getReadySettle(@Param("now") Long now);

    Settle getAndLock(@Param("id") Long id);

    void addSettlePhase(@Param("ids") List<Long> ids);

    Settle getMatchSettle(@Param("shopId") String shopId,@Param("nextCalTime") Long nextCalTime);
}