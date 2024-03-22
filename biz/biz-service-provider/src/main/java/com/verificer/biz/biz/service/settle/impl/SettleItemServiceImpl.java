package com.verificer.biz.biz.service.settle.impl;

import com.verificer.biz.beans.vo.settle.SettleItemVo;
import com.verificer.biz.beans.vo.settle.req.SettleItemQryVo;
import com.verificer.biz.biz.entity.Settle;
import com.verificer.biz.biz.entity.SettleItem;
import com.verificer.biz.biz.entity.SettleOrder;
import com.verificer.biz.biz.mapper.SettleItemMapper;
import com.verificer.biz.biz.service.settle.SettleItemService;
import com.verificer.utils.SBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class SettleItemServiceImpl implements SettleItemService {


    @Autowired
    SettleItemMapper mapper;

    private SettleItemVo toVo(SettleItem e){
        if(e == null)
            return  null;
        SettleItemVo vo = new SettleItemVo();
        SBeanUtils.copyProperties2(e,vo);
        return vo;
    }

    private List<SettleItemVo> toVoList(List<SettleItem> list){

        List<SettleItemVo> voList = new LinkedList<>();
        for(SettleItem e : list){
            
            voList.add(toVo(e));
        }
        return voList;
    }
    
    @Override
    public List<SettleItemVo> settleItemPage(SettleItemQryVo reqVo) {
        List<SettleItem> list = mapper.page(reqVo);
        return toVoList(list);

    }

    @Override
    public int settleItemCount(SettleItemQryVo reqVo) {
        return mapper.count(reqVo);
    }

    @Override
    public void createItem(SettleOrder so, Settle settle) {
        SettleItem item = new SettleItem();
        item.setOrderId(so.getId());
        item.setSettleId(settle.getId());
        item.setSettlePhase(settle.getCalPahse());
        item.setShopId(so.getShopId());
        item.setTitle("会员分期");
        item.setRemark(settle.getTitle()+item.getSettlePhase()+"/"+settle.getTotalPhase()+"期");
        item.setYear(so.getYear());
        item.setMonth(so.getMonth());
        item.setSettleFlag(false);
        item.setAmount(settle.getPhaseAmount());
        item.setCommissionRate(settle.getCommissionRate());
        mapper.insertSelective(item);
    }

    @Override
    public List<SettleItem> getBySettleOrdId(Long orderId) {
        return mapper.selectByOrderId(orderId);
    }

    @Override
    public void afterSettle(SettleOrder so) {
        mapper.updSettleFlagByOrderId(so.getId());

    }
}
