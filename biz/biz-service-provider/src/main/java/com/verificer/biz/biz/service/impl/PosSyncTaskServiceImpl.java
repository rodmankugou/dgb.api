package com.verificer.biz.biz.service.impl;

import com.verificer.GlobalConfig;
import com.verificer.base.sup.itf.BaseSupService;
import com.verificer.base.sup.itf.CfgCodes;
import com.verificer.biz.beans.enums.*;
import com.verificer.biz.beans.vo.order.YbOrdFormVo;
import com.verificer.biz.beans.vo.order.YbOrdItemVo;
import com.verificer.biz.beans.vo.req.OrdItemFormVo;
import com.verificer.biz.biz.entity.*;
import com.verificer.biz.biz.mapper.PosSyncTaskMapper;
import com.verificer.biz.biz.pospay.YinBaoClient;
import com.verificer.biz.biz.pospay.entity.*;
import com.verificer.biz.biz.pospay.entity.req.*;
import com.verificer.biz.biz.service.*;
import com.verificer.biz.biz.service.core.order.OrdCoreService;
import com.verificer.biz.biz.service.core.user.UserCoreService;
import com.verificer.utils.AESUtils;
import com.verificer.utils.FastJson;
import com.verificer.utils.SDateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class PosSyncTaskServiceImpl implements PosSyncTaskService {
    private static final Logger logger = LoggerFactory.getLogger(PosSyncTaskServiceImpl.class);

    @Autowired
    PosSyncTaskMapper mapper ;

    @Autowired
    BaseSupService baseSupService;

    @Autowired
    ShopService shopService;

    @Autowired
    PointerService pointerService;

    @Autowired
    UserCoreService userCoreService;

    @Autowired
    ShopGoodsService shopGoodsService;

    @Autowired
    DbgOrderService dbgOrderService;

    @Autowired
    OrdCoreService ordCoreService;

    @Autowired



    private static final String VERSION = "V1.0";

    @Override
    public void addTask(String shopId,Long relId, int type, String reqJson) {
        PosSyncTask t = new PosSyncTask();
        t.setVersion(VERSION);
        t.setType(type);
        t.setRelId(relId);
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
    public int handlePosGoodsSyncTask() {
        PosSyncTask task = mapper.selectByStatusLimit1(PosSyncTaskStatus.INIT.getValue());
        if(task == null)
            return 0;

        try {
            Shop shop = shopService.getById(task.getShopId());
            String apiSecret = AESUtils.decrypt(GlobalConfig.AES_SEED,shop.getPosAppSecret());
            if(PosSyncTaskType.GOODS_UPD.getValue() == task.getType()){
                UpdGoodsReq req = FastJson.fromJson(task.getReqData(),UpdGoodsReq.class);
                if(req.getUid() == null){
                    ShopGoods shopGoods = shopGoodsService.getByShopIdAndSpecId(shop.getId(),task.getRelId());
                    req.setUid(shopGoods.getPosGoodsId());
                }
                YinBaoClient.goodsUpd(shop.getPosBaseUrl(),shop.getPosAppId(),apiSecret,req);
                task.setStatus(PosSyncTaskStatus.SUC.getValue());
            }else if((PosSyncTaskType.GOODS_ADD.getValue() == task.getType())){
                AddGoodsReq req = FastJson.fromJson(task.getReqData(),AddGoodsReq.class);
                YbGoods goods = YinBaoClient.goodsAdd(shop.getPosBaseUrl(),shop.getPosAppId(),apiSecret,req);
                shopGoodsService.bindPosGoodsId(shop.getId(),task.getRelId(),goods.getUid());
                task.setStatus(PosSyncTaskStatus.SUC.getValue());
            }else if(PosSyncTaskType.MEMBER_ADD.getValue() == task.getType()){
                AddMemberReq req = FastJson.fromJson(task.getReqData(),AddMemberReq.class);
                YbMember member = YinBaoClient.addMember(
                        baseSupService.getCfg(CfgCodes.YB_BASE_RUL),
                        baseSupService.getCfg(CfgCodes.YB_APP_ID),
                        baseSupService.getAesEncryptCfg(CfgCodes.YB_APP_SECRET),
                        req);
                userCoreService.bindPosMemberId(task.getRelId(),member.getCustomerId());
            }else if(PosSyncTaskType.MEMBER_UPD.getValue() == task.getType()){
                UpdMemberReq req = FastJson.fromJson(task.getReqData(),UpdMemberReq.class);
                YinBaoClient.updMember(
                        baseSupService.getCfg(CfgCodes.YB_BASE_RUL),
                        baseSupService.getCfg(CfgCodes.YB_APP_ID),
                        baseSupService.getAesEncryptCfg(CfgCodes.YB_APP_SECRET),
                        req);
            }
            mapper.updateByPrimaryKey(task);
            return 1;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    @Override
    public int handlePosOrderSyncTask(String shopId)  throws Exception {
        Shop shop = shopService.getById(shopId);
        Pointer pointer = pointerService.getAndLock("POS_SHOP_ORDER_SYNC_"+shop.getId());
        Long sTime = Long.parseLong(pointer.getPointer());
        sTime = sTime > 0? sTime -1 : sTime;
        Long eTime = System.currentTimeMillis() - 5 * SDateUtil.MS_PER_MINUTE;
        if(sTime > eTime)
            return 0;
        //银豹的请求参数限制，两个时间的间隔不能大于1天
        eTime = (eTime - sTime) > SDateUtil.MS_PER_DAY ? sTime + SDateUtil.MS_PER_DAY : eTime;

        QryOrderReq req = new QryOrderReq();
        req.setStartTime(sTime);
        req.setEndTime(eTime);
        String appSecret = AESUtils.decrypt(GlobalConfig.AES_SEED,shop.getPosAppSecret());
        List<YbOrder> orders = YinBaoClient.qryOrder(shop.getPosBaseUrl(),shop.getPosAppId(),appSecret,req);

        for(YbOrder order : orders){
            synPosOrder(shop,order);
        }
        pointer.setPointer(eTime.toString());
        pointer.setUpdTime(System.currentTimeMillis());
        pointerService.upd(pointer);
        return 0;
    }

    private void synPosOrder(Shop shop, YbOrder order) {

        //TODO 添加订单排重逻辑，防止重复写入Pos订单
        User user = userCoreService.selectByPosMemberId(order.getCustomerUid());


        YbOrdFormVo of = new YbOrdFormVo();
        if(user != null)
            of.setUserId(user.getId());
        of.setOrderType(OrdType.POS.getValue());
        of.setRelId(shop.getId());
        of.setRelType(MerType.SHOP.getValue());
        of.setPosOrdId(order.getUid());
        of.setPosCashierId(order.getCashierUid());
        of.setPosOrdTime(SDateUtil.parse(order.getDatetime(),SDateUtil.FM_yyyy_MM_dd_HH_mm_ss).getTime());
        of.setPosMemberId(order.getCashierUid());


        ShopGoods shopGoods = shopGoodsService.selectByPosGoodsId(order.getItems().get(0).getProductUid());
        if(shopGoods == null){
            logger.error("无法识别订单中的商品，Pos订单信息:"+FastJson.toJson(order));
            return;

        }
        List<OrdItemFormVo> detail = parseDetail(shopGoods,order.getItems());
        of.setDetails(detail);

        ordCoreService.create(of);
    }

    private List<OrdItemFormVo> parseDetail(ShopGoods shopGoods, List<YbOrderItem> itemList){
        List<OrdItemFormVo> list = new LinkedList<>();
        for(YbOrderItem item : itemList){
            YbOrdItemVo vo = new YbOrdItemVo();
            list.add(vo);
            vo.setGoodsId(shopGoods.getGoodsId());
            vo.setSpecId(shopGoods.getSpecId());
            vo.setCount(item.getQuantity());
            vo.setPrice(item.getSellPrice());
            vo.setRealPrice(item.getTotalAmount().divide(item.getQuantity()));
            vo.setAmount(item.getTotalAmount());

        }

        return list;
    }


}
