package com.verificer.biz.biz.service.impl;

import com.verificer.biz.beans.vo.OrderDetailVo;
import com.verificer.biz.biz.entity.OrderDetail;
import com.verificer.biz.biz.mapper.OrderDetailMapper;
import com.verificer.biz.biz.service.OrderDetailService;
import com.verificer.utils.SBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Override
    public List<OrderDetailVo> getVoListByOrdId(Long id) {
        List<OrderDetail> orderDetails = orderDetailMapper.selectByOrdId(id);
        List<OrderDetailVo> voList = new LinkedList<>();
        for(OrderDetail o : orderDetails){
            OrderDetailVo vo = new OrderDetailVo();
            SBeanUtils.copyProperties2(o,vo);
            voList.add(vo);
        }
        return voList;
    }
}
