package com.verificer.biz.biz.service.impl;

import com.verificer.ErrCode;
import com.verificer.biz.beans.vo.CatVo;
import com.verificer.biz.beans.vo.req.CatDelVo;
import com.verificer.biz.beans.vo.req.CatFormVo;
import com.verificer.biz.beans.vo.req.CatListQryVo;
import com.verificer.biz.beans.vo.req.CatPageQryVo;
import com.verificer.biz.biz.entity.Category;
import com.verificer.biz.biz.mapper.CategoryMapper;
import com.verificer.biz.biz.service.CatService;
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
public class CatServiceImpl implements CatService {
    @Autowired
    CategoryMapper mapper;

    private CatVo toVo(Category e){
        if(e == null)
            return  null;
        CatVo vo = new CatVo();
        SBeanUtils.copyProperties2(e,vo);
        return vo;
    }

    private List<CatVo> toVoList(List<Category> list){

        List<CatVo> voList = new LinkedList<>();
        for(Category e : list){
            CatVo vo = new CatVo();
            SBeanUtils.copyProperties2(e,vo);
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public List<CatVo> catList(CatListQryVo qryVo) {
        List<Category> list = mapper.list(qryVo);

        return toVoList(list);
    }

    @Override
    public List<CatVo> catPage(CatPageQryVo qryVo) {
        List<Category> list = mapper.page(qryVo);

        return toVoList(list);
    }

    @Override
    public int catCount(CatPageQryVo qryVo) {
        return  mapper.count(qryVo);
    }

    private void mCheck(Category e){
        SCheckUtil.notEmpty(e.getName(),"Name");
        SCheckUtil.notEmpty(e.getLogoImg(),"Logo Image");
        SCheckUtil.notEmpty(e.getShortName(),"Short Name");
        SCheckUtil.notEmpty(e.getSearchKey(),"Search Key");
        SCheckUtil.notEmpty(e.getCreateTime(),"Create Time");
        SCheckUtil.notEmpty(e.getDelFlag(),"Delete Flag");
    }

    private void uniqueCheck(Category e){
        Category old = mapper.selectByNameLimit1(e.getName());
        if(e.getId() == null){
            if(old != null)
                throw new BaseException(ErrCode.BRAND_NAME_REPEAT,new Object[]{e.getName()});
        }else {
            if(old != null && !old.getId().equals(e.getId()))
                throw new BaseException(ErrCode.BRAND_NAME_REPEAT,new Object[]{e.getName()});
        }
    }

    private String genSearchKey(String name,String shortName){
        return name+"@"+shortName;
    }

    @Override
    public void catAdd(CatFormVo formVo) {
        Category e = new Category();
        SBeanUtils.copyProperties2(formVo,e);
        e.setSearchKey(genSearchKey(formVo.getName(),formVo.getShortName()));
        e.setCreateTime(System.currentTimeMillis());
        e.setDelFlag(false);

        mCheck(e);
        uniqueCheck(e);
        mapper.insertSelective(e);
    }

    @Override
    public void catUpd(CatFormVo formVo) {
        SCheckUtil.notEmpty(formVo.getId(),"ID");

        Category old = mapper.selectByPrimaryKey(formVo.getId());

        if(old == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        if(old.getDelFlag())
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        Category e = new Category();
        SBeanUtils.copyProperties2(formVo,e);
        e.setSearchKey(genSearchKey(e.getName(),e.getShortName()));
        e.setCreateTime(old.getCreateTime());
        e.setDelFlag(old.getDelFlag());

        mCheck(e);
        uniqueCheck(e);
        mapper.updateByPrimaryKeySelective(e);
    }

    @Override
    public void catDel(CatDelVo delVo) {
        SCheckUtil.notEmpty(delVo.getId(),"ID");
        Category old = mapper.selectByPrimaryKey(delVo.getId());
        old.setDelFlag(true);
        old.setDelTime(System.currentTimeMillis());
        mapper.updateByPrimaryKeySelective(old);
    }
}
