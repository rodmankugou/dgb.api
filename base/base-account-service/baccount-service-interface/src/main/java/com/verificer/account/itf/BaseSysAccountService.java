package com.verificer.account.itf;

import com.verificer.beans.SysAccountAddVo;
import com.verificer.beans.SysAccountQueryVo;
import com.verificer.beans.SysAccountVo;
import com.verificer.beans.account.AccountVo;

import java.math.BigDecimal;
import java.util.List;

public interface BaseSysAccountService {

    public List<SysAccountVo> all(SysAccountQueryVo queryVo);

    void addAvailable(Integer type
            ,Long attachId
            , Long accountId
            , BigDecimal amount
            , String remark);

    SysAccountVo getAccount(String subName);

    SysAccountVo createAccount(String subName);


    void lockAccounts(Long[] ids);

    SysAccountVo getAndLock(Long accountId);
}
