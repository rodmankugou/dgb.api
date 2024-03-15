package com.verificer.base.sup.pvd.service.impl;

import com.verificer.ErrCode;
import com.verificer.base.sup.pvd.entity.Banner;
import com.verificer.base.sup.pvd.mapper.BannerMapper;
import com.verificer.base.sup.pvd.service.BannerService;
import com.verificer.beans.*;
import com.verificer.beans.suportVo.BannerVo;
import com.verificer.common.exception.BaseException;
import com.verificer.common.exception.BizErrMsgException;
import com.verificer.utils.SBeanUtils;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.check.SCheckUtil;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 35336 on 2021/2/25.
 */
@Service("bannerService")
public class BannerServiceImpl implements BannerService {
    @Autowired
    BannerMapper bannerMapper;

    @Override
    public List<AdminBannerVo> bannerPage(String language, BannerQueryVo queryVo) {
        List<Banner> list = bannerMapper.page(queryVo);
        List<AdminBannerVo> voList = new LinkedList<>();
        for(Banner banner : list){
            AdminBannerVo vo = new AdminBannerVo();
            SBeanUtils.copyProperties2(banner,vo);
            voList.add(vo);
        }
        return voList;
    }


    private void mCheck(Banner banner){
        SCheckUtil.notEmpty(banner.getName(),"Name");
        SCheckUtil.notEmpty(banner.getImageUri(),"Image Url");
        SCheckUtil.notEmpty(banner.getHtmlUrl(),"Html Url");

    }

    @Override
    public void bannerAdd(BannerFormVo fvo) {

        Banner b = new Banner();
        SBeanUtils.copyProperties2(fvo,b);

        b.setCreateTime(System.currentTimeMillis());

        mCheck(b);
        bannerMapper.insertSelective(b);
    }

    @Override
    public void bannerUpd(BannerFormVo fvo) {
        SCheckUtil.notEmpty(fvo.getId(),"ID");


        Banner b = bannerMapper.selectByPrimaryKey(fvo.getId());
        if(b == null){
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        }
        SBeanUtils.copyProperties2(fvo,b);
        b.setUpdateTime(System.currentTimeMillis());
        mCheck(b);
        bannerMapper.updateByPrimaryKey(b);
    }

    @Override
    public List<BannerVo> bannerList(BannerQueryVo bannerQueryVo) {
        List<Banner> list = bannerMapper.page(bannerQueryVo);
        List<BannerVo> voList = new LinkedList<>();
        for(Banner banner : list){

            voList.add(toVo(banner));
        }
        return voList;
    }

    @Override
    public int bannerCount(BannerQueryVo bannerQueryVo) {
        return bannerMapper.count(bannerQueryVo);
    }

    private BannerVo toVo(Banner banner){
        if(banner == null)
            return  null;

        BannerVo vo = new BannerVo();
        SBeanUtils.copyProperties2(banner,vo);
        return vo;
    }

    @Override
    public BannerVo bannerDetail(Long id) {
        if(id == null)
            throw new BizErrMsgException("Parameter id can not be null");
        Banner banner = bannerMapper.selectByPrimaryKey(id);

        return toVo(banner);
    }

    @Override
    public void bannerDel(IdVo idVo) {
        SCheckUtil.notEmpty(idVo.getId(),"ID");
        bannerMapper.deleteByPrimaryKey(idVo.getId());
    }


}
