package com.verificer.biz.biz.service;

import com.verificer.biz.biz.entity.GoodsSta;

import java.util.List;

public interface GoodsStaService {

    void add(Long goodsId);

    void add(Long goodsId,Long specId);


    GoodsSta getBySpecId(Long specId);

    List<GoodsSta> getAll(Boolean sumStaFlag);
}
