package com.verificer.biz.biz.service;

import com.verificer.biz.beans.vo.OrderDetailVo;
import com.verificer.biz.biz.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailVo> getVoListByOrdId(Long id);
    List<OrderDetail> getByOrdId(Long id);

}
