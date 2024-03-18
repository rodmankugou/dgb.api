package com.verificer.biz.biz.service.impl;

import com.verificer.biz.beans.vo.OrderDetailVo;
import com.verificer.biz.biz.entity.OrderDetail;
import com.verificer.biz.biz.mapper.OrderDetailMapper;
import com.verificer.biz.biz.service.OrderDetailService;
import com.verificer.utils.SBeanUtils;
import com.verificer.utils.SBigDecimalUtils;
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
            if(SBigDecimalUtils.getRealScale(o.getCount()) == 0)
                vo.setCount(o.getCount().setScale(0));
            else
                vo.setCount(o.getCount().setScale(2));

            voList.add(vo);
        }
        return voList;
    }

    @Override
    public List<OrderDetail> getByOrdId(Long id) {
        return orderDetailMapper.selectByOrdId(id);
    }
}
