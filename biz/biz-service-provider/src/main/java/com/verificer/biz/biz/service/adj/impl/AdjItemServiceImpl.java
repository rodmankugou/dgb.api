package com.verificer.biz.biz.service.adj.impl;

import com.verificer.ErrCode;
import com.verificer.biz.beans.enums.AdjItemSta;
import com.verificer.biz.beans.vo.adjust.TreeAdjItemVo;
import com.verificer.biz.beans.vo.adjust.req.AdjItemFormVo;
import com.verificer.biz.beans.vo.adjust.req.AdjItemTreeQryVo;
import com.verificer.biz.beans.vo.adjust.req.AdjOrdConfirmItemVo;
import com.verificer.biz.biz.entity.AdjustItem;
import com.verificer.biz.biz.entity.AdjustOrder;
import com.verificer.biz.biz.mapper.AdjustItemMapper;
import com.verificer.biz.biz.service.adj.AdjItemService;
import com.verificer.biz.biz.service.common.GoodsCommon;
import com.verificer.common.exception.BaseException;
import com.verificer.common.exception.BizErrMsgException;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdjItemServiceImpl implements AdjItemService {
    @Autowired
    AdjustItemMapper mapper;

    @Autowired
    GoodsCommon goodsCommon;


    @Override
    public List<AdjustItem> getByOrdId(Long ordId) {
        return mapper.selectByOrdId(ordId);
    }

    @Override
    public List<TreeAdjItemVo> adjItemTree(AdjItemTreeQryVo qryVo) {
        List<AdjustItem> items = mapper.selectByOrdId(qryVo.getOrderId());

        LinkedHashMap<Long,TreeAdjItemVo>  map = buildTreeMap(items);
        List<TreeAdjItemVo> tree = new LinkedList<>();
        for(Map.Entry<Long,TreeAdjItemVo> entry : map.entrySet()){
            tree.add(entry.getValue());
        }
        return tree;
    }

    private LinkedHashMap<Long,TreeAdjItemVo> buildTreeMap(List<AdjustItem> items){
        LinkedHashMap<Long,TreeAdjItemVo> map = new LinkedHashMap<>();
        for(AdjustItem vo : items){
            Long goodsId = vo.getGoodsId();
            if(!map.containsKey(goodsId)){
                TreeAdjItemVo goods = new TreeAdjItemVo();
                goods.setId(vo.getGoodsId());
                goods.setName(vo.getGoodsName());
                goods.setCount(BigDecimal.ZERO);
                goods.setRealCount(BigDecimal.ZERO);
                goods.setSpecList(new LinkedList<>());
                map.put(goodsId,goods);
            }

            TreeAdjItemVo goods = map.get(goodsId);
            goods.setCount(goods.getCount().add(vo.getCount()));
            if(vo.getRealCount() != null)
                goods.setRealCount(goods.getRealCount().add(vo.getRealCount()));
            goods.getSpecList().add(itemToTree(vo));
        }
        return map;
    }

    private TreeAdjItemVo itemToTree(AdjustItem item){
        TreeAdjItemVo spec = new TreeAdjItemVo();
        spec.setId(item.getSpecId());
        spec.setName(item.getSpecName());
        spec.setCount(item.getCount());
        spec.setRealCount(item.getRealCount());
        return spec;
    }

    @Override
    public void checkParams( List<AdjItemFormVo> items) {
        if(items == null || items.size() == 0)
            throw new BizErrMsgException("Parameter items can not be empty");
        for(AdjItemFormVo item : items){
            SCheckUtil.lgThanAndNotNull(item.getCount(),true, BigDecimal.ZERO,"Count");
            SCheckUtil.notEmpty(item.getGoodsId(),"item.goodsId");
            SCheckUtil.notEmpty(item.getSpecId(),"item.specId");


        }
    }

    @Override
    public void create(AdjustOrder o, List<AdjItemFormVo> items) {
        checkParams(items);
        for(AdjItemFormVo item : items){
            AdjustItem ai = new AdjustItem();
            ai.setOrderId(o.getId());
            ai.setStatus(AdjItemSta.WAIT_CONFIRM.getValue());
            ai.setGoodsId(item.getGoodsId());
            ai.setSpecId(item.getSpecId());
            ai.setGoodsName(goodsCommon.getGoodsName(item.getGoodsId()));
            ai.setSpecName(goodsCommon.getSpecName(item.getSpecId()));
            ai.setCount(item.getCount());
            ai.setCreateTime(System.currentTimeMillis());
            mapper.insertSelective(ai);
        }
    }

    @Override
    public List<AdjustItem> preConfirm(AdjustOrder o, List<AdjOrdConfirmItemVo> items) {
        Long now = System.currentTimeMillis();
        Map<Long,AdjOrdConfirmItemVo> confirmMap = new HashMap<>();
        for(AdjOrdConfirmItemVo cv : items)
            confirmMap.put(cv.getId(),cv);
        List<AdjustItem> ais = mapper.selectByOrdId(o.getId());
        for(AdjustItem ai : ais){
            AdjOrdConfirmItemVo cv = confirmMap.get(ai.getId());
            if(cv == null)
                throw new BaseException(ErrCode.ADJ_ORD_CONFIRM_LESS_ITEM);

            ai.setFinishTime(now);
            ai.setStatus(AdjItemSta.SUC.getValue());
            ai.setRealCount(cv.getRealCount());


        }
        return ais;
    }

    @Override
    public void onConfirm(AdjustOrder o, List<AdjOrdConfirmItemVo> items) {
        List<AdjustItem> ais = preConfirm(o,items);

        for(AdjustItem ai : ais)
            mapper.updateByPrimaryKeySelective(ai);
    }



}
