package com.verificer.biz.biz.service;

import com.verificer.biz.beans.vo.req.SpecReqVo;
import com.verificer.biz.biz.entity.Spec;

import java.util.List;

public interface SpecService {
    void add(Long goodsId, List<SpecReqVo> specList);

    Spec getById(Long specId);
}
