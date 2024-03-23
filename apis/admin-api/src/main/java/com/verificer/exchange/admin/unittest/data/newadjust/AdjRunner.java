package com.verificer.exchange.admin.unittest.data.newadjust;

import com.verificer.biz.beans.enums.MerType;
import com.verificer.biz.beans.vo.adjust.req.AdjItemFormVo;
import com.verificer.biz.beans.vo.adjust.req.AdjOrdConfirmItemVo;
import com.verificer.biz.beans.vo.adjust.req.AdjOrdConfirmVo;
import com.verificer.biz.beans.vo.adjust.req.AdjOrdFormVo;
import com.verificer.exchange.admin.unittest.Tools;
import com.verificer.utils.FastJson;
import com.verificer.utils.RandomUtils;
import com.verificer.utils.reflect.SBeanUtils;
import com.verificer.tools.db.*;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AdjRunner {

    private static final  String ADJ_OTHER_ID = "ADJ_OTHER";

    static class Spec{
        Long id;
        Long goodsId;
    }

    public static List<Spec> loadAllSpec() throws SQLException {
        String sql = "select * from spec;";
        return DbTools.read("dbg", new IReader<Spec>(sql) {
            @Override
            public Spec read(ResultSet rs) throws SQLException {
                Spec spec = new Spec();
                spec.id = rs.getLong("id");
                spec.goodsId = rs.getLong("goods_id");
                return spec;
            }
        });
    }

    public static List<AdjOrdConfirmItemVo> loadItems(long ordId) throws SQLException {
        String sql = "select * from adjust_item where order_id = " + ordId;
        return DbTools.read("dbg", new IReader<AdjOrdConfirmItemVo>(sql) {
            @Override
            public AdjOrdConfirmItemVo read(ResultSet rs) throws SQLException {
                AdjOrdConfirmItemVo av = new AdjOrdConfirmItemVo();
                av.setId(rs.getLong("id"));
                av.setRealCount(rs.getBigDecimal("count"));
                return av;
            }
        });
    }

    public static void adj() throws SQLException {
        List<Spec> specList = loadAllSpec();
        AdjOrdFormVo afStage = new AdjOrdFormVo();
        afStage.setFromId(ADJ_OTHER_ID);
        afStage.setToId(Tools.getStageId());
        afStage.setFromType(MerType.STAGE.getValue());
        afStage.setToType(MerType.STAGE.getValue());


        AdjOrdFormVo afShop = new AdjOrdFormVo();
        afShop.setFromId(Tools.getStageId());
        afShop.setToId(Tools.getShopId());
        afShop.setFromType(MerType.STAGE.getValue());
        afShop.setToType(MerType.SHOP.getValue());

        List<AdjItemFormVo> itemsShop = new LinkedList<>();
        List<AdjItemFormVo> itemsStage = new LinkedList<>();
        afShop.setItems(itemsShop);
        afStage.setItems(itemsStage);



        for(Spec spec : specList){

            AdjItemFormVo item = new AdjItemFormVo();
            itemsStage.add(item);
            item.setGoodsId(spec.goodsId);
            item.setSpecId(spec.id);
            item.setCount(new BigDecimal(RandomUtils.getInt(1000,3000)));





            AdjItemFormVo itemShop = new AdjItemFormVo();
            SBeanUtils.copyProperties2(item,itemShop);
            itemShop.setCount(new BigDecimal(RandomUtils.getInt(100,500)));
            itemsShop.add(itemShop);





        }

        Tools.callApi("/adjust/order/create", FastJson.toJson(afStage));

        AdjOrdConfirmVo ccv = new AdjOrdConfirmVo();
        ccv.setId(getId());
        ccv.setItems(loadItems(ccv.getId()));
        Tools.callApi("/adjust/order/confirm", FastJson.toJson(ccv));


        Tools.callApi("/adjust/order/create", FastJson.toJson(afShop));
        AdjOrdConfirmVo scv = new AdjOrdConfirmVo();
        scv.setId(getId());
        scv.setItems(loadItems(scv.getId()));

        Tools.callApi("/adjust/order/confirm", FastJson.toJson(scv));
    }

    public  static Long getId() throws SQLException {
        return Tools.getTableMaxId("dbg","adjust_order");
    }

    public static void run() throws SQLException {
//        Tools.init("dbg", "newadjust/init.sql");
//        Runner.run("goods/add_1");
//        Runner.run("goods/add_2");
//        adj();

//        Tools.callApi("adjust/order/page","{}");
        Tools.callApi("adjust/item/list","{\"orderId\":"+getId()+"}");


//        List<Spec> specList = loadAllSpec();


    }





    public static void main(String[] args) throws SQLException {
        run();
    }
}
