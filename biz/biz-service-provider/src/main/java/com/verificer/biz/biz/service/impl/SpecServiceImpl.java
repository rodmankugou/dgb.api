package com.verificer.biz.biz.service.impl;

import com.verificer.ErrCode;
import com.verificer.biz.beans.vo.SpecVo;
import com.verificer.biz.beans.vo.req.SpecReqVo;
import com.verificer.biz.biz.entity.Spec;
import com.verificer.biz.biz.mapper.SpecMapper;
import com.verificer.biz.biz.service.GoodsStaService;
import com.verificer.biz.biz.service.SpecService;
import com.verificer.biz.biz.service.StockService;
import com.verificer.common.exception.BaseException;
import com.verificer.utils.SBeanUtils;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class SpecServiceImpl implements SpecService {
    @Autowired
    SpecMapper mapper;

    @Autowired
    StockService stockService;

    @Autowired
    GoodsStaService goodsStaService;

    private void mCheck(Spec e){
        SCheckUtil.notEmpty(e.getGoodsId(),"Specification.GoodsId");
        SCheckUtil.notEmpty(e.getName(),"Specification.Name");//需
        SCheckUtil.notEmpty(e.getImg(),"Specification.Img");
        SCheckUtil.notEmpty(e.getDelFlag(),"Specification.DelFlag");  //需
        SCheckUtil.notEmpty(e.getPrice(),"Specification.Price");

    }

    @Override
    public void add(Long goodsId, List<SpecReqVo> specList) {
        if(goodsId == null)
            throw new RuntimeException("Goods Id can not be null");
        for(SpecReqVo spec : specList)
            add(goodsId,spec);

    }

    @Override
    public Spec getById(Long specId) {
        return mapper.selectByPrimaryKey(specId);
    }

    @Override
    public List<SpecVo> getGoodsSpecVoList(Long goodsId) {
        List<Spec> specList = mapper.selectByGoodsId(goodsId);
        List<SpecVo> voList = new LinkedList<>();
        for(Spec spec : specList){
            SpecVo specVo = new SpecVo();
            SBeanUtils.copyProperties2(spec,specVo);
            voList.add(specVo);
        }
        return voList;
    }

    private void addOrUpdate(Long goodsId, SpecReqVo reqVo){
        if(reqVo.getId() != null)
            add(goodsId,reqVo);
        else
            upd(goodsId,reqVo);
    }

    private void add(Long goodsId,SpecReqVo reqVo){
        Spec spec = new Spec();
        SBeanUtils.copyProperties2(reqVo,spec);
        spec.setGoodsId(goodsId);
        spec.setCreateTime(System.currentTimeMillis());
        spec.setDelFlag(false);
        spec.setPrice(reqVo.getPrice());
        spec.setwPrice(reqVo.getwPrice());

        mCheck(spec);
        mapper.insertSelective(spec);
        goodsStaService.add(goodsId, spec.getId());


        stockService.addStageStockIfNotExist(spec.getGoodsId(),spec.getId(),reqVo.getStageIds());
    }

    private void upd(Long goodsId,SpecReqVo reqVo){
        SCheckUtil.notEmpty(reqVo.getId(),"Specification.ID");

        Spec old = mapper.selectByPrimaryKey(reqVo.getId());
        if(old == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        if(old.getDelFlag())
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        Spec e = new Spec();
        SBeanUtils.copyProperties2(reqVo,e);
        e.setCreateTime(old.getCreateTime());
        e.setDelFlag(old.getDelFlag());
        e.setGoodsId(old.getGoodsId());

        mCheck(e);
        mapper.updateByPrimaryKeySelective(e);

        stockService.addStageStockIfNotExist(old.getGoodsId(),old.getId(),reqVo.getStageIds());

    }
}
