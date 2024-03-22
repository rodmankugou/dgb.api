package com.verificer.biz.biz.mapper;

import com.verificer.biz.beans.vo.user.RefereeVo;
import com.verificer.biz.beans.vo.user.ReferrerStaVo;
import com.verificer.biz.beans.vo.user.UserWithdrawVo;
import com.verificer.biz.beans.vo.user.member.MemberPageVo;
import com.verificer.biz.beans.vo.user.member.MemberRankVo;
import com.verificer.biz.beans.vo.user.member.MemberVo;
import com.verificer.biz.beans.vo.user.req.RefereeListReqVo;
import com.verificer.biz.beans.vo.user.req.RefereeStaReqVo;
import com.verificer.biz.beans.vo.user.req.ReferrerWithdrawPageReqVo;
import com.verificer.biz.beans.vo.user.req.SumAccAmountVo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BizMemberMapper {

    List<MemberRankVo> rank();

    List<MemberVo> page(MemberPageVo reqVo);

    int count(MemberPageVo reqVo);

    int shopMemberCount(@Param("shopId") String shopId);
}
