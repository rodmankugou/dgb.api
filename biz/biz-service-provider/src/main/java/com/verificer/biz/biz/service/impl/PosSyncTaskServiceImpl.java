package com.verificer.biz.biz.service.impl;

import com.mysql.cj.xdevapi.DatabaseObject;
import com.verificer.GlobalConfig;
import com.verificer.base.sup.itf.BaseSupService;
import com.verificer.biz.beans.enums.*;
import com.verificer.biz.beans.exceptions.YinBaoApiException;
import com.verificer.biz.beans.vo.req.DbgOrderFormVo2;
import com.verificer.biz.beans.vo.req.OrderDetailFormVo;
import com.verificer.biz.beans.vo.req.ShopListVo;
import com.verificer.biz.biz.entity.*;
import com.verificer.biz.biz.mapper.PosSyncTaskMapper;
import com.verificer.biz.biz.pospay.YinBaoClient;
import com.verificer.biz.biz.pospay.entity.YbGoods;
import com.verificer.biz.biz.pospay.entity.YbOrder;
import com.verificer.biz.biz.pospay.entity.YbOrderItem;
import com.verificer.biz.biz.pospay.entity.req.AddGoodsReq;
import com.verificer.biz.biz.pospay.entity.req.QryOrderReq;
import com.verificer.biz.biz.pospay.entity.req.UpdGoodsReq;
import com.verificer.biz.biz.service.*;
import com.verificer.utils.AESUtils;
import com.verificer.utils.FastJson;
import com.verificer.utils.SDateUtil;
import com.verificer.utils.SStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class PosSyncTaskServiceImpl implements PosSyncTaskService {
    @Autowired
    PosSyncTaskMapper mapper ;

    @Autowired
    BaseSupService baseSupService;

    @Autowired
    ShopService shopService;

    @Autowired
    PointerService pointerService;

    @Autowired
    UserService userService;

    @Autowired
    ShopGoodsService shopGoodsService;

    @Autowired
    DbgOrderService dbgOrderService;


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
        return 0;
    }

    private void synPosOrder(Shop shop, YbOrder order) {

        //TODO 添加订单排重逻辑，防止重复写入Pos订单
        User user = userService.selectByPosMemberId(order.getCustomerUid());


        DbgOrderFormVo2 of = new DbgOrderFormVo2();
        if(user != null)
            of.setUserId(user.getId());
        of.setStatus(DbgOrdSta.Finish.getValue());
        of.setPayType(PayType.POS.getValue());
        of.setOrderNum("DX"+ SStringUtils.generateRandomNumSequence(18));
        of.setCreateTime(SDateUtil.parse(order.getDatetime(),SDateUtil.FM_yyyy_MM_dd_HH_mm_ss).getTime());
        of.setTakeTime(SDateUtil.parse(order.getDatetime(),SDateUtil.FM_yyyy_MM_dd_HH_mm_ss).getTime());
        of.setOrderType(OrdType.POS.getValue());
        of.setRefId(shop.getId());
        of.setRefType(MerType.SHOP.getValue());

        ShopGoods shopGoods = shopGoodsService.selectByPosGoodsId(order.getItems().get(0).getProductUid());
        if(shopGoods == null)
            return;
        List<OrderDetailFormVo> detail = parseDetail(shopGoods,order.getItems());
        of.setDetails(detail);

        dbgOrderService.orderAdd(of);
    }

    private List<OrderDetailFormVo> parseDetail(ShopGoods shopGoods,List<YbOrderItem> itemList){
        List<OrderDetailFormVo> list = new LinkedList<>();
        for(YbOrderItem item : itemList){
            OrderDetailFormVo vo = new OrderDetailFormVo();
            list.add(vo);
            vo.setGoodsId(shopGoods.getGoodsId());
            vo.setSpecId(shopGoods.getSpecId());
            vo.setCount(item.getQuantity());
            vo.setPrice(item.getSellPrice());

        }

        return list;
    }


}
