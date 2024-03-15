package com.verificer.exchange.admin.unittest.order;

import com.verificer.biz.beans.enums.OrdSta;
import com.verificer.biz.beans.enums.MerType;
import com.verificer.biz.beans.enums.OrdType;
import com.verificer.biz.beans.enums.PayType;
import com.verificer.biz.beans.vo.GoodsVo;
import com.verificer.biz.beans.vo.req.OrdFormVo2;
import com.verificer.biz.beans.vo.req.OrdItemFormVo;
import com.verificer.exchange.admin.unittest.TResp;
import com.verificer.exchange.admin.unittest.Tools;
import com.verificer.utils.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OrderGen {
    static Map<Integer,Integer[]> staTypeMap = new HashMap<>();
    static {
        staTypeMap.put(OrdSta.WAIT_PAY.getValue(),new Integer[]{OrdType.SELF_TAKE.getValue(),OrdType.STAGE.getValue()});
        staTypeMap.put(OrdSta.InStock.getValue(),new Integer[]{OrdType.STAGE.getValue()});
        staTypeMap.put(OrdSta.WaitTransit.getValue(),new Integer[]{OrdType.STAGE.getValue()});
        staTypeMap.put(OrdSta.InTransit.getValue(),new Integer[]{OrdType.STAGE.getValue()});
        staTypeMap.put(OrdSta.Received.getValue(),new Integer[]{OrdType.SELF_TAKE.getValue(),OrdType.STAGE.getValue()});
        staTypeMap.put(OrdSta.Evaluated.getValue(),new Integer[]{OrdType.SELF_TAKE.getValue(),OrdType.STAGE.getValue()});
        staTypeMap.put(OrdSta.Finish.getValue(),new Integer[]{OrdType.POS.getValue(),OrdType.SELF_TAKE.getValue(),OrdType.STAGE.getValue()});
        staTypeMap.put(OrdSta.WaitSelfTake.getValue(),new Integer[]{OrdType.SELF_TAKE.getValue()});
    }

    public static List<OrdFormVo2> gen(Integer status, int num) throws SQLException {

        List<OrdFormVo2> oList = new LinkedList<>();
        for(int i=0; i< num-2;i++){
            oList.add(gen(status));
        }
        oList.add((gen(status)));

        return oList;
    }

    private static OrdFormVo2 gen(Integer status) throws SQLException {
        OrdFormVo2 o = new OrdFormVo2();
        o.setStatus(status);
        o.setBuyerRemark("");
        o.setUserId(getUserId());
        o.setOrderNum("DX"+ SStringUtils.generateRandomNumSequence(18));

        Integer[] typeList = staTypeMap.get(status);
        Integer type = null;
        if(typeList.length == 1){
            type = typeList[0];
        }else {
            int seed = RandomUtils.getInt(0,typeList.length-1);
            type = typeList[seed];
        }
        o.setOrderType(type);
        o.setDetails(genDetails());
        fillByType( o);
        return o;
    }

    private static Long twoHourRan(){
        return SDateUtil.MS_PER_SEC * RandomUtils.getInt(10,7200);
    }

    private static Long fourDayRan(){
        return SDateUtil.MS_PER_DAY * RandomUtils.getInt(0,4);
    }


    private static void fillByType(OrdFormVo2 o) throws SQLException {
        Integer type = o.getOrderType();
        Long bTime = 1709348003000L;
        Long createTime = null;
        Long transitTime = null;
        Long recTime = null;
        if(type == OrdType.POS.getValue()){
            createTime = bTime + fourDayRan() + twoHourRan();
            o.setPayType(PayType.POS.getValue());
            o.setRelId(getShopId());
            o.setRelType(MerType.SHOP.getValue());

        }else if(type == OrdType.SELF_TAKE.getValue()){
            createTime = bTime + fourDayRan() + twoHourRan();
            recTime = createTime + twoHourRan()*2;
            o.setTakeCode(SStringUtils.generateRandomNumSequence(6));
            o.setPayType(PayType.WX.getValue());
            o.setRelId(getShopId());
            o.setRelType(MerType.SHOP.getValue());

        }else {
            createTime = bTime+fourDayRan() + twoHourRan();
            transitTime = createTime + twoHourRan();
            recTime = transitTime + twoHourRan()*2;
            o.setAddrId(getAddrId());
            o.setPayType(PayType.WX.getValue());
            o.setRelId(getStageId());
            o.setRelType(MerType.STAGE.getValue());
            o.setTransitType(1);
        }

        int status = o.getStatus();
        if(status == 1 || status == 2 || status == 3)
            transitTime = null;
        if(status == 4 || status == 22)
            recTime = null;

        o.setCreateTime(createTime);
        o.setTransitTime(transitTime);
        o.setTakeTime(recTime);

    }

    private static String getShopId() throws SQLException {
        Connection conn = C3p0Tools.getInstance().getConnection();
        String sql = "select * from shop where del_flag = 0";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = conn.prepareStatement(sql);

            rs = stm.executeQuery();
            if (rs.next()){
                return rs.getString("id");
            }
            throw new RuntimeException("shop表没有数据");
        }finally {
            DbUtil.closeConnection(conn,stm,rs);
        }

    }

    private static String getUserId() throws SQLException {
        Connection conn = C3p0Tools.getInstance().getConnection();
        String sql = "select * from user ";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = conn.prepareStatement(sql);

            rs = stm.executeQuery();
            if (rs.next()){
                return rs.getString("id");
            }
            throw new RuntimeException("stage表没有数据");
        }finally {
            DbUtil.closeConnection(conn,stm,rs);
        }
    }

    private static String getStageId() throws SQLException {
        Connection conn = C3p0Tools.getInstance().getConnection();
        String sql = "select * from stage ";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = conn.prepareStatement(sql);

            rs = stm.executeQuery();
            if (rs.next()){
                return rs.getString("id");
            }
            throw new RuntimeException("stage表没有数据");
        }finally {
            DbUtil.closeConnection(conn,stm,rs);
        }
    }

    private static Long getAddrId() throws SQLException {
        Connection conn = C3p0Tools.getInstance().getConnection();
        String sql = "select * from addr ";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = conn.prepareStatement(sql);

            rs = stm.executeQuery();
            if (rs.next()){
                return rs.getLong("id");
            }
            throw new RuntimeException("addr表没有数据");
        }finally {
            DbUtil.closeConnection(conn,stm,rs);
        }
    }

    private static List<OrdItemFormVo> genDetails(){
        Map<String,Object> map = new HashMap<>();
        map.put("page",1);
        map.put("pageSize",10);
        TResp resp = Tools.callApi("goods/page",FastJson.toJson(map));
        if(resp.getCode() != 1)
            throw new RuntimeException("获取商品失败");
        String json = FastJson.toJson(resp);
        GoodsResp gr = FastJson.fromJson(json,GoodsResp.class);
        List<GoodsVo> goodsList = gr.getData();
        if(goodsList.size() < 2)
            throw new RuntimeException("商品表中数量不足，必须要有超过2条数据");

        List<OrdItemFormVo> list = new LinkedList<>();
        int gSize = RandomUtils.getInt(1,2);
        BigDecimal cSize = new BigDecimal(RandomUtils.getInt(1,4));
        int gIdx = RandomUtils.getInt(0,1);
        if(gSize == 1){
            list.add(genDetail(goodsList.get(gIdx),cSize));
        }else {
            list.add(genDetail(goodsList.get(0),cSize));
            list.add(genDetail(goodsList.get(1),cSize));

        }

        return list;
    }

    public static OrdItemFormVo genDetail(GoodsVo goods, BigDecimal count){
        OrdItemFormVo vo = new OrdItemFormVo();
        vo.setCount(count);
        vo.setPrice(goods.getSpecList().get(0).getPrice());
        vo.setSpecId(goods.getSpecList().get(0).getId());
        vo.setGoodsId(goods.getId());
        return vo;
    }

    public static void genGoods() throws SQLException {
        List<OrdFormVo2> formList = new LinkedList<>();
        for(Integer key : staTypeMap.keySet()){
            formList.addAll(gen(key,5));
        }

        for(OrdFormVo2 form : formList){
            Tools.callApi("/ot/submit",FastJson.toJson(form));
        }
    }

    public static void main(String args[]) throws SQLException {
//        System.out.println(FastJson.toJson(gen(1,3)));
        System.out.println(FastJson.toJson(genDetails()));

    }
}
