package com.verificer.biz.biz.service;

import com.verificer.biz.beans.vo.OrderDetailVo;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailVo> getVoListByOrdId(Long id);
}
