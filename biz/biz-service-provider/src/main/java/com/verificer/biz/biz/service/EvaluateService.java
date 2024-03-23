package com.verificer.biz.biz.service;

import com.verificer.biz.beans.vo.evaluate.EvaluateQryVo;
import com.verificer.biz.beans.vo.evaluate.EvaluateReviewVo;
import com.verificer.biz.beans.vo.evaluate.EvaluateVo;

import java.util.List;

public interface EvaluateService {
    List<EvaluateVo> evaluatePage(EvaluateQryVo reqVo);

    int evaluateCount(EvaluateQryVo reqVo);

    /**
     * 审核商品评论
     * @param reqVo
     */
    void evaluateReview(EvaluateReviewVo reqVo);
}
