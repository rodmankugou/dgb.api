package com.verificer.biz.biz.service.core.user.impl;

import com.verificer.GlobalConfig;
import com.verificer.biz.beans.enums.MemberTypeTimeUnit;
import com.verificer.biz.beans.vo.member.MemberTypeVo;
import com.verificer.biz.biz.entity.MemberType;
import com.verificer.biz.biz.mapper.MemberTypeMapper;
import com.verificer.biz.biz.service.core.user.MemberTypeService;
import com.verificer.common.exception.BizErrMsgException;
import com.verificer.utils.SBeanUtils;
import com.verificer.utils.SDateUtil;
import jnr.a64asm.Mem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberTypeServiceImpl implements MemberTypeService {
    @Autowired
    MemberTypeMapper mapper;

    private MemberTypeVo toVo(MemberType e){
        if(e == null)
            return  null;
        MemberTypeVo vo = new MemberTypeVo();

        SBeanUtils.copyProperties2(e,vo);
        vo.setPricePerDay(e.getPrice().divide(new BigDecimal(getDays(e)), GlobalConfig.PREC, RoundingMode.HALF_UP));
        return vo;
    }

    private List<MemberTypeVo> toVoList(List<MemberType> list){

        List<MemberTypeVo> voList = new LinkedList<>();
        for(MemberType e : list){
            voList.add(toVo(e));
        }
        return voList;
    }

    @Override
    public List<MemberTypeVo> memberTypeList() {
        List<MemberType> list = mapper.selectAll();
        return toVoList(list);
    }

    @Override
    public MemberType getById(Long memberTypeId) {
        return mapper.selectByPrimaryKey(memberTypeId);
    }

    private Integer getDays(MemberType type){
        if (MemberTypeTimeUnit.YEAR.getValue() == type.getTimeUnit()) {
            return 365;
        } else if (MemberTypeTimeUnit.MONTH.getValue() == type.getTimeUnit()) {
            return  30;
        } else if (MemberTypeTimeUnit.DAY.getValue() == type.getTimeUnit()) {
            return  1;
        }else {
            throw new BizErrMsgException("Illegal Member type time unit");
        }
    }


    @Override
    public Long getEndTime(Long now, MemberType type) {
        int dayOffset = 0;
        int monthOffset = 0;
        int yearOffset = 0;
        if (MemberTypeTimeUnit.YEAR.getValue() == type.getTimeUnit()) {
            yearOffset = type.getTimeCount();
        } else if (MemberTypeTimeUnit.MONTH.getValue() == type.getTimeUnit()) {
            monthOffset = type.getTimeCount();
        } else if (MemberTypeTimeUnit.DAY.getValue() == type.getTimeUnit()) {
            dayOffset = type.getTimeCount();
        } else {
            throw new BizErrMsgException("Illegal Member type time unit");
        }
        Long eTime = SDateUtil.offsetDate(now, dayOffset, monthOffset, yearOffset);
        eTime = SDateUtil.getDayEndTime(eTime);

        return eTime;
    }
}
