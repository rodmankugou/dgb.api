package com.verificer.biz.biz.service.core.user.impl;

import com.verificer.biz.beans.vo.member.MemberTypeVo;
import com.verificer.biz.biz.entity.MemberType;
import com.verificer.biz.biz.mapper.MemberTypeMapper;
import com.verificer.biz.biz.service.core.user.MemberTypeService;
import com.verificer.utils.SBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
