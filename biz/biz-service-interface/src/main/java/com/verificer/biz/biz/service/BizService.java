package com.verificer.biz.biz.service;

import com.verificer.beans.AreaVo;
import com.verificer.beans.ArtworkSubmitVo;
import com.verificer.biz.beans.vo.*;
import com.verificer.biz.beans.vo.req.*;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.ApiOperation;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 35336 on 2021/2/28.
 */
public interface BizService {




    /**
     * 分页获取品牌
     * @param qryVo
     * @return
     */
    List<BrandVo> brandPage(BrandPageQryVo qryVo);

    /**
     * 统计符合条件的品牌数
     * @param qryVo
     * @return
     */
    int brandCount(BrandPageQryVo qryVo);

    /**
     * 品牌列表
     * @param qryVo
     * @return
     */
    List<BrandVo> brandList(BrandListQryVo qryVo);

    /**
     * 新增Brand
     * @param formVo
     */
    void brandAdd(BrandFormVo formVo);

    /**
     * 修改Brand
     * @param formVo
     */
    void brandUpd(BrandFormVo formVo);

    /**
     * 删除Brand
     * @param delVo
     */
    void brandDel(BrandDelVo delVo);

    /**
     * 分类列表（不分页）
     * @param qryVo
     * @return
     */
    List<CatVo> catList(CatListQryVo qryVo);

    /**
     * 分页获取品牌
     * @param qryVo
     * @return
     */
    List<CatVo> catPage(CatPageQryVo qryVo);

    /**
     * 统计符合条件的品牌数
     * @param qryVo
     * @return
     */
    int catCount(CatPageQryVo qryVo);


    /**
     * 新增Brand
     * @param formVo
     */
    void catAdd(CatFormVo formVo);

    /**
     * 修改Brand
     * @param formVo
     */
    void catUpd(CatFormVo formVo);

    /**
     * 删除Brand
     * @param delVo
     */
    void catDel(CatDelVo delVo);

    /**
     * 分页查询商品
     * @param qryVo
     * @return
     */
    List<GoodsVo> goodsPage(GoodsQryVo qryVo);

    /**
     * 统计符合查询条件的商品数
     * @param qryVo
     * @return
     */
    int goodsCount(GoodsQryVo qryVo);

    /**
     * 新增商品
     * @param formVo
     */
    void goodsAdd(GoodsFormVo formVo);

    /**
     * 修改商品
     * @param formVo
     */
    void goodsUpd(GoodsFormVo formVo);

    /**
     * 删除（放入回收站），商品列表的删除功能使用该接口，商品被删除后将被放入回收站
     * @param reqVo
     */
    void goodsRubbish(GoodsRubbishVo reqVo);

    /**
     * 恢复，将回收站中的商品恢复
     * @param reqVo
     */
    void goodsRecover(GoodsRecoverVo reqVo);

    /**
     * 删除，删除回收站中的商品，回收站中的商品被删除后将不可恢复
     * @param reqVo
     */
    void goodsDel(GoodsDelVo reqVo);


    /**
     * 上架/下架
     * @param reqVo
     */
    void goodsUpdSaleFlag(GoodsUpdSaleFlagVo reqVo);

    /**
     * 仓库列表（分页）
     * @param qryVo
     * @return
     */
    List<StageVo> stagePage(StagePageVo qryVo);

    /**
     * 统计符合条件的仓库数
     * @param qryVo
     * @return
     */
    int stageCount(StagePageVo qryVo);

    /**
     * 新增仓库
     * @param formVo
     */
    void stageAdd(StageFormVo formVo);

    /**
     * 修改仓库
     * @param formVo
     */
    void stageUpd(StageFormVo formVo);

    /**
     * 仓库列表
     * @return
     */
    List<StageVo> stageList();

    /**
     * 店铺列表（不分页）
     * @param qryVo
     * @return
     */
    List<ShopVo> shopList(ShopListVo qryVo);

    /**
     * 店铺列表（分页）
     * @param qryVo
     * @return
     */
    List<ShopVo> shopPage(ShopPageVo qryVo);

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

    /**
     * 批量调货
     * @param formVo
     */
    void adjustBatch(AdjustBatchVo formVo);

    /**
     * 调货
     * @param delVo
     */
    void adjust(AdjustFormVo formVo);

    /**
     * 调货列表（分页）
     * @param qryVo
     * @return
     */
    List<AdjustVo> adjustPage(AdjustPageVo qryVo);

    /**
     * 统计符合条件的调货记录数
     * @param qryVo
     * @return
     */
    int adjustCount(AdjustPageVo qryVo);

    /**
     * 新增订单
     * @param formVo
     * @return
     */
    Long orderAdd(DbgOrderFormVo2 formVo);

    /**
     * 订单列表
     * @param qryVo
     * @return
     */
    List<DbgOrderVo> orderPage(OrderPageVo qryVo);

    /**
     * 统计符合条件的订单数
     * @param qryVo
     * @return
     */
    int orderCount(OrderPageVo qryVo);

    /**
     * 订单详情
     * @return
     */
    DbgOrderVo orderDetail(Long id);

    /**
     * 同步银豹数据，临时
     * @param
     */
    void ybSync();
}

