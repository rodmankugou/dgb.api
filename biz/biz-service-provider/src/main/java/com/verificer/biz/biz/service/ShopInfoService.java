package com.verificer.biz.biz.service;

import com.verificer.biz.beans.vo.req.ShopFormVo;

public interface ShopInfoService {
    void add(Long shopId,ShopFormVo formVo);
    void update(ShopFormVo formVo);
}
