package com.verificer.account.provider.service;

import com.verificer.ErrCode;
import com.verificer.account.itf.BaseSysAccountService;
import com.verificer.account.provider.BaseAccountUtils;
import com.verificer.account.provider.entity.Account;
import com.verificer.account.provider.entity.SysAccount;
import com.verificer.account.provider.entity.SysAccountLog;
import com.verificer.account.provider.mapper.SysAccountLogMapper;
import com.verificer.account.provider.mapper.SysAccountMapper;
import com.verificer.beans.SysAccountAddVo;
import com.verificer.beans.SysAccountQueryVo;
import com.verificer.beans.SysAccountVo;
import com.verificer.beans.account.AccountVo;
import com.verificer.common.exception.BaseException;
import com.verificer.utils.SBeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class BaseSysAccountServiceImpl implements BaseSysAccountService {
    private static final Log logger = LogFactory.getLog(BaseSysAccountServiceImpl.class);

    @Autowired
    SysAccountMapper sysAccountMapper;

    @Autowired
    SysAccountLogMapper sysAccountLogMapper;

    @Override
    public List<SysAccountVo> all(SysAccountQueryVo queryVo) {
        List<SysAccount> list = sysAccountMapper.list(queryVo);

        return toVoList(list);
    }



    private List<SysAccountVo> toVoList(List<SysAccount> beans){
        List<SysAccountVo> rst = new LinkedList<>();
        for(SysAccount bean : beans){
            rst.add(toVo(bean));
        }
        return rst;
    }

    private SysAccountVo toVo(SysAccount bean) {
        if(bean == null)
            return null;
        SysAccountVo vo = new SysAccountVo();
        SBeanUtils.copyProperties2(bean,vo);
        vo.setCurrency(BaseAccountUtils.getCurrencyName(vo.getSubName()));
        return vo;
    }

    @Override
    public void addAvailable(Integer type, Long attachId, Long accountId, BigDecimal amount, String remark) {
        SysAccount account = sysAccountMapper.selectByPrimaryKeyForUpdate(accountId);
        if(account == null)
            throw new BaseException(ErrCode.SERVER_ERROR);

        BigDecimal beforeAvail = account.getAvailableAmount();
        BigDecimal beforeFrozen = account.getFrozenAmount();

        account.setAvailableAmount(account.getAvailableAmount().add(amount));
        account.setUpdateTime(System.currentTimeMillis());

        if (account.getAvailableAmount().compareTo(BigDecimal.ZERO) < 0
                || account.getFrozenAmount().compareTo(BigDecimal.ZERO) < 0 ){
            throw new IllegalStateException("Insufficient balance ! ");
        }

        sysAccountMapper.updateByPrimaryKey(account);
        this.insertAccountLog(
                accountId,
                account.getSubName(),
                type,
                amount,
                attachId,
                beforeAvail,
                beforeFrozen,
                account.getAvailableAmount(),
                account.getFrozenAmount(),
                remark
        );

    }


    @Override
    public SysAccountVo getAccount(String subName){
        SysAccount account = sysAccountMapper.selectBySubName(subName);
        return toVo(account);
    }

    @Override
    public SysAccountVo createAccount(String subName) {
        SysAccountVo old = getAccount(subName);
        if(old != null)
            return old;

        SysAccount account = new SysAccount();
        account.setSubName(subName);
        account.setAvailableAmount(BigDecimal.ZERO);
        account.setFrozenAmount(BigDecimal.ZERO);
        account.setCreateTime(System.currentTimeMillis());
        account.setUpdateTime(System.currentTimeMillis());
        sysAccountMapper.insertSelective(account);
        return getAccount(account.getSubName());
    }



    protected void insertAccountLog(
                                    Long accountId,
                                    String subName,
                                    Integer opType,
                                    BigDecimal opAmount,
                                    Long attachId,
                                    BigDecimal beforeAvailable,
                                    BigDecimal beforeFrozen,
                                    BigDecimal afterAvailable,
                                    BigDecimal afterFrozen,
                                    String remark){

        SysAccountLog al = new SysAccountLog();
        al.setAccountId(accountId);
        al.setSubName(subName);
        al.setOpType(opType);
        al.setOpAmount(opAmount);
        al.setAttachid(attachId);
        al.setBeforeAvailable(beforeAvailable);
        al.setAfterAvailable(afterAvailable);
        al.setBeforeFrozen(beforeFrozen);
        al.setAfterFrozen(afterFrozen);
        al.setRemark(remark);
        al.setCreateTime(System.currentTimeMillis());

        sysAccountLogMapper.insertSelective(al);

    }

    @Override
    public void lockAccounts(Long[] ids) {
        if(ids.length == 0)
            return;

        List<Long> idList = new LinkedList<>();
        for(Long id : ids)
            idList.add(id);


        sysAccountMapper.selectByIdForUpdate(idList);
    }

    @Override
    public SysAccountVo getAndLock(Long accountId) {
        if(accountId == null)
            throw new BaseException(ErrCode.UNKNOW_ERR);
        SysAccount a = sysAccountMapper.selectByPrimaryKeyForUpdate(accountId);
        return toVo(a);
    }

}
