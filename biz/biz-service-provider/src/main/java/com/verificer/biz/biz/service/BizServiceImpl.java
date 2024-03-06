package com.verificer.biz.biz.service;

import com.verificer.beans.AreaVo;
import com.verificer.biz.beans.vo.*;
import com.verificer.biz.beans.vo.req.*;
import com.verificer.dubbo.BaseDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bizService")
public class BizServiceImpl extends BaseDubboService implements BizService {
    @Autowired
    BrandService brandService;

    @Autowired
    CatService catService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    StageService stageService;

    @Autowired
    ShopService shopService;

    @Override
    public List<BrandVo> brandPage(BrandPageQryVo qryVo) {
        return brandService.brandPage(qryVo);
    }

    @Override
    public int brandCount(BrandPageQryVo qryVo) {
        return brandService.brandCount(qryVo);
    }

    @Override
    public List<BrandVo> brandList(BrandListQryVo qryVo) {
        return brandService.brandList(qryVo);
    }

    @Override
    public void brandAdd(BrandFormVo formVo) {
        brandService.brandAdd(formVo);
    }

    @Override
    public void brandUpd(BrandFormVo formVo) {
        brandService.brandUpd(formVo);
    }

    @Override
    public void brandDel(BrandDelVo delVo) {
        brandService.brandDel(delVo);
    }

    @Override
    public List<CatVo> catList(CatListQryVo qryVo) {
        return catService.catList(qryVo);
    }

    @Override
    public List<CatVo> catPage(CatPageQryVo qryVo) {
        return catService.catPage(qryVo);
    }

    @Override
    public int catCount(CatPageQryVo qryVo) {
        return catService.catCount(qryVo);
    }

    @Override
    public void catAdd(CatFormVo formVo) {
        catService.catAdd(formVo);
    }

    @Override
    public void catUpd(CatFormVo formVo) {
        catService.catUpd(formVo);
    }

    @Override
    public void catDel(CatDelVo delVo) {
        catService.catDel(delVo);
    }

    @Override
    public List<GoodsVo> goodsPage(GoodsQryVo qryVo) {
        return goodsService.goodsPage(qryVo);
    }

    @Override
    public int goodsCount(GoodsQryVo qryVo) {
        return goodsService.goodsCount(qryVo);
    }

    @Override
    public void goodsAdd(GoodsFormVo formVo) {
        goodsService.goodsAdd(formVo);
    }

    @Override
    public void goodsUpd(GoodsFormVo formVo) {
        goodsService.goodsUpd(formVo);
    }

    @Override
    public void goodsRubbish(GoodsRubbishVo reqVo) {
        goodsService.goodsRubbish(reqVo);
    }

    @Override
    public void goodsRecover(GoodsRecoverVo reqVo) {
        goodsService.goodsRecover(reqVo);
    }

    @Override
    public void goodsDel(GoodsDelVo reqVo) {
        goodsService.goodsDel(reqVo);
    }

    @Override
    public void goodsUpdSaleFlag(GoodsUpdSaleFlagVo reqVo) {
        goodsService.goodsUpdSaleFlag(reqVo);
    }

    @Override
    public List<StageVo> stagePage(StagePageVo qryVo) {
        return stageService.stagePage(qryVo);
    }

    @Override
    public int stageCount(StagePageVo qryVo) {
        return stageService.stageCount(qryVo);
    }

    @Override
    public void stageAdd(StageFormVo formVo) {
        stageService.stageAdd(formVo);
    }

    @Override
    public void stageUpd(StageFormVo formVo) {
        stageService.stageUpd(formVo);
    }

    @Override
    public List<StageVo> stageList() {
        return stageService.stageList();
    }

    @Override
    public List<ShopVo> shopList(ShopListVo qryVo) {
        return shopService.shopList(qryVo);
    }

    @Override
    public List<ShopVo> shopPage(ShopPageVo qryVo) {
        return shopService.shopPage(qryVo);
    }

    @Override
    public int shopCount(ShopPageVo qryVo) {
        return shopService.shopCount(qryVo);
    }

    @Override
    public void shopAdd(ShopFormVo formVo) {
        shopAdd(formVo);
    }

    @Override
    public void shopUpd(ShopFormVo formVo) {
        shopUpd(formVo);
    }

    @Override
    public void shopDel(ShopDelVo delVo) {
        shopDel(delVo);
    }

    @Override
    public void shopUpdFrozenSta(ShopFrozenVo reqVo) {
        shopUpdFrozenSta(reqVo);
    }
}
