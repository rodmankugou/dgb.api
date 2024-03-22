package com.verificer.biz.biz.service.core.user.impl;

import com.mchange.v2.uid.UidUtils;
import com.verificer.ErrCode;
import com.verificer.account.itf.AccOpType;
import com.verificer.account.itf.AccTools;
import com.verificer.account.itf.BaseAccountService;
import com.verificer.account.itf.exception.BalanceNotEnoughException;
import com.verificer.beans.account.AccountVo;
import com.verificer.beans.num.NumGenerator;
import com.verificer.biz.beans.constants.BizConst;
import com.verificer.biz.beans.enums.ReferrerWithdrawSta;
import com.verificer.biz.beans.vo.user.withdraw.*;
import com.verificer.biz.biz.entity.ReferrerWithdraw;
import com.verificer.biz.biz.entity.User;
import com.verificer.biz.biz.mapper.ReferrerWithdrawMapper;
import com.verificer.biz.biz.service.common.UserCommon;
import com.verificer.biz.biz.service.core.user.ReferrerWithdrawService;
import com.verificer.common.exception.BaseException;
import com.verificer.utils.UuidUtils;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;


@Service
@Transactional(rollbackFor = Exception.class)
public class ReferrerWithdrawServiceImpl implements ReferrerWithdrawService {

    @Autowired
    BaseAccountService baseAccountService;

    @Autowired
    UserCommon userCommon;


    @Autowired
    ReferrerWithdrawMapper mapper;

    private NumGenerator ordNumGenerator = new NumGenerator() {
        @Override
        public boolean isNumExist(String num) {
            return mapper.selectByOrdNum(num) != null;
        }
    };


    @Override
    public List<ReferrerWithdrawVo> referrerWithdrawPage(ReferrerWithdrawPageVo qryVo) {

        return mapper.page(qryVo);
    }

    @Override
    public int referrerWithdrawCount(ReferrerWithdrawPageVo qryVo) {
        return mapper.count(qryVo);
    }

    @Override
    public long referrerWithdrawApply(ReferrerFormVo reqVo) {
        SCheckUtil.notEmpty(reqVo.getUserId(),"UserId");
        SCheckUtil.lgThanAndNotNull(reqVo.getAmount(),false, BigDecimal.ZERO,"Amount");

        User user = userCommon.getById(reqVo.getUserId());
        if(user == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        AccountVo accountVo = baseAccountService.getAccount(user.getUid(), AccTools.USER_REFERRER);
        if(accountVo == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);


        ReferrerWithdraw rw = new ReferrerWithdraw();
        rw.setOrdNum(UuidUtils.newUuid());
        rw.setUserId(user.getId());
        rw.setAmount(reqVo.getAmount());
        rw.setStatus(ReferrerWithdrawSta.WAIT_REVIEW.getValue());
        rw.setCreateTime(System.currentTimeMillis());
        mapper.insert(rw);

        rw.setOrdNum(ordNumGenerator.genNum(BizConst.WITHDRAW_ORD_NUM_PREFIX,18,rw.getId()));
        mapper.updateByPrimaryKey(rw);

        try {
            baseAccountService.frozen(
                    AccOpType.USER_REF_WITHDRAW_FROZEN.getValue(),
                    accountVo.getId(),
                    rw.getId(),
                    rw.getAmount(),
                    "引荐人申请提现-冻结"
                    );
        } catch (BalanceNotEnoughException e) {
            throw new BaseException(ErrCode.ACC_BALANCE_NOT_ENOUGH);
        }
        return rw.getId();
    }

    @Override
    public void referrerWithdrawReview(ReferrerReviewVo reqVo) {
        SCheckUtil.notEmpty(reqVo.getStaffId(),"Staff Id");
        SCheckUtil.notEmpty(reqVo.getStaffName(),"Staff Name");
        SCheckUtil.notEmpty(reqVo.getId(),"ID");
        SCheckUtil.notEmpty(reqVo.getPassFlag(),"Pass Flag");
        if(!reqVo.getPassFlag())
            SCheckUtil.notEmpty(reqVo.getRemark(),"Remark");

        ReferrerWithdraw rw = mapper.getAndLock(reqVo.getId());
        if(rw == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        if(ReferrerWithdrawSta.WAIT_REVIEW.getValue() != rw.getStatus())
            throw new BaseException(ErrCode.REF_WITHDRAW_CAN_NOT_REVIEW);

        rw.setStatus(reqVo.getPassFlag() ? ReferrerWithdrawSta.PASS.getValue() :  ReferrerWithdrawSta.REJECT.getValue());
        rw.setRemark(reqVo.getRemark());
        rw.setReviewStaffId(reqVo.getStaffId());
        rw.setReviewStaffName(reqVo.getStaffName());
        rw.setReviewTime(System.currentTimeMillis());
        mapper.updateByPrimaryKeySelective(rw);
    }

    @Override
    public void referrerWithdrawTransfer(ReferrerTransferVo reqVo) {
        SCheckUtil.notEmpty(reqVo.getStaffId(),"Staff Id");
        SCheckUtil.notEmpty(reqVo.getStaffName(),"Staff Name");
        SCheckUtil.notEmpty(reqVo.getId(),"ID");
        SCheckUtil.notEmpty(reqVo.getCertificateImg(),"Certificate Img");


        ReferrerWithdraw rw = mapper.getAndLock(reqVo.getId());
        if(rw == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        if(ReferrerWithdrawSta.PASS.getValue() != rw.getStatus())
            throw new BaseException(ErrCode.REF_WITHDRAW_CAN_NOT_TRANSFER);

        rw.setStatus(ReferrerWithdrawSta.TRANSFER_FIN.getValue() );
        rw.setTransferStaffId(reqVo.getStaffId());
        rw.setTransferStaffName(reqVo.getStaffName());
        rw.setTransferTime(System.currentTimeMillis());
        mapper.updateByPrimaryKeySelective(rw);

        User user = userCommon.getById(rw.getUserId());
        if(user == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        AccountVo accountVo = baseAccountService.getAccount(user.getUid(),AccTools.USER_REFERRER);
        baseAccountService.subFrozen(
                AccOpType.USER_REF_WITHDRAW.getValue(),
                rw.getId(),
                accountVo.getId(),
                rw.getAmount(),
                "引荐人提现");

    }
}
