package com.verificer.biz.biz.service.impl;

import com.verificer.ErrCode;
import com.verificer.biz.beans.vo.BrandVo;
import com.verificer.biz.beans.vo.req.BrandDelVo;
import com.verificer.biz.beans.vo.req.BrandFormVo;
import com.verificer.biz.beans.vo.req.BrandListQryVo;
import com.verificer.biz.beans.vo.req.BrandPageQryVo;
import com.verificer.biz.biz.entity.Brand;
import com.verificer.biz.biz.mapper.BrandMapper;
import com.verificer.biz.biz.service.BrandService;
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
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandMapper mapper;

    private BrandVo toVo(Brand e){
        if(e == null)
            return  null;
        BrandVo vo = new BrandVo();
        SBeanUtils.copyProperties2(e,vo);
        return vo;
    }

    private List<BrandVo> toVoList(List<Brand> list){

        List<BrandVo> voList = new LinkedList<>();
        for(Brand e : list){
            BrandVo vo = new BrandVo();
            SBeanUtils.copyProperties2(e,vo);
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public List<BrandVo> brandPage(BrandPageQryVo qryVo) {
        List<Brand> list = mapper.page(qryVo);

        return toVoList(list);
    }

    @Override
    public int brandCount(BrandPageQryVo qryVo) {
        return mapper.count(qryVo);
    }

    @Override
    public List<BrandVo> brandList(BrandListQryVo qryVo) {
        List<Brand> list = mapper.list(qryVo);

        return toVoList(list);
    }

    private void mCheck(Brand e){
        SCheckUtil.notEmpty(e.getName(),"Name");
        SCheckUtil.notEmpty(e.getLogoImg(),"Logo Image");
        SCheckUtil.notEmpty(e.getFirstChar(),"First Char");
        SCheckUtil.notEmpty(e.getPriority(),"Priority");
        SCheckUtil.notEmpty(e.getCreateTime(),"Create Time");
        SCheckUtil.notEmpty(e.getDelFlag(),"Delete Flag");
    }

    private void uniqueCheck(Brand e){
        Brand old = mapper.selectByNameLimit1(e.getName());
        if(e.getId() == null){
            if(old != null)
                throw new BaseException(ErrCode.BRAND_NAME_REPEAT,new Object[]{e.getName()});
        }else {
            if(old != null && !old.getId().equals(e.getId()))
                throw new BaseException(ErrCode.BRAND_NAME_REPEAT,new Object[]{e.getName()});
        }
    }

    @Override
    public void brandAdd(BrandFormVo formVo) {
        Brand e = new Brand();
        SBeanUtils.copyProperties2(formVo,e);
        e.setCreateTime(System.currentTimeMillis());
        e.setDelFlag(false);

        mCheck(e);
        uniqueCheck(e);
        mapper.insertSelective(e);
    }

    @Override
    public void brandUpd(BrandFormVo formVo) {
        SCheckUtil.notEmpty(formVo.getId(),"ID");

        Brand old = mapper.selectByPrimaryKey(formVo.getId());
        if(old == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        if(old.getDelFlag())
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        Brand e = new Brand();
        SBeanUtils.copyProperties2(formVo,e);
        e.setCreateTime(old.getCreateTime());
        e.setDelFlag(old.getDelFlag());

        mCheck(e);
        uniqueCheck(e);
        mapper.updateByPrimaryKeySelective(e);
    }

    @Override
    public void brandDel(BrandDelVo delVo) {


        SCheckUtil.notEmpty(delVo.getId(),"ID");
        Brand old = mapper.selectByPrimaryKey(delVo.getId());
        old.setDelFlag(true);
        old.setDelTime(System.currentTimeMillis());
        mapper.updateByPrimaryKeySelective(old);
    }
}
