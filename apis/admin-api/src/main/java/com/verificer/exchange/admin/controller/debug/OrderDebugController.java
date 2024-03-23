package com.verificer.exchange.admin.controller.debug;

import com.verificer.GlobalConfig;
import com.verificer.biz.beans.enums.MerType;
import com.verificer.biz.beans.enums.OrdType;
import com.verificer.biz.beans.vo.GoodsVo;
import com.verificer.biz.beans.vo.SpecVo;
import com.verificer.biz.beans.vo.debug.order.DebPosOrderVo;
import com.verificer.biz.beans.vo.order.req.OrdFormVo;
import com.verificer.biz.beans.vo.order.YbOrdFormVo;
import com.verificer.biz.beans.vo.order.YbOrdItemVo;
import com.verificer.biz.beans.vo.req.GoodsQryVo;
import com.verificer.biz.beans.vo.req.OrdItemFormVo;
import com.verificer.biz.biz.service.BizService;
import com.verificer.common.exception.BizErrMsgException;
import com.verificer.exchange.admin.security.annotation.DebugController;
import com.verificer.utils.RandomUtils;
import com.verificer.utils.reflect.SBeanUtils;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 35336 on 2021/2/26.
 */
@Api(tags = "订单Debug")
@RequestMapping("/debug/order")
@RestController
@DebugController
public class OrderDebugController extends BaseDebugController{

    @Autowired
    BizService bizService;


    @ApiOperation(
            value = "新增Pos机收银订单",
            response = Response.class,
            httpMethod = "POST",
            notes = "响应报文的data字段为订单ID"
    )
    @ApiImplicitParams({
    })
    @ResponseBody
    @RequestMapping(value = "/create/pos", method = RequestMethod.POST)
    public Response createPosOrder(@RequestBody DebPosOrderVo  reqVo) {
        YbOrdFormVo fvo = new YbOrdFormVo();
        fvo.setPosOrdId(System.currentTimeMillis());
        fvo.setPosCashierId(121212L);
        fvo.setPosOrdTime(System.currentTimeMillis());
        fvo.setPosMemberId(212121L);
        fillOrdFormVo(fvo,reqVo.getMultiGoodsFlag() ? 2:1,OrdType.POS,MerType.SHOP);
        List<OrdItemFormVo> items = fvo.getDetails();
        List<OrdItemFormVo> ybItems = new LinkedList<>();
        for(OrdItemFormVo item : items){
            YbOrdItemVo ybItem = new YbOrdItemVo();
            SBeanUtils.copyProperties2(item,ybItem);
            ybItem.setAmount(item.getCount().multiply(item.getPrice()).setScale(GlobalConfig.PREC,RoundingMode.HALF_UP));
            ybItem.setRealPrice(item.getPrice());
            ybItems.add(ybItem);
        }
        fvo.setDetails(ybItems);


        Long id = bizService.orderCreate(fvo);
        return Response.dataSuccess(id);
    }

    private void fillOrdFormVo(OrdFormVo ordFormVo, int goodsSize, OrdType ordType, MerType merType){
        List<OrdItemFormVo> items = genItems(goodsSize);
        ordFormVo.setUserId(getUserId());
        ordFormVo.setOrderType(ordType.getValue());
        ordFormVo.setRelType(ordType.getValue());
        if(MerType.SHOP.getValue() == merType.getValue())
            ordFormVo.setRelId(getShopId());
        else
            ordFormVo.setRelId(getStageId());

        if(ordType.getValue() == OrdType.STAGE.getValue() || ordType.getValue() == OrdType.REISSUE.getValue()){
            ordFormVo.setAddrId(getAddrId(ordFormVo.getUserId()));
        }
        ordFormVo.setTransitType(1);
        ordFormVo.setBuyerRemark("备注1111");
        ordFormVo.setDetails(items);

        BigDecimal amount = BigDecimal.ZERO;
        for(OrdItemFormVo item : items){
            amount  = amount.add(item.getCount().multiply(item.getPrice()));
        }
        amount.setScale(GlobalConfig.PREC, RoundingMode.HALF_UP);
        ordFormVo.setAmount(amount);

    }




    private Long getAddrId(Long userId){
        return 1L;
    }

    private List<OrdItemFormVo> genItems(int size){
        GoodsQryVo qry = new GoodsQryVo();
        qry.setPageSize(1000);
        List<GoodsVo> allGoodsList = bizService.goodsPage(qry);

        List<GoodsVo> gList = new LinkedList<>();
        for(GoodsVo g : allGoodsList){
            if(g.getSaleFlag())
                gList.add(g);
        }

        if(size > gList.size())
            throw new BizErrMsgException("上架的商品数量为："+size+"，无法满足创建订单要求");

        List<OrdItemFormVo> items = new LinkedList<>();
        for(int i = 0 ; i<size; i++){
            GoodsVo g = gList.get(i);
            SpecVo spec = g.getSpecList().get(0);

            OrdItemFormVo item = new OrdItemFormVo();
            items.add(item);
            item.setGoodsId(g.getId());
            item.setSpecId(spec.getId());
            item.setCount(new BigDecimal(RandomUtils.getInt(1,4)));
            item.setPrice(spec.getPrice());
        }

        return items;
    }
}
