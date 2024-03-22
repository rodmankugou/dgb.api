package com.verificer.biz.biz.service.settle.impl;

import com.verificer.biz.beans.vo.settle.PlaIncomeLogVo;
import com.verificer.biz.beans.vo.settle.req.PlaIncomeLogQryVo;
import com.verificer.biz.biz.mapper.PlaIncomeLogMapper;
import com.verificer.biz.biz.service.settle.PlaIncomeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class PlaIncomeLogServiceImpl implements PlaIncomeLogService {
    @Autowired
    PlaIncomeLogMapper mapper;


    @Override
    public List<PlaIncomeLogVo> plaIncomeLogPage(PlaIncomeLogQryVo reqVo) {
        return mapper.page(reqVo);
    }

    @Override
    public int plaIncomeLogCount(PlaIncomeLogQryVo reqVo) {
        return mapper.count(reqVo);
    }
}
