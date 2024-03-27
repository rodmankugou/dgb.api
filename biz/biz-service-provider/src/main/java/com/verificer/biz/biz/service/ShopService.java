package com.verificer.biz.biz.service;

import com.verificer.biz.beans.vo.ShopIdVo;
import com.verificer.biz.beans.vo.shop.ShopVo;
import com.verificer.biz.beans.vo.req.*;
import com.verificer.biz.beans.vo.shop.req.ShopPageVo;
import com.verificer.biz.biz.entity.Shop;

import java.util.List;

public interface ShopService {
    /**
     * 店铺列表（不分页）
     * @param qryVo
     * @return
     */
    List<ShopVo> shopVoList(ShopListVo qryVo);

    /**
     * 店铺列表（分页）
     * @param qryVo
     * @return
     */
    List<ShopVo> shopPage(ShopPageVo qryVo);

    ShopVo shopDetail(ShopIdVo reqVo);


    /**
     * 统计符合条件的店铺数
     * @param qryVo
     * @return
     */
    int shopCount(ShopPageVo qryVo);

    /**
     * 增加店铺
     * @param formVo
     * @return
     */
    void shopAdd(ShopFormVo formVo);

    /**
     * 修改店铺
     * @param formVo
     */
    void shopUpd(ShopFormVo formVo);

    /**
     * 删除店铺
     * @param delVo
     */
    void shopDel(ShopDelVo delVo);

    /**
     * 解冻/冻结店铺
     * @param reqVo
     */
    void shopUpdFrozenSta(ShopFrozenVo reqVo);

    Shop getById(String relId);

    /**
     * 所有店铺
     * @param shopListVo
     * @return
     */
    List<Shop> shopList();

    /**
     * 获取所有未结业的店铺
     * @return
     */
    List<Shop> getAllEffShop();
}
