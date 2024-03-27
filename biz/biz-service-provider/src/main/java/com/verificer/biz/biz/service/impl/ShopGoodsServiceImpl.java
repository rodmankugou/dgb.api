package com.verificer.biz.biz.service.impl;

import com.verificer.biz.beans.enums.MerType;
import com.verificer.biz.biz.entity.Goods;
import com.verificer.biz.biz.entity.ShopGoods;
import com.verificer.biz.biz.entity.Spec;
import com.verificer.biz.biz.mapper.ShopGoodsMapper;
import com.verificer.biz.biz.service.*;
import com.verificer.biz.biz.service.core.goods.GoodsPublishService;
import com.verificer.biz.biz.service.core.goods.notify.IGoodsPublishListener;
import com.verificer.biz.biz.service.core.goods.notify.PublishGoodsEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ShopGoodsServiceImpl implements ShopGoodsService {
    @Autowired
    ShopGoodsMapper mapper;

    @Autowired
    PosGoodsSyncService posGoodsSyncService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    SpecService specService;

    @Autowired
    GoodsPublishService goodsPublishService;

    @PostConstruct
    void init(){
        goodsPublishService.addListener(new IGoodsPublishListener() {
            @Override
            public void onEvent(PublishGoodsEvent e) {
                if(MerType.SHOP.equals(e.getMerType()))
                    addGoodsIfNotExist(e.getMerId(),e.getGoodsId(),e.getSpecId());
            }
        });
    }

    @Override
    public void addGoodsIfNotExist(String shopId, Long goodsId, Long specId) {
        Goods goods = goodsService.getById(goodsId);
        Spec spec = specService.getById(specId);
        ShopGoods sg = new ShopGoods();

        if(mapper.selectByShopIdAndSpecId(shopId,specId) != null)
            return;
        sg.setGoodsId(goodsId);
        sg.setSpecId(specId);
        sg.setShopId(shopId);
        sg.setCreateTime(System.currentTimeMillis());
        sg.setDelFlag(false);
        mapper.insertSelective(sg);

        posGoodsSyncService.onAddGoodsToShop(shopId,goods,spec);
    }

    @Override
    public List<ShopGoods> getBySpecId(Long specId) {
        return mapper.selectBySpecId(specId);
    }

    @Override
    public ShopGoods selectByPosGoodsId(Long posGoodsId) {
        return mapper.selectByPosGoodsId(posGoodsId);
    }

    @Override
    public void bindPosGoodsId(String shopId,Long specId, Long uid) {
        ShopGoods goods =  mapper.selectByShopIdAndSpecId(shopId,specId);
        goods.setPosGoodsId(uid);
        mapper.updateByPrimaryKeySelective(goods);
    }

    @Override
    public ShopGoods getByShopIdAndSpecId(String shopId, Long specId) {
        return mapper.selectByShopIdAndSpecId(shopId,specId);
    }

    @Override
    public List<Long> getEffGoodsIsByShopId(String shopId) {
        return mapper.selectEffGoodsIsByShopId( shopId);
    }
}

