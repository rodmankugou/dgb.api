package com.verificer.biz.biz.service.impl;

import com.verificer.ErrCode;
import com.verificer.biz.beans.vo.SpecVo;
import com.verificer.biz.beans.vo.req.SpecReqVo;
import com.verificer.biz.biz.entity.Goods;
import com.verificer.biz.biz.entity.Spec;
import com.verificer.biz.biz.mapper.SpecMapper;
import com.verificer.biz.biz.service.GoodsStaService;
import com.verificer.biz.biz.service.SpecService;
import com.verificer.biz.biz.service.core.stock.StockCoreService;
import com.verificer.common.exception.BaseException;
import com.verificer.utils.SBeanUtils;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class SpecServiceImpl implements SpecService {
    @Autowired
    SpecMapper mapper;

    @Autowired
    StockCoreService stockCoreService;

    @Autowired
    GoodsStaService goodsStaService;

    private void mCheck(Goods goods,Spec e){
        SCheckUtil.notEmpty(e.getGoodsId(),"Specification.GoodsId");
        SCheckUtil.notEmpty(e.getName(),"Specification.Name");//需
        SCheckUtil.notEmpty(e.getImg(),"Specification.Img");
        SCheckUtil.notEmpty(e.getDelFlag(),"Specification.DelFlag");  //需
        SCheckUtil.notEmpty(e.getPrice(),"Specification.Price");

        if(goods.getAppSaleFlag())
            checkSPrice(e);

        if(goods.getPosByWeightFlag())
            checkWPrice(e);

        if(!goods.getPosByWeightFlag())
            checkSPrice(e);


    }

    private void checkAllPrice(Spec e){
        checkSPrice(e);
        checkWPrice(e);
    }

    private void checkWPrice(Spec e){
        SCheckUtil.lgThanAndNotNull(e.getwPrice(),false,BigDecimal.ZERO,"Spec.wPrice");
        SCheckUtil.lgThanAndNotNull(e.getwOriPrice(),false,BigDecimal.ZERO,"Spec.wOriPrice");
    }

    private void checkSPrice(Spec e){
        SCheckUtil.lgThanAndNotNull(e.getPrice(),false,BigDecimal.ZERO,"Spec.price");
        SCheckUtil.lgThanAndNotNull(e.getOriPrice(),false,BigDecimal.ZERO,"Spec.oriPrice");
    }

    @Override
    public void add(Goods goods, List<SpecReqVo> specList) {
        if(goods.getId() == null)
            throw new RuntimeException("Goods Id can not be null");
        for(SpecReqVo spec : specList)
            add(goods,spec);

    }

    @Override
    public Spec getById(Long specId) {
        return mapper.selectByPrimaryKey(specId);
    }

    @Override
    public Spec getActById(Long specId) {
        Spec spec = getById(specId);
        if(spec == null || spec.getDelFlag())
            return null;
        return spec;
    }

    @Override
    public List<SpecVo> getGoodsSpecVoList(Long goodsId) {
        List<SpecVo> voList = mapper.selectVoByGoodsId(goodsId);
//        List<SpecVo> voList = new LinkedList<>();
//        for(Spec spec : specList){
//            SpecVo specVo = new SpecVo();
//            SBeanUtils.copyProperties2(spec,specVo);
//            voList.add(specVo);
//            GoodsSta sta = goodsStaService.select
//        }
        return voList;
    }

    @Override
    public List<Spec> getGoodsSpecList(Long id) {
        return mapper.selectByGoodsId(id);
    }



    private void addOrUpdate(Goods goods, SpecReqVo reqVo){
        if(reqVo.getId() != null)
            add(goods,reqVo);
        else
            upd(goods,reqVo);
    }

    private void add(Goods goods, SpecReqVo reqVo){
        Spec spec = new Spec();
        SBeanUtils.copyProperties2(reqVo,spec);
        spec.setGoodsId(goods.getId());
        spec.setCreateTime(System.currentTimeMillis());
        spec.setDelFlag(false);
        spec.setPrice(reqVo.getPrice());
        spec.setwPrice(reqVo.getwPrice());

        mCheck(goods,spec);
        mapper.insertSelective(spec);
        goodsStaService.add(goods.getId(), spec.getId());


    }

    @Override
    public void upd(Goods goods, List<SpecReqVo> list) {

        //TODO 暂时不支持删除
        for(SpecReqVo spec : list){
            if(spec.getId() == null){
                add(goods,spec);
                continue;
            }

            Spec old = mapper.selectByPrimaryKey(spec.getId());
            if(old == null)
                throw new BaseException(ErrCode.RECORD_NOT_EXIST);

            old.setPrice(spec.getPrice());
            old.setwPrice(spec.getwPrice());
            old.setName(spec.getName());
            old.setImg(spec.getImg());
            mapper.updateByPrimaryKeySelective(old);
        }
    }

    private void upd(Goods goods,SpecReqVo reqVo){
        SCheckUtil.notEmpty(reqVo.getId(),"Specification.ID");

        Spec old = mapper.selectByPrimaryKey(reqVo.getId());
        if(old == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        if(old.getDelFlag())
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        Spec e = new Spec();
        SBeanUtils.copyProperties2(reqVo,e);
        e.setGoodsId(goods.getId());
        e.setCreateTime(old.getCreateTime());
        e.setDelFlag(old.getDelFlag());
        e.setGoodsId(old.getGoodsId());

        mCheck(goods,e);
        mapper.updateByPrimaryKeySelective(e);


    }
}
