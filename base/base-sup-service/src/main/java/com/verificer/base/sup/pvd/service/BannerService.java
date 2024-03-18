package com.verificer.base.sup.pvd.service;

import com.verificer.beans.AdminBannerVo;
import com.verificer.beans.BannerFormVo;
import com.verificer.beans.BannerQueryVo;
import com.verificer.beans.IdVo;
import com.verificer.beans.suportVo.BannerVo;

import java.util.List;

/**
 * Created by 35336 on 2021/2/25.
 */
public interface BannerService
{
    /**
     * 分页获取banner列表
     * @param language
     * @param queryVo
     * @return
     */
    List<AdminBannerVo> bannerPage(String language, BannerQueryVo queryVo);

    int bannerCount(BannerQueryVo queryVo);



    void bannerAdd(BannerFormVo vvo);

    void bannerUpd(BannerFormVo fvo);


    /**
     * 获取banner列表，Api端接口
     * @param bannerQueryVo
     * @return
     */
    List<BannerVo> bannerList(BannerQueryVo bannerQueryVo);



    /**
     * banner详情
     * @param id
     * @return
     */
    BannerVo bannerDetail(Long id);

    /**
     * 删除banner
     * @param idVo
     */
    void bannerDel(IdVo idVo);

    AdminBannerVo bannerDetail(IdVo idVo);
}
