package com.verificer.base.sup.itf;

import java.util.List;

import com.verificer.beans.AdvertPageVo;
import com.verificer.beans.*;
import com.verificer.beans.suportVo.AdvertVo;
import com.verificer.beans.suportVo.HelpVo;

public interface BaseSupService {
    /**
     * 区域列表
     * @param qryVo
     * @return
     */
    List<AreaVo> areaList(AreaQryVo qryVo);

    /**
     * 根据code获取Area
     * @param code
     * @return
     */
    AreaVo areaGetByCode(String code);

    /**
     * 获取配置
     * @param code
     * @return
     */
    String getCfg(String code);

    /**
     * 修改配置
     * @param code
     * @param val
     */
    void updCfg(String code, String val);

    /**
     * 获取加密的配置
     * @param code
     * @return 返回解密后的配置值
     */
    String getAesEncryptCfg(String code);

    List<AdminBannerVo> bannerPage(String language, BannerQueryVo queryVo);

    int bannerCount(BannerQueryVo queryVo);

    void bannerAdd(BannerFormVo fvo);

    void bannerUpd(BannerFormVo fvo);

    void bannerDel(IdVo idVo);

    List<AdvertVo> advertPage(String language, AdvertPageVo queryVo);

    int advertCount(AdvertPageVo queryVo);

    void advertAdd(AdvertFormVo fVo);

    void advertUpd(AdvertFormVo fVo);

    void advertDel(IdVo idVo);

    List<HelpVo> helpPage(String language, HelpPageVo queryVo);

    int helpCount(HelpPageVo queryVo);

    void helpAdd(HelpFormVo fVo);

    void helpUpd(HelpFormVo fVo);

    void helpDel(IdVo idVo);

    HelpVo helpDetail(IdVo idVo);


    AdvertVo advertDetail(IdVo idVo);

    AdminBannerVo bannerDetail(IdVo idVo);

}
