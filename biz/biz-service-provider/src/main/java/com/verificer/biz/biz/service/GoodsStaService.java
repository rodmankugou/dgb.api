package com.verificer.biz.biz.service;

import com.verificer.biz.biz.entity.GoodsSta;

public interface GoodsStaService {

    void add(Long goodsId);

    void add(Long goodsId,Long specId);


    GoodsSta getBySpecId(Long specId);
}
