package com.verificer.biz.biz.service.impl;

import com.verificer.biz.biz.entity.MerCategory;
import com.verificer.biz.biz.mapper.MerCategoryMapper;
import com.verificer.biz.biz.service.MerCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class MerCatServiceImpl implements MerCatService {
    @Autowired
    MerCategoryMapper mapper;

    @Override
    public MerCategory getByShopIdAndCatId(String shopId, Long categoryId) {
        return mapper.selectByMerIdAndCatId(shopId,categoryId);
    }
}
