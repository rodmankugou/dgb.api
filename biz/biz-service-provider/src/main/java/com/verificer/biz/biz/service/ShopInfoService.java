package com.verificer.biz.biz.service;

import com.verificer.biz.beans.vo.req.ShopFormVo;

public interface ShopInfoService {
    void add(String shopId,ShopFormVo formVo);
    void update(ShopFormVo formVo);
}
