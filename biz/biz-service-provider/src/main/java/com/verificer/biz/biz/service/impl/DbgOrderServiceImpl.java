package com.verificer.biz.biz.service.impl;

import com.verificer.biz.beans.enums.OrdSta;
import com.verificer.biz.beans.enums.MerType;
import com.verificer.biz.beans.enums.OrdDtlSta;
import com.verificer.biz.beans.vo.DbgOrderVo;
import com.verificer.biz.beans.vo.OrderDetailVo;
import com.verificer.biz.beans.vo.req.OrdFormVo2;
import com.verificer.biz.beans.vo.req.OrdItemFormVo;
import com.verificer.biz.beans.vo.req.OrderPageVo;
import com.verificer.biz.biz.entity.*;
import com.verificer.biz.biz.mapper.DbgOrderMapper;
import com.verificer.biz.biz.mapper.OrderDetailMapper;
import com.verificer.biz.biz.service.*;
import com.verificer.common.exception.BizErrMsgException;
import com.verificer.utils.reflect.SBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class DbgOrderServiceImpl implements DbgOrderService {

    @Autowired
    AddrService addrService;

    @Autowired
    DbgOrderMapper mapper;

    @Autowired
    StageService stageService;

    @Autowired
    ShopService shopService;

    @Autowired
    OrderDetailMapper orderDetailMapper ;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    SpecService specService;

    private DbgOrderVo toVo(DbgOrder o){
        if(o == null)
            return null;
        DbgOrderVo vo = new DbgOrderVo();
        SBeanUtils.copyProperties2(o,vo);
        List<OrderDetailVo> odVoList = orderDetailService.getVoListByOrdId(vo.getId());
        vo.setOrderItems(odVoList);
        return vo;
    }

    @Override
    public List<DbgOrderVo> orderPage(OrderPageVo qryVo) {
        List<DbgOrder> orders = mapper.page(qryVo);
        List<DbgOrderVo> voList = new LinkedList<>();
        for(DbgOrder o : orders){
            voList.add(toVo(o));

        }
        return voList;
    }

    @Override
    public int orderCount(OrderPageVo qryVo) {
        return mapper.count(qryVo);
    }

    @Override
    public Long orderAdd(OrdFormVo2 formVo) {
        DbgOrder order = new DbgOrder();
        order.setStatus(formVo.getStatus());
        order.setUserId(formVo.getUserId());
        order.setOrderType(formVo.getOrderType());
        order.setRelType(formVo.getRelType());
        order.setRelId(formVo.getRelId());
        order.setTransitType(formVo.getTransitType());
        order.setBuyerRemark(formVo.getBuyerRemark());
        order.setPayType(formVo.getPayType());
        order.setOrderNum(formVo.getOrderNum());
        order.setTransitTime(formVo.getTransitTime());
        order.setTransitOrderNum(formVo.getTransitOrderNum());
        order.setTakeTime(formVo.getTakeTime());
        order.setTakeCode(formVo.getTakeCode());
        order.setCreateTime(formVo.getCreateTime());
        order.setDelFlag(false);

        if(order.getRelType() == MerType.STAGE.getValue()){
            Stage stage = stageService.getById(order.getRelId());
            order.setRefName(stage.getName());
        }else if(order.getRelType() == MerType.SHOP.getValue()){
            Shop shop = shopService.getById(order.getRelId());
            order.setRefName(shop.getName());
        }else {
            throw new BizErrMsgException("Parameter refType error");
        }

        if(formVo.getAddrId() != null){
            Addr addr = addrService.getById(formVo.getAddrId(),formVo.getUserId());
            if(addr == null)
                throw new BizErrMsgException("Address not exist");

            order.setRcAddr(addr.getAddr());
            order.setRcAddrDetail(addr.getDetailAddr());
            order.setRcLatitude(addr.getLatitude());
            order.setRcLongitude(addr.getLongitude());
            order.setRcMobile(addr.getMobile());
            order.setRcName(addr.getRcName());
        }


        mapper.insertSelective(order);

        //TODO 废弃代码，造数据用
        if(formVo.getDetails() == null || formVo.getDetails().size() == 0){
            throw new BizErrMsgException("Details 不能为空");
        }
        for(OrdItemFormVo vo : formVo.getDetails()){
            Goods goods = goodsService.getById(vo.getGoodsId());
            Spec spec = specService.getById(vo.getSpecId());
            OrderDetail od = new OrderDetail();
            od.setOrderId(order.getId());
            od.setGoodsId(vo.getGoodsId());
            od.setGoodsName(goods.getName());
            od.setSpecId(vo.getSpecId());
            od.setSpecName(spec.getName());
            od.setSpecImg(spec.getImg());
            od.setTransitFee(BigDecimal.ZERO);
            od.setPrice(vo.getPrice());
            od.setCount(vo.getCount());
            od.setAmount(vo.getPrice().multiply(vo.getCount()));

            if(order.getStatus() == OrdSta.WAIT_PAY.getValue()
                    || order.getStatus() == OrdSta.WaitTransit.getValue()
                    || order.getStatus() == OrdSta.InStock.getValue()
            ){
                od.setStatus(OrdDtlSta.WAIT_TRANSIT.getValue());
            }else if(order.getStatus() == OrdSta.InTransit.getValue()

            ){
                od.setStatus(OrdDtlSta.WAIT_TRANSIT.getValue());

            }else {
                od.setStatus(OrdDtlSta.RECEIVED.getValue());
            }

            orderDetailMapper.insertSelective(od);

        }




        return order.getId();

    }

    @Override
    public DbgOrderVo orderDetail(Long id) {
        DbgOrder order = mapper.selectByPrimaryKey(id);
        return toVo(order);
    }

}
