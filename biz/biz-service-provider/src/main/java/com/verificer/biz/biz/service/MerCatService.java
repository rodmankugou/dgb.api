package com.verificer.biz.biz.service;

import com.verificer.biz.biz.entity.MerCategory;

public interface MerCatService {
    MerCategory getByShopIdAndCatId(String shopId, Long categoryId);
}
