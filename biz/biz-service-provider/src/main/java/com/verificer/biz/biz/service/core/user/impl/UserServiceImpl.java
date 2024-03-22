package com.verificer.biz.biz.service.core.user.impl;

import com.verificer.ErrCode;
import com.verificer.account.itf.AccOpType;
import com.verificer.account.itf.AccTools;
import com.verificer.account.itf.BaseAccountService;
import com.verificer.beans.IdVo;
import com.verificer.beans.account.AccountVo;
import com.verificer.biz.beans.vo.user.RefereeVo;
import com.verificer.biz.beans.vo.user.ReferrerStaVo;
import com.verificer.biz.beans.vo.user.UserWithdrawVo;
import com.verificer.biz.beans.vo.user.UserVo;
import com.verificer.biz.beans.vo.req.UserPageVo;
import com.verificer.biz.beans.vo.user.req.*;
import com.verificer.biz.biz.entity.User;
import com.verificer.biz.biz.mapper.BizAccLogMapper;
import com.verificer.biz.biz.mapper.UserMapper;
import com.verificer.biz.biz.service.core.user.UserService;
import com.verificer.common.exception.BaseException;
import com.verificer.utils.SBeanUtils;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    BizAccLogMapper bizAccLogMapper;

    @Autowired
    BaseAccountService baseAccountService;

    @Autowired
    UserMapper userMapper;

    private UserVo toVo(User e){
        if(e == null)
            return  null;
        UserVo vo = new UserVo();
        SBeanUtils.copyProperties2(e,vo);
        return vo;
    }

    private List<UserVo> toVoList(List<User> list){

        List<UserVo> voList = new LinkedList<>();
        for(User e : list){
            UserVo vo = new UserVo();
            SBeanUtils.copyProperties2(e,vo);
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public List<UserVo> userPage(UserPageVo qryVo) {
        List<User> user = userMapper.page(qryVo);
        return toVoList(user);
    }

    @Override
    public int userCount(UserPageVo qryVo) {
        return userMapper.count(qryVo);
    }

    @Override
    public UserVo userDetail(IdVo idVo) {
        SCheckUtil.notEmpty(idVo.getId(),"ID");

        User user = userMapper.selectByPrimaryKey(idVo.getId());
        return toVo(user);
    }

    @Override
    public List<RefereeVo> userRefereeList(RefereeListReqVo reqVo) {
        preProcessListReqVo(reqVo);
        List<RefereeVo> list = bizAccLogMapper.pageReferee(reqVo);
        return list;
    }

    @Override
    public int userRefereeCount(RefereeListReqVo reqVo) {
        preProcessListReqVo(reqVo);

        return bizAccLogMapper.countReferee(reqVo);
    }

    @Override
    public ReferrerStaVo userRefereeSta(RefereeStaReqVo reqVo) {
        preProcessStaReqVo(reqVo);
        ReferrerStaVo sta = bizAccLogMapper.refereeSta(reqVo);
        SumAccAmountVo sunAmountVo = new SumAccAmountVo();
        sunAmountVo.setAccountId(reqVo.getAccountId());
        sunAmountVo.setOpType(AccOpType.USER_REF_WITHDRAW.getValue());
        BigDecimal sum = bizAccLogMapper.sumAmount(sunAmountVo);
        sta.setTotalWithdraw(sum);
        return sta;
    }

    @Override
    public List<UserWithdrawVo> referrerWithdrawPage(ReferrerWithdrawPageReqVo reqVo) {
        preProcessWithdrawReqVo(reqVo);

        List<UserWithdrawVo> list = bizAccLogMapper.pageReferrerWithdraw(reqVo);
        return list;
    }

    @Override
    public int referrerWithdrawCount(ReferrerWithdrawPageReqVo reqVo) {
        preProcessWithdrawReqVo(reqVo);

        return bizAccLogMapper.countReferrerWithdraw(reqVo);
    }

    private void preProcessWithdrawReqVo(ReferrerWithdrawPageReqVo reqVo){
        preProcessReqVo(reqVo);
        reqVo.setOpType(AccOpType.USER_REF_WITHDRAW.getValue());
    }

    private void preProcessStaReqVo(RefereeStaReqVo reqVo){
        preProcessReqVo(reqVo);
        reqVo.setOpType(AccOpType.USER_REF_COMMISSION.getValue());
    }

    private void preProcessListReqVo(RefereeListReqVo reqVo){
        preProcessReqVo(reqVo);
        reqVo.setOpType(AccOpType.USER_REF_COMMISSION.getValue());
    }

    private void preProcessReqVo(RefereeBaseVo reqVo){
        SCheckUtil.notEmpty(reqVo.getUserId(),"User ID");
        User user = userMapper.selectByPrimaryKey(reqVo.getUserId());
        if(user == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        AccountVo accountVo = baseAccountService.getAccount(user.getUid(), AccTools.USER_REFERRER);
        if(accountVo == null)
            throw new BaseException(ErrCode.CUR_USER_NOT_REFERRER);

        reqVo.setAccountId(accountVo.getId());
    }


}
