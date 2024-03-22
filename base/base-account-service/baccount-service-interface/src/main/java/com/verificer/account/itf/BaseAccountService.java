package com.verificer.account.itf;

import com.verificer.beans.account.AccountLogQueryVo;
import com.verificer.beans.account.AccountLogVo;
import com.verificer.beans.account.AccountVo;
import com.verificer.beans.rpc.RpcResponse;
import io.swagger.models.auth.In;

import java.math.BigDecimal;
import java.util.List;

public interface BaseAccountService {
    AccountVo getAccount(Long accountId);
    AccountVo getAccount(String customerId , String subName);
    List<AccountVo> getAccounts(String customerId);
    AccountVo createAccountIfNeed(String customerId, String subName,boolean negativeFlag);
    void createAccount(String customerId, List<String> subNameList ,boolean negativeFlag);

    void frozen(Integer type
            , Long accountId
            , Long attachId
            , BigDecimal amount
            , String remark);

    void unFrozen(Integer type
            , Long accountId
            , Long attachId
            , BigDecimal amount
            , String remark);

    void subFrozen(Integer type
            , Long attachId
            , Long accountId
            , BigDecimal amount
            , String remark);

    void addAvailable(Integer type
            ,Long attachId
            , Long accountId
            , BigDecimal amount
            , String remark);

    void subAvailable(Integer type
            , Long attachId
            , Long accountId
            , BigDecimal amount
            , String remark);

    List<AccountLogVo> getAccountLogs(AccountLogQueryVo accountLogQueryVo);
    List<AccountLogVo> getAccountLogsAsc(AccountLogQueryVo accountLogQueryVo);
    int countAccountLogs(AccountLogQueryVo accountLogQueryVo);


    void lockAccounts(Long[] ids);

    AccountVo getAndLock(Long accountId);
}
