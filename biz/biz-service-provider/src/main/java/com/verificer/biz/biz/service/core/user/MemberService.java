package com.verificer.biz.biz.service.core.user;

import com.verificer.beans.IdVo;
import com.verificer.biz.beans.vo.member.AppMemberVo;
import com.verificer.biz.beans.vo.user.member.MemberPageVo;
import com.verificer.biz.beans.vo.user.member.MemberRankVo;
import com.verificer.biz.beans.vo.user.member.MemberStaVo;
import com.verificer.biz.beans.vo.user.member.MemberVo;

import java.util.List;

public interface MemberService {

    /**
     * 会员概况
     * @return
     */
    MemberStaVo memberSta();

    /**
     * 会员排行榜（按省份）
     * @return
     */
    List<MemberRankVo> memberRank();

    List<MemberVo> memberPage(MemberPageVo reqVo);

    int memberCount(MemberPageVo reqVo);

    int shopMemberCount(String shopId);

    /**
     * 获取会员信息
     * @param idVo
     * @return
     */
    AppMemberVo memberInfo(IdVo idVo);
}
