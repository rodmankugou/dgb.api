package com.verificer.biz.biz.service.impl;

import com.verificer.ErrCode;
import com.verificer.biz.beans.enums.EvaluateSta;
import com.verificer.biz.beans.vo.evaluate.EvaluateQryVo;
import com.verificer.biz.beans.vo.evaluate.EvaluateReviewVo;
import com.verificer.biz.beans.vo.evaluate.EvaluateVo;
import com.verificer.biz.biz.entity.Evaluate;
import com.verificer.biz.biz.mapper.EvaluateMapper;
import com.verificer.biz.biz.service.EvaluateService;
import com.verificer.common.exception.BaseException;
import com.verificer.utils.SBeanUtils;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class EvaluateServiceImpl implements EvaluateService {
    @Autowired
    EvaluateMapper mapper;

    private EvaluateVo toVo(Evaluate e){
        if(e == null)
            return  null;
        EvaluateVo vo = new EvaluateVo();
        SBeanUtils.copyProperties2(e,vo);
        return vo;
    }

    private List<EvaluateVo> toVoList(List<Evaluate> list){

        List<EvaluateVo> voList = new LinkedList<>();
        for(Evaluate e : list){
            voList.add(toVo(e));
        }
        return voList;
    }

    @Override
    public List<EvaluateVo> evaluatePage(EvaluateQryVo reqVo) {
        List<Evaluate> list = mapper.page(reqVo);
        return toVoList(list);
    }

    @Override
    public int evaluateCount(EvaluateQryVo reqVo) {
        return mapper.count(reqVo);
    }

    @Override
    public void evaluateReview(EvaluateReviewVo reqVo) {
        SCheckUtil.notEmpty(reqVo.getId(),"ID");
        SCheckUtil.notEmpty(reqVo.getPassFlag(),"Pass Flag");
        Evaluate e = mapper.selectByPrimaryKey(reqVo.getId());
        if(e == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        if(EvaluateSta.WAIT_REVIEW.getValue() != e.getStatus())
            throw new RuntimeException(ErrCode.EVALUATE_NOT_ON_WAIT_REVIEW);

        e.setStatus(reqVo.getPassFlag() ? EvaluateSta.PASS.getValue() : EvaluateSta.REJECT.getValue());
        mapper.updateByPrimaryKeySelective(e);

    }
}
