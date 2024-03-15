package com.verificer.base.sup.pvd.service;

import com.verificer.base.sup.itf.BaseSupService;
import com.verificer.base.sup.pvd.entity.Banner;
import com.verificer.beans.*;
import com.verificer.beans.suportVo.AdvertVo;
import com.verificer.beans.suportVo.HelpVo;
import com.verificer.dubbo.BaseDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("baseSupService")
public class BaseSupServiceImpl extends BaseDubboService implements BaseSupService {
    @Autowired
    AreaService areaService;

    @Autowired
    CfgService cfgService;

    @Autowired
    BannerService bannerService;

    @Autowired
    AdvertService advertService;

    @Autowired
    HelpService helpService;

    @Override
    public List<AreaVo> areaList(AreaQryVo qryVo) {
        return areaService.areaList(qryVo);
    }

    @Override
    public AreaVo areaGetByCode(String code) {
        return areaService.areaGetByCode(code);
    }

    @Override
    public String getCfg(String code) {
        return cfgService.getCfg(code);
    }

    @Override
    public String getAesEncryptCfg(String code) {
        return cfgService.getAesEncryptCfg(code);
    }

    @Override
    public List<AdminBannerVo> bannerPage(String language, BannerQueryVo queryVo) {
        return bannerService.bannerPage(language,queryVo);
    }

    @Override
    public int bannerCount(BannerQueryVo queryVo) {
        return bannerService.bannerCount(queryVo);
    }

    @Override
    public void bannerAdd(BannerFormVo fvo) {
        bannerService.bannerAdd(fvo);
    }

    @Override
    public void bannerUpd(BannerFormVo fvo) {
        bannerService.bannerUpd(fvo);
    }

    @Override
    public void bannerDel(IdVo idVo) {
        bannerService.bannerDel(idVo);
    }

    @Override
    public List<AdvertVo> advertPage(String language, AdvertPageVo queryVo) {
        return advertService.advertPage(language,queryVo);
    }

    @Override
    public int advertCount(AdvertPageVo queryVo) {
        return advertService.advertCount(queryVo);
    }

    @Override
    public void advertAdd(AdvertFormVo fVo) {
        advertService.advertAdd(fVo);
    }

    @Override
    public void advertUpd(AdvertFormVo fVo) {
        advertService.advertUpd(fVo);
    }

    @Override
    public void advertDel(IdVo idVo) {
        advertService.advertDel(idVo);
    }

    @Override
    public List<HelpVo> helpPage(String language, HelpPageVo queryVo) {
        return helpService.helpPage(language,queryVo) ;
    }

    @Override
    public int helpCount(HelpPageVo queryVo) {
        return helpService.helpCount(queryVo);
    }

    @Override
    public void helpAdd(HelpFormVo fVo) {
        helpService.helpAdd(fVo);
    }

    @Override
    public void helpUpd(HelpFormVo fVo) {
        helpService.helpUpd(fVo);
    }

    @Override
    public void helpDel(IdVo idVo) {
        helpService.helpDel(idVo);
    }

    @Override
    public HelpVo helpDetail(IdVo idVo) {

        return helpService.helpDetail(idVo);
    }


}
