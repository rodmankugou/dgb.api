package com.verificer.biz.biz.service.impl;

import com.amazonaws.services.rekognition.model.Face;
import com.verificer.GlobalConfig;
import com.verificer.base.sup.itf.BaseSupService;
import com.verificer.base.sup.itf.CfgCodes;
import com.verificer.biz.beans.enums.PosSyncTaskStatus;
import com.verificer.biz.beans.enums.PosSyncTaskType;
import com.verificer.biz.beans.exceptions.YinBaoApiException;
import com.verificer.biz.biz.entity.PosSyncTask;
import com.verificer.biz.biz.entity.Shop;
import com.verificer.biz.biz.mapper.PosSyncTaskMapper;
import com.verificer.biz.biz.pospay.YinBaoClient;
import com.verificer.biz.biz.pospay.entity.req.AddGoodsReq;
import com.verificer.biz.biz.pospay.entity.req.UpdGoodsReq;
import com.verificer.biz.biz.service.PosSyncTaskService;
import com.verificer.biz.biz.service.ShopService;
import com.verificer.utils.AESUtils;
import com.verificer.utils.FastJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

@Service
@Transactional(rollbackFor = Exception.class)
public class PosSyncTaskServiceImpl implements PosSyncTaskService {
    @Autowired
    PosSyncTaskMapper mapper ;

    @Autowired
    BaseSupService baseSupService;

    @Autowired
    ShopService shopService;

    private static final String VERSION = "V1.0";

    @Override
    public void addTask(String shopId, int type, String reqJson) {
        PosSyncTask t = new PosSyncTask();
        t.setVersion(VERSION);
        t.setType(type);
        t.setShopId(shopId);
        t.setReqData(reqJson);
        t.setStatus(PosSyncTaskStatus.INIT.getValue());
        t.setRetryCount(0);
        t.setCreateTime(System.currentTimeMillis());
        t.setNextRetryTime(null);
        t.setFinishTime(null);
        mapper.insertSelective(t);


    }

    @Override
    public int handleSyncTask() {
        PosSyncTask task = mapper.selectByStatusLimit1(PosSyncTaskStatus.INIT.getValue());
        if(task == null)
            return 0;

        try {
            Shop shop = shopService.getById(task.getShopId());
            String apiSecret = AESUtils.decrypt(GlobalConfig.AES_SEED,shop.getPosAppSecret());
            if(PosSyncTaskType.GOODS_UPD.getValue() == task.getType()){
                UpdGoodsReq req = FastJson.fromJson(task.getReqData(),UpdGoodsReq.class);
                YinBaoClient.goodsUpd(shop.getPosBaseUrl(),shop.getPosAppId(),apiSecret,req);
                task.setStatus(PosSyncTaskStatus.SUC.getValue());
            }else if((PosSyncTaskType.GOODS_ADD.getValue() == task.getType())){
                AddGoodsReq req = FastJson.fromJson(task.getReqData(),AddGoodsReq.class);
                YinBaoClient.goodsAdd(shop.getPosBaseUrl(),shop.getPosAppId(),apiSecret,req);
                task.setStatus(PosSyncTaskStatus.SUC.getValue());
            }
            mapper.updateByPrimaryKey(task);
            return 1;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
}
