package com.verificer.biz.biz.service.core.user.impl;

import com.verificer.GlobalConfig;
import com.verificer.account.itf.AccTools;
import com.verificer.account.itf.BaseAccountService;
import com.verificer.beans.IdVo;
import com.verificer.beans.account.AccountVo;
import com.verificer.biz.beans.enums.MemberOrdSta;
import com.verificer.biz.beans.enums.MemberRefType;
import com.verificer.biz.beans.vo.member.AppMemberVo;
import com.verificer.biz.beans.vo.user.UserVo;
import com.verificer.biz.beans.vo.user.member.*;
import com.verificer.biz.biz.entity.Shop;
import com.verificer.biz.biz.entity.User;
import com.verificer.biz.biz.mapper.BizMemberMapper;
import com.verificer.biz.biz.mapper.MemberOrderMapper;
import com.verificer.biz.biz.service.common.ShopCommon;
import com.verificer.biz.biz.service.common.UserCommon;
import com.verificer.biz.biz.service.core.user.MemberOrderService;
import com.verificer.biz.biz.service.core.user.MemberService;
import com.verificer.utils.SBeanUtils;
import com.verificer.utils.SBigDecimalUtils;
import com.verificer.utils.SDateUtil;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.check.SCheckUtil;
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

    @Autowired
    UserCommon userCommon;

    @Autowired
    ShopCommon shopCommon;

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

    @Override
    public AppMemberVo memberInfo(IdVo idVo) {
        SCheckUtil.notEmpty(idVo.getId(),"ID");
        User user = userCommon.getById(idVo.getId());

        AppMemberVo abv = new AppMemberVo();
        abv.setMemberFlag(user.getMemberFlag());
        if(abv.getMemberFlag()){
            abv.setNickName(user.getMemberNickname());
            long restDays = (user.getMemberETime()- SDateUtil.getDayStartTime(System.currentTimeMillis())) / SDateUtil.MS_PER_DAY + 1;
            long restDay = restDays < 0 ? 0 : restDays;
            abv.setRestDay(restDay);
            abv.setNum(user.getUid());
            abv.setsTime(user.getMemberSTime());
            abv.seteTime(user.getMemberETime());
            if(MemberRefType.SHOP.getValue() == user.getMemberRefType() && !SStringUtils.isEmpty(user.getMemberRefId())){
                abv.setRefShopName(shopCommon.getName(user.getMemberRefId()));
                abv.setShopQrCode("referrerType="+MemberRefType.SHOP.toString()+"&referrerId="+user.getMemberRefId());
            }
        }
        return abv;
    }


}
