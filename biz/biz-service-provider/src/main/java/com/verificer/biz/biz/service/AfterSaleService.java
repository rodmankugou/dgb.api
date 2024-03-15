package com.verificer.biz.biz.service;

import com.verificer.biz.biz.entity.AfterSale;
import com.verificer.biz.biz.mapper.AfterSaleMapper;
import org.springframework.beans.factory.annotation.Autowired;

public interface AfterSaleService {
    public void add(AfterSale afterSale);

}
