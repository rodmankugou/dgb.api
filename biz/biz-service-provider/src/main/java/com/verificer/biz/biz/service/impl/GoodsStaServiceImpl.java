package com.verificer.biz.biz.service.impl;

import com.verificer.biz.biz.entity.Goods;
import com.verificer.biz.biz.entity.GoodsSta;
import com.verificer.biz.biz.mapper.GoodsStaMapper;
import com.verificer.biz.biz.service.GoodsStaService;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.sasl.SaslClient;

@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsStaServiceImpl implements GoodsStaService {

    @Autowired
    GoodsStaMapper goodsStaMapper;


    public GoodsStaMapper getGoodsStaMapper() {
        return goodsStaMapper;
    }

    public void setGoodsStaMapper(GoodsStaMapper goodsStaMapper) {
        this.goodsStaMapper = goodsStaMapper;
    }


    private void mCheck(GoodsSta m){
        SCheckUtil.notEmpty(m.getGoodsId(),"GoodsSta.GoodsId");
        SCheckUtil.notEmpty(m.getSumStaFlag(),"GoodsSta.SumStaFlag");
        if(!m.getSumStaFlag())
            SCheckUtil.notEmpty(m.getSpecId(),"GoodsSta.SpecId");

        SCheckUtil.notEmpty(m.getPlaSaleCount(),"GoodsSta.PlaSaleCount");
        SCheckUtil.notEmpty(m.getPlaStageCount(),"GoodsSta.PlaStageCount");
        SCheckUtil.notEmpty(m.getShopSaleCount(),"GoodsSta.ShopSaleCount");
        SCheckUtil.notEmpty(m.getShopStageCount(),"GoodsSta.ShopStageCount");
        SCheckUtil.notEmpty(m.getEvaluateCount(),"GoodsSta.EvaluateCount");
    }

    @Override
    public void add(Long goodsId) {
        add(goodsId,null);
    }


    @Override
    public void add(Long goodsId,Long specId) {
        GoodsSta gs = new GoodsSta();
        gs.setGoodsId(goodsId);
        gs.setSpecId(specId);
        gs.setSumStaFlag(specId == null ? true : false);
        gs.setPlaSaleCount(0);
        gs.setPlaStageCount(0);
        gs.setShopSaleCount(0);
        gs.setShopStageCount(0);
        gs.setEvaluateCount(0);

        mCheck(gs);
        goodsStaMapper.insertSelective(gs);

    }
}
