package com.verificer.biz.biz.service.impl;

import com.verificer.ErrCode;
import com.verificer.biz.beans.enums.PosSyncTaskType;
import com.verificer.biz.biz.entity.Goods;
import com.verificer.biz.biz.entity.MerCategory;
import com.verificer.biz.biz.entity.ShopGoods;
import com.verificer.biz.biz.entity.Spec;
import com.verificer.biz.biz.pospay.YinBaoClient;
import com.verificer.biz.biz.pospay.entity.req.AddGoodsReq;
import com.verificer.biz.biz.pospay.entity.req.UpdGoodsReq;
import com.verificer.biz.biz.service.*;
import com.verificer.common.exception.BaseException;
import com.verificer.utils.FastJson;
import com.verificer.utils.SBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class PosGoodsSyncServiceImpl implements PosGoodsSyncService {
    @Autowired
    MerCatService merCatService;

    @Autowired
    PosSyncTaskService posSyncTaskService;

    @Autowired
    SpecService specService;

    @Autowired
    ShopGoodsService shopGoodsService;

    @Override
    public void onAddGoodsToShop(String shopId, Goods goods, Spec spec) {
        MerCategory merCategory = merCatService.getByShopIdAndCatId(shopId,goods.getCategoryId());
        AddGoodsReq req = new AddGoodsReq();
        req.setGoodsId(goods.getId());
        req.setName(goods.getName());
        req.setSpecName(spec.getName());
        req.setPosCatId(merCategory.getPosCatId());
        req.setMainSpec(false);
        req.setPrice(spec.getPrice());
        req.setWeighing(goods.getPosByWeightFlag());

        posSyncTaskService.addTask(shopId,spec.getId(),PosSyncTaskType.GOODS_ADD.getValue(), FastJson.toJson(req));
    }

    //TODO 目前还不支持Spec的新增和修改
    public void onGoodsUpdate(Goods goods){
        if(goods == null)
            throw new BaseException(ErrCode.SERVER_ERROR);
        List<Spec> specList = specService.getGoodsSpecList(goods.getId());

        for(Spec spec : specList){
            UpdGoodsReq req = new UpdGoodsReq();
            req.setPrice(goods.getSaleTimeOutFlag() ? spec.getwPrice() : spec.getPrice());
            req.setName(goods.getName());
            req.setSpecName(spec.getName());

            //if的条件判断顺序不能修改
            if(goods.getDelFlag()){
                req.setEnable(-1);
            }else if(goods.getRubbishFlag() || !goods.getSaleFlag()){
                req.setEnable(0);
            }else {
                req.setEnable(1);
            }

            updateSpec(goods,spec,req);
        }

    }

    private void updateSpec(Goods goods, Spec spec, UpdGoodsReq baseReq) {
        List<ShopGoods> sgList = shopGoodsService.getBySpecId(spec.getId());
        for(ShopGoods sg : sgList){
            MerCategory merCategory = merCatService.getByShopIdAndCatId(sg.getShopId(),goods.getCategoryId());
            UpdGoodsReq req = new UpdGoodsReq();
            SBeanUtils.copyProperties2(baseReq,req);
            req.setUid(sg.getPosGoodsId());
            req.setCategoryUid(merCategory.getPosCatId());
            posSyncTaskService.addTask(sg.getShopId(),spec.getId(),PosSyncTaskType.GOODS_UPD.getValue(), FastJson.toJson(req));
        }
    }


}
