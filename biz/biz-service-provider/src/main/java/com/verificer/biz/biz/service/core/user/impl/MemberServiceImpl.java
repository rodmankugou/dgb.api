package com.verificer.biz.biz.service.core.user.impl;

import com.verificer.GlobalConfig;
import com.verificer.account.itf.AccTools;
import com.verificer.account.itf.BaseAccountService;
import com.verificer.beans.account.AccountVo;
import com.verificer.biz.beans.enums.MemberOrdSta;
import com.verificer.biz.beans.vo.user.member.*;
import com.verificer.biz.biz.mapper.BizMemberMapper;
import com.verificer.biz.biz.mapper.MemberOrderMapper;
import com.verificer.biz.biz.service.core.user.MemberOrderService;
import com.verificer.biz.biz.service.core.user.MemberService;
import com.verificer.utils.SBeanUtils;
import com.verificer.utils.SBigDecimalUtils;
import com.verificer.utils.SDateUtil;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberOrderMapper memberOrderMapper;

    @Autowired
    BizMemberMapper bizMemberMapper;

    @Autowired
    BaseAccountService baseAccountService;


    @Override
    public MemberStaVo memberSta() {

        MemberStaQryVo allQryVo = new MemberStaQryVo();
        allQryVo.setStatus(MemberOrdSta.SUC.getValue());

        MemberStaQryVo monthQryVo = new MemberStaQryVo();
        monthQryVo.setStatus(MemberOrdSta.SUC.getValue());
        monthQryVo.setsTime(SDateUtil.getRangeBeginTime(System.currentTimeMillis(), Calendar.MONTH).getTime());
        monthQryVo.seteTime(System.currentTimeMillis());

        MemberStaBaseVo all = memberOrderMapper.memberSta(allQryVo);
        MemberStaBaseVo month = memberOrderMapper.memberSta(monthQryVo);

        MemberStaVo vo = new MemberStaVo();
        vo.setTotalCount(all.getTotalCount());
        vo.setTotalAmount(all.getTotalAmount());
        vo.setMonthCount(month.getTotalCount());
        vo.setMonthAmount(month.getTotalAmount());
        return vo;
    }

    @Override
    public List<MemberRankVo> memberRank() {
        List<MemberRankVo> voList = bizMemberMapper.rank();
        BigDecimal total = BigDecimal.ZERO;
        for(MemberRankVo r : voList)
            total = total.add(new BigDecimal(r.getCount()));

        for(MemberRankVo r : voList){
            BigDecimal percent = new BigDecimal(r.getCount()).divide(total, GlobalConfig.PERCENT_PREC, RoundingMode.HALF_UP);
            r.setPercent(SBigDecimalUtils.formatPercent(percent,GlobalConfig.PERCENT_PREC));
        }
        return voList;
    }

    @Override
    public List<MemberVo> memberPage(MemberPageVo reqVo) {

        List<MemberVo> voList = bizMemberMapper.page(reqVo);
        for(MemberVo vo : voList){
            AccountVo accountVo = baseAccountService.getAccount(vo.getUid(), AccTools.USR_INTEGRAL);
            if(accountVo != null)
                vo.setTotalIntegral(accountVo.getAvailableAmount());
        }
        return voList;
    }

    @Override
    public int memberCount(MemberPageVo reqVo) {
        return bizMemberMapper.count(reqVo);
    }

    @Override
    public int shopMemberCount(String shopId) {
        return bizMemberMapper.shopMemberCount(shopId);
    }


}
