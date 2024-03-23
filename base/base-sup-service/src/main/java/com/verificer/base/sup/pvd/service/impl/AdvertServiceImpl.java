package com.verificer.base.sup.pvd.service.impl;

import com.verificer.ErrCode;
import com.verificer.beans.AdvertPageVo;
import com.verificer.base.sup.pvd.entity.Advert;
import com.verificer.base.sup.pvd.mapper.AdvertMapper;
import com.verificer.base.sup.pvd.service.AdvertService;
import com.verificer.beans.AdvertFormVo;
import com.verificer.beans.IdVo;
import com.verificer.beans.suportVo.AdvertVo;
import com.verificer.common.exception.BaseException;
import com.verificer.utils.reflect.SBeanUtils;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdvertServiceImpl implements AdvertService {
    @Autowired
    AdvertMapper mapper;

    private List<AdvertVo> toVoList(List<Advert> list){
        List<AdvertVo> voList = new LinkedList<>();
        for(Advert e : list){
            voList.add(toVo(e));

        }
        return voList;
    }

    private AdvertVo toVo(Advert e){
        if(e == null)
            return null;
        AdvertVo vo = new AdvertVo();
        SBeanUtils.copyProperties2(e,vo);

        return vo;
    }

    @Override
    public List<AdvertVo> advertPage(String language, AdvertPageVo queryVo) {
        List<Advert> list = mapper.page(queryVo);
        return toVoList(list);
    }

    @Override
    public int advertCount(AdvertPageVo queryVo) {
        return mapper.count(queryVo);
    }

    private void mCheck(Advert e){
        SCheckUtil.notEmpty(e.getName(),"Name");
        SCheckUtil.notEmpty(e.getImageUri(),"Image Url");
        SCheckUtil.notEmpty(e.getHtmlUrl(),"Html Url");
    }


    @Override
    public void advertAdd(AdvertFormVo fvo) {
        Advert e = new Advert();
        SBeanUtils.copyProperties2(fvo,e);

        e.setCreateTime(System.currentTimeMillis());

        mCheck(e);
        mapper.insertSelective(e);
    }

    @Override
    public void advertUpd(AdvertFormVo fvo) {
        SCheckUtil.notEmpty(fvo.getId(),"ID");


        Advert e = mapper.selectByPrimaryKey(fvo.getId());
        if(e == null){
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        }
        SBeanUtils.copyProperties2(fvo,e);
        mCheck(e);
        mapper.updateByPrimaryKey(e);
    }

    @Override
    public void advertDel(IdVo idVo) {
        SCheckUtil.notEmpty(idVo.getId(),"ID");
        mapper.deleteByPrimaryKey(idVo.getId());
    }

    @Override
    public AdvertVo advertDetail(IdVo idVo) {
        SCheckUtil.notEmpty(idVo.getId(),"ID");
        Advert advert = mapper.selectByPrimaryKey(idVo.getId());
        return toVo(advert);
    }
}
