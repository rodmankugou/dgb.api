package com.verificer.biz.biz.service;

import com.verificer.biz.beans.vo.req.SpecReqVo;

import java.util.List;

public interface SpecService {
    void add(Long goodsId, List<SpecReqVo> specList);
}
