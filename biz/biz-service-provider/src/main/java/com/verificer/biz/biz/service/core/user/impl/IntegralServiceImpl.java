package com.verificer.biz.biz.service.core.user.impl;

import com.verificer.ErrCode;
import com.verificer.account.itf.AccTools;
import com.verificer.account.itf.BaseAccountService;
import com.verificer.account.itf.IntegralTools;
import com.verificer.beans.IdVo;
import com.verificer.beans.account.AccountVo;
import com.verificer.biz.beans.vo.integral.AppIntegralLogVo;
import com.verificer.biz.beans.vo.integral.IntegralListVo;
import com.verificer.biz.biz.entity.User;
import com.verificer.biz.biz.mapper.BizAccLogMapper;
import com.verificer.biz.biz.service.BizService;
import com.verificer.biz.biz.service.common.UserCommon;
import com.verificer.biz.biz.service.core.user.IntegralService;
import com.verificer.common.exception.BaseException;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.sasl.SaslClient;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class IntegralServiceImpl implements IntegralService {
    @Autowired
    BizAccLogMapper bizAccLogMapper;

    @Autowired
    BaseAccountService baseAccountService;

    @Autowired
    UserCommon userCommon;


    @Override
    public BigDecimal integralBalance(IdVo idVo) {
        SCheckUtil.notEmpty(idVo.getId(),"id");
        User user = userCommon.getById(idVo.getId());
        if(user == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        AccountVo accountVo = baseAccountService.getAccount(user.getUid(), AccTools.USR_INTEGRAL);
        if(accountVo == null)
            return BigDecimal.ZERO;

        return accountVo.getAvailableAmount();
    }

    @Override
    public List<AppIntegralLogVo> integralList(IntegralListVo reqVo) {
        SCheckUtil.notEmpty(reqVo.getUserId(),"User Id");
        User user = userCommon.getById(reqVo.getUserId());
        if(user == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        AccountVo accountVo = baseAccountService.getAccount(user.getUid(), AccTools.USR_INTEGRAL);
        if(accountVo == null)
            return new LinkedList<>();

        reqVo.setAccountId(accountVo.getId());

        List<AppIntegralLogVo> list = bizAccLogMapper.integralList(reqVo);
        for(AppIntegralLogVo vo : list){
            IntegralTools.IntegralRemark remark = IntegralTools.parse(vo.getRemark());
            vo.setTitle(remark.title);
            vo.setSubTitle(remark.subTitle);
        }
        return list;
    }
}
