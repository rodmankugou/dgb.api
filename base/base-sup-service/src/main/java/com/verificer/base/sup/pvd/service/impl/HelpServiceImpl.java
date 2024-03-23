package com.verificer.base.sup.pvd.service.impl;

import com.verificer.ErrCode;
import com.verificer.base.sup.pvd.entity.Help;
import com.verificer.base.sup.pvd.mapper.HelpMapper;
import com.verificer.base.sup.pvd.service.HelpService;
import com.verificer.beans.HelpFormVo;
import com.verificer.beans.HelpPageVo;
import com.verificer.beans.IdVo;
import com.verificer.beans.suportVo.HelpVo;
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
public class HelpServiceImpl implements HelpService {
    @Autowired
    HelpMapper mapper;
    
    
    private List<HelpVo> toVoList(List<Help> list){
        List<HelpVo> voList = new LinkedList<>();
        for(Help e : list){
            voList.add(toVo(e));

        }
        return voList;
    }

    private HelpVo toVo(Help e){
        if(e == null)
            return null;
        HelpVo vo = new HelpVo();
        SBeanUtils.copyProperties2(e,vo);

        return vo;
    }

    @Override
    public List<HelpVo> helpPage(String language, HelpPageVo queryVo) {
        List<Help> list = mapper.page(queryVo);
        return toVoList(list);
    }

    @Override
    public int helpCount(HelpPageVo queryVo) {
        return mapper.count(queryVo);
    }

    private void mCheck(Help e){
        SCheckUtil.notEmpty(e.getTitle(),"Title");
        SCheckUtil.notEmpty(e.getContent(),"Content");
    }


    @Override
    public void helpAdd(HelpFormVo fvo) {
        Help e = new Help();
        SBeanUtils.copyProperties2(fvo,e);

        e.setCreateTime(System.currentTimeMillis());

        mCheck(e);
        mapper.insertSelective(e);
    }

    @Override
    public void helpUpd(HelpFormVo fvo) {
        SCheckUtil.notEmpty(fvo.getId(),"ID");


        Help e = mapper.selectByPrimaryKey(fvo.getId());
        if(e == null){
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        }
        SBeanUtils.copyProperties2(fvo,e);
        mCheck(e);
        mapper.updateByPrimaryKey(e);
    }

    @Override
    public void helpDel(IdVo idVo) {
        SCheckUtil.notEmpty(idVo.getId(),"ID");
        mapper.deleteByPrimaryKey(idVo.getId());
    }

    @Override
    public HelpVo helpDetail(IdVo idVo) {
        SCheckUtil.notEmpty(idVo.getId(),"ID");
        Help help = mapper.selectByPrimaryKey(idVo.getId());
        return toVo(help);
    }
}
