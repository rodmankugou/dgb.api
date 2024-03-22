package com.verificer.biz.biz.service.core.user;

import com.verificer.biz.beans.vo.member.MemberTypeVo;
import com.verificer.biz.biz.entity.MemberType;

import java.util.List;

public interface MemberTypeService {
    List<MemberTypeVo> memberTypeList();

    MemberType getById(Long memberTypeId);
}
