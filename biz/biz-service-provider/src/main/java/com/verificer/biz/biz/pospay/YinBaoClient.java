package com.verificer.biz.biz.pospay;

import com.verificer.biz.beans.exceptions.YinBaoApiException;
import com.verificer.biz.biz.pospay.entity.*;
import com.verificer.biz.biz.pospay.entity.req.*;
import com.verificer.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class YinBaoClient {
    private static final Logger logger = LoggerFactory.getLogger(YinBaoClient.class);


    static String CAT_PAGE = "pospal-api2/openapi/v1/productOpenApi/queryProductCategoryPages";
    static String CAT_ADD = "pospal-api2/openapi/v1/productOpenApi/addCategory";
    static String CAT_UPD = "pospal-api2/openapi/v1/productOpenApi/updateCategory";

    static String GOODS_ADD = "pospal-api2/openapi/v1/productOpenApi/addProductInfo";
    static String GOODS_UPD = "pospal-api2/openapi/v1/productOpenApi/updateProductInfo";
    static String GOODS_QRY = "pospal-api2/openapi/v1/productOpenApi/queryProductPages";

    static String QRY_ORDER = "pospal-api2/openapi/v1/ticketOpenApi/queryTicketPages";

    static String MEMBER_ADD = "pospal-api2/openapi/v1/customerOpenApi/add";

//    static {
//        try {
//            List<String> lines = FileUtils.readLines(new File("/mnt/dbg/secret/yinbao.txt"));
//            appKey = lines.get(0).trim();
//        } catch (Exception e) {
//            System.out.println("加载银豹appKey失败,错误信息："+e);
//            System.exit(-1);
//        }
//    }

    public static YinBaoResp post(String baseUrl,String appId,String appSecret,String path,Object param){
        String reqJson = "{}";
        if(param != null ){
            if(param instanceof String)
                reqJson = (String) param;
            else
                reqJson = FastJson.toJson(param);
        }

        String respStr = HttpsUtils.postJson(baseUrl+path,reqJson,buildHeaders(appSecret,reqJson));
        YinBaoResp resp = FastJson.fromJson(respStr,YinBaoResp.class);
        return  resp;
    }

    private static Map<String,String> buildHeaders(String appSecret,String jsonBody){
        Map<String,String> map = new HashMap<>();
        map.put("User-Agent","openApi");
        map.put("Content-Type","application/json; charset=utf-8");
        map.put("accept-encoding","gzip,deflate");
        map.put("time-stamp",System.currentTimeMillis()+"");
        map.put("data-signature",sign(jsonBody,appSecret));


        return map;

    }

    public static String sign(String jsonStr,String ak){
        return encryptToMd5String(jsonStr,ak);
    }

    static String  encryptToMd5String(String content,String appKey)
    {
        return encryptToMd5String(
                appKey.trim()+content.trim());
    }

    static String encryptToMd5String(String content) {
        String md5String = null;
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            md.update(content.getBytes("UTF-8"));
            md5String = parseByte2HexString(md.digest());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        return md5String;
    }

    static String parseByte2HexString(byte buf[])
    {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < buf.length; i++)
        {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {hex = '0' + hex;}
            stringBuffer.append(hex.toUpperCase());
        }
        return stringBuffer.toString();
    }

    public static YbCat catUpd(String baseUrl,String appId,String appSecret,UpdCatReq req) throws YinBaoApiException {
        Map<String,Object> rp = new HashMap<>();
        rp.put("appId",appId);
        rp.put("uid",req.getUid());
        rp.put("name",req.getName());
        setParamsIfNotNotNull(rp,"parentId",req.getParentId());
        YinBaoResp resp = post(baseUrl,appId,appSecret,CAT_UPD,rp);
        if(!resp.isSuc())
            throw new YinBaoApiException(resp.getStatus(),resp.getMessages());
        String data = resp.getData();
        System.out.println(data);
        YbCat cat = FastJson.fromJson(data,YbCat.class);
        return cat;
    }

    public static YbCat catAdd(String baseUrl,String appId,String appSecret,String catName) throws YinBaoApiException {
        Map<String,String> rp = new HashMap<>();
        rp.put("appId",appId);
        rp.put("name",catName);
        YinBaoResp resp = post(baseUrl,appId,appSecret,CAT_ADD,rp);
        if(!resp.isSuc())
            throw new YinBaoApiException(resp.getStatus(),resp.getMessages());
        String data = resp.getData();
        System.out.println(data);
        YbCat cat = FastJson.fromJson(data,YbCat.class);
        return cat;
    }

    public static YbGoods goodsAdd(String baseUrl, String appId, String appSecret, AddGoodsReq req) throws YinBaoApiException {
        String price  = req.getPrice().setScale(2).toPlainString();
        Map<String,Object> rp = new HashMap<>();
        Map<String,Object> pInfo = new HashMap<>();
        Map<String,Object> cate = new HashMap<>();
        rp.put("appId",appId);
        rp.put("productInfo",pInfo);
        rp.put("cate",cate);

        pInfo.put("categoryUid",req.getPosCatId());
        pInfo.put("name",req.getName());
        pInfo.put("barcode", UuidUtils.newUuid());
        pInfo.put("buyPrice", price);
        pInfo.put("sellPrice", price);
        pInfo.put("stock",0);
        pInfo.put("minStock",0);
        pInfo.put("maxStock",0);
        pInfo.put("noStock",1);
        pInfo.put("customerPrice", price);
        pInfo.put("isCustomerDiscount", 1);
        pInfo.put("enable",  1 );
        pInfo.put("attribute5", req.getGoodsId().toString());
        pInfo.put("attribute6", req.getSpecName());
        pInfo.put("attribute7", req.getMainSpec() ? 1 : 0);
        cate.put("isWeighing",req.getWeighing() ?  1 : 0);

        YinBaoResp resp = post(baseUrl,appId,appSecret,GOODS_ADD,rp);
        if(!resp.isSuc())
            throw new YinBaoApiException(resp.getStatus(),resp.getMessages());
        String data = resp.getData();
        System.out.println(data);
        YbGoods goods = FastJson.fromJson(data,YbGoods.class);
        return goods;
    }

    public static void goodsDel(String baseUrl, String appId, String appSecret,Long uid) throws YinBaoApiException {
        UpdGoodsReq req = UpdGoodsReq.buildDelReq(uid);
        goodsUpd(baseUrl,appId,appSecret,req);
    }

    public static void goodsDisable(String baseUrl, String appId, String appSecret,Long uid) throws YinBaoApiException {
        UpdGoodsReq req = UpdGoodsReq.buildDisableReq(uid);
        goodsUpd(baseUrl,appId,appSecret,req);
    }

    public static void goodsEnable(String baseUrl, String appId, String appSecret,Long uid) throws YinBaoApiException {
        UpdGoodsReq req = UpdGoodsReq.buildEnableReq(uid);
        goodsUpd(baseUrl,appId,appSecret,req);
    }

    private static void setParamsIfNotNotNull(Map<String,Object> map,String name,Object val){
        if(val != null)
            map.put(name,val);
    }

    public static void goodsUpd(String baseUrl, String appId, String appSecret, UpdGoodsReq req) throws YinBaoApiException {
        String price  = null;
        if(req.getPrice() != null)
            req.getPrice().setScale(2).toPlainString();
        Map<String,Object> rp = new HashMap<>();
        Map<String,Object> pInfo = new HashMap<>();
        rp.put("appId",appId);
        rp.put("productInfo",pInfo);
        pInfo.put("uid",req.getUid());

        setParamsIfNotNotNull(pInfo,"categoryUid",req.getCategoryUid());
        setParamsIfNotNotNull(pInfo,"name",req.getName());
        setParamsIfNotNotNull(pInfo,"buyPrice", price);
        setParamsIfNotNotNull(pInfo,"sellPrice", price);
        setParamsIfNotNotNull(pInfo,"customerPrice", price);
        setParamsIfNotNotNull(pInfo,"enable",  req.getEnable());
        setParamsIfNotNotNull(pInfo,"attribute6", req.getSpecName());
        if( req.getMainSpec() != null)
            setParamsIfNotNotNull(pInfo,"attribute7",req.getMainSpec()  ? 1 : 0);

        YinBaoResp resp = post(baseUrl,appId,appSecret,GOODS_UPD,rp);
        if(!resp.isSuc())
            throw new YinBaoApiException(resp.getStatus(),resp.getMessages());
        System.out.println("处理成功");
    }

    public static List<YbOrder> qryOrder(String baseUrl, String appId, String appSecret, QryOrderReq req) throws YinBaoApiException {
        List<YbOrder> orders = new LinkedList<>();
        YbPostBackParameter pbp = null;
        while (true){
            req.setPostBackParameter(pbp);
            YbQryOrdResp resp = qryOrder0(baseUrl,appId,appSecret,req);
            orders.addAll(resp.getResult());

            if(resp.getResult().size() < resp.getPageSize()){
                return orders;
            }else {
                pbp = resp.getPostBackParameter();
                if(pbp == null)
                    throw new RuntimeException("银豹的查询订单接口与文档不兼容");
            }
        }
    }

    public static YbQryOrdResp qryOrder0(String baseUrl, String appId, String appSecret, QryOrderReq req) throws YinBaoApiException {
        Map<String,Object> rp = new HashMap<>();
        rp.put("appId",appId);
        //endTime - startTime<=1天
        rp.put("startTime",SDateUtil.format(req.getStartTime(),SDateUtil.FM_yyyy_MM_dd_HH_mm_ss));
        rp.put("endTime",SDateUtil.format(req.getEndTime(),SDateUtil.FM_yyyy_MM_dd_HH_mm_ss));
        if(req.getPostBackParameter() != null)
            rp.put("postBackParameter",req.getPostBackParameter());
        YinBaoResp resp = post(baseUrl,appId,appSecret,QRY_ORDER,rp);
        if(!resp.isSuc())
            throw new YinBaoApiException(resp.getStatus(),resp.getMessages());
        String data = resp.getData();
        System.out.println(data);
        YbQryOrdResp qr = FastJson.fromJson(data,YbQryOrdResp.class);
        return qr;
    }

    public static List<YbGoods> qryAllGoods(String baseUrl, String appId, String appSecret) throws YinBaoApiException {
        List<YbGoods> list = new LinkedList<YbGoods>();
        QryGoodsReq req = new QryGoodsReq();
        YbPostBackParameter pbp = null;
        while (true){
            req.setPostBackParameter(pbp);
            YbQryGoodsResp resp = qryGoods(baseUrl,appId,appSecret,req);
            list.addAll(resp.getResult());

            if(resp.getResult().size() < resp.getPageSize()){
                return list;
            }else {
                pbp = resp.getPostBackParameter();
                if(pbp == null)
                    throw new RuntimeException("银豹的查询订单接口与文档不兼容");
            }
        }
    }

    public static YbQryGoodsResp qryGoods(String baseUrl, String appId, String appSecret, QryGoodsReq req) throws YinBaoApiException {
        Map<String,Object> rp = new HashMap<>();
        rp.put("appId",appId);
        if(req.getPostBackParameter() != null)
            rp.put("postBackParameter",req.getPostBackParameter());
        rp.put("needAllIncludeDel",0);
        YinBaoResp resp = post(baseUrl,appId,appSecret,GOODS_QRY,rp);
        if(!resp.isSuc())
            throw new YinBaoApiException(resp.getStatus(),resp.getMessages());
        String data = resp.getData();
        System.out.println(data);
        YbQryGoodsResp qr = FastJson.fromJson(data,YbQryGoodsResp.class);
        return qr;
    }

    public static YinBaoResp catPage(String baseUrl,String appId,String appSecret) throws YinBaoApiException {
        Map<String,Object> reqParam = new HashMap<>();
        reqParam.put("appId",appId);
        YinBaoResp resp = post(baseUrl,appId,appSecret,CAT_PAGE,reqParam);
        if(!resp.isSuc())
            throw new YinBaoApiException(resp.getStatus(),resp.getMessages());
        return resp;
    }

    public static boolean checkApiKey(String baseUrl,String appId,String apiSecret){
        try {
            catPage(baseUrl,appId,apiSecret);
            return true;
        } catch (Exception e) {
            logger.error("银豹ApiKey设置有误，appId="+appId+e.getMessage(),e);
            return false;
        }
    }


    public static YbMember addMember(String baseUrl, String appId, String appSecret, AddMemberReq req) throws YinBaoApiException {
        Map<String,Object> rp = new HashMap<>();
        Map<String,Object> customerInfo = new HashMap<>();
        rp.put("appId",appId);
        rp.put("customerInfo",customerInfo);

        customerInfo.put("number",req.getNumber());
        customerInfo.put("name",SStringUtils.isEmpty(req.getName()) ? req.getNumber() : req.getName());
        customerInfo.put("phone",req.getPhone());
        customerInfo.put("expiryDate",SDateUtil.format(req.getExpireTime(),SDateUtil.FM_yyyy_MM_dd_HH_mm_ss));

        YinBaoResp resp = post(baseUrl,appId,appSecret,MEMBER_ADD,rp);
        if(!resp.isSuc())
            throw new YinBaoApiException(resp.getStatus(),resp.getMessages());
        String data = resp.getData();
        System.out.println(data);
        YbMember m = FastJson.fromJson(data,YbMember.class);
        return m;
    }

    public static void delAllGoods(String baseUrl, String appId, String appSecret) throws YinBaoApiException {
        List<YbGoods> goods = qryAllGoods(baseUrl,appId,appSecret);

        System.out.println(FastJson.toJson(goods));
        for(YbGoods g : goods){
            if(g.getEnable() == 1)
                goodsUpd(baseUrl,appId,appSecret,UpdGoodsReq.buildDisableReq(g.getUid()));
        }
    }

    public static void main(String args[]) throws Exception{


        String baseUrl = "https://area53-win.pospal.cn:443/";
        String appId = "747A09332D58D135805F190A93C84FA7";
        String appSecret = "874859095189735996";
//        catAdd(baseUrl,appId,appSecret,"猫山王榴莲");

//        AddGoodsReq req = new AddGoodsReq();
//        req.setGoodsId(1L);
//        req.setName("猫山王果肉");
//        req.setPosCatId(1709867459998183336L);
//        req.setMainSpec(true);
//        req.setSpecName("0.2KG");
//        req.setPrice(new BigDecimal("100"));
//        req.setWeighing(false);
//        goodsAdd(baseUrl,appId,appSecret,req);

//        QryOrderReq req = new QryOrderReq();
////        req.setStartTime(SDateUtil.getTodayStartTime() - 2 * SDateUtil.MS_PER_DAY);
//        req.setStartTime(SDateUtil.getTodayStartTime());
//        req.setEndTime(req.getStartTime()+23*60*60*1000);
//        List<YbOrder> orders = qryOrder(baseUrl,appId,appSecret,req);
//        System.out.println(orders.size());

//        AddMemberReq req = new AddMemberReq();
//        req.setNumber("10008");
//        req.setName("李东云");
//        req.setPhone("18169408966");
//        req.setExpireTime(System.currentTimeMillis()+30*SDateUtil.SEC_PER_DAY);
//        addMember(baseUrl,"FE39EEFF17DE79E337AD71D0511385D4","511271613037108330",req);

//        goodsEnable(baseUrl,appId,appSecret,330976892038548422L);

//        UpdGoodsReq req = new UpdGoodsReq();
//        req.setUid(330976892038548422L);
//        req.setName("金枕头");
//        req.setPrice(new BigDecimal("30.88"));
//        req.setSpecName("大果");
//        req.setMainSpec(false);
//        goodsUpd(baseUrl,appId,appSecret,req);

//        List<YbGoods> list = qryAllGoods(baseUrl,appId,appSecret);
//        System.out.println(FastJson.toJson(list));

//        UpdCatReq req = new UpdCatReq();
//        req.setUid(1709867459998183336L);
//        req.setName("马来猫山王");
//        catUpd(baseUrl,appId,appSecret,req);
//        YinBaoResp resp = catPage(baseUrl,appId,appSecret);
//        System.out.println(FastJson.toJson(resp));

//        delAllGoods(baseUrl,appId,appSecret);



////        QryOrderReq req = new QryOrderReq();
////        req.setStartTime(0L);
////        req.setEndTime(System.currentTimeMillis());
//        List<YbOrder> goods = qryOrder(baseUrl,appId,appSecret,req);
//        System.out.println(FastJson.toJson(goods));
        YinBaoClient.qryAllGoods(baseUrl,appId,appSecret);
    }
}
