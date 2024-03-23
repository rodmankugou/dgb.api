package com.verificer.account.provider.service;

import com.verificer.ErrCode;
import com.verificer.account.itf.BaseAccountService;
import com.verificer.account.itf.exception.BalanceNotEnoughException;
import com.verificer.account.provider.entity.Account;
import com.verificer.account.provider.entity.AccountLog;
import com.verificer.account.provider.exception.RecordNotExist;
import com.verificer.account.provider.mapper.AccountLogMapper;
import com.verificer.account.provider.mapper.AccountMapper;
import com.verificer.beans.account.AccountLogQueryVo;
import com.verificer.beans.account.AccountLogVo;
import com.verificer.beans.account.AccountVo;
import com.verificer.common.exception.BaseException;
import com.verificer.utils.reflect.SBeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Service
public class BaseAccountServiceImpl implements BaseAccountService {
    private static final Log logger = LogFactory.getLog(BaseAccountServiceImpl.class);


    @Autowired
    AccountMapper accountMapper;
    @Autowired
    AccountLogMapper accountLogMapper;

    protected AccountLogVo toLogVo(AccountLog accountLog) {
        if(accountLog == null)
            return null;
        AccountLogVo vo = new AccountLogVo();
        SBeanUtils.copyProperties2(accountLog,vo);
        return vo;
    }

    protected List<AccountLogVo> toLogVoList(List<AccountLog> accountLogList) {
        List<AccountLogVo> voList = new LinkedList<>();
        for(AccountLog accountLog : accountLogList)
            voList.add(toLogVo(accountLog));
        return voList;
    }


    protected AccountVo toVo(Account account){
        if(account == null)
            return null;
        AccountVo vo = new AccountVo();
        SBeanUtils.copyProperties2(account,vo);
        return vo;
    }

    protected List<AccountVo> toVoList(List<Account> accountList){
        List<AccountVo> voList = new LinkedList<>();
        for(Account account : accountList)
            voList.add(toVo(account));
        return voList;
    }


    /**
     * 记录账户明细
     * @param customerId
     * @param accountId
     * @param subName
     * @param opType
     * @param opAmount
     * @param attachId
     * @param beforeAvailable
     * @param beforeFrozen
     * @param afterAvailable
     * @param afterFrozen
     * @param remark
     */
    protected void insertAccountLog(String customerId,
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

        AccountLog al = new AccountLog();
        al.setCustomerId(customerId);
        al.setAccountId(accountId);
        al.setSubName(subName);
        al.setOpType(opType);
        al.setOpAmount(opAmount);
        al.setAttachId(attachId);
        al.setBeforeAvailable(beforeAvailable);
        al.setAfterAvailable(afterAvailable);
        al.setBeforeFrozen(beforeFrozen);
        al.setAfterFrozen(afterFrozen);
        al.setRemark(remark);
        al.setCreateTime(System.currentTimeMillis());

        accountLogMapper.insertSelective(al);

    }

    /**
     * 获取账户
     * @param accountId
     * @return
     */
    @Override
    public AccountVo getAccount(Long accountId){
        Account account = accountMapper.selectByPrimaryKey(accountId);
        return toVo(account);
    }


    /**
     * 获取某个用户的指定账户
     *
     * @param customerId
     * @param subName
     * @return
     */
    @Override
    public AccountVo getAccount(String customerId,String subName){
        Account account = accountMapper.selectByCustomerIdAndSubName(customerId,subName);
        return toVo(account);
    }


    /**
     * 获取某个用户的所有账号
     * @param customerId
     * @return
     */
    @Override
    public  List<AccountVo> getAccounts( String customerId ){
        List<Account> accountList = accountMapper.selectByCustomerId(customerId);
        return toVoList(accountList);
    }




    /**
     * 创建新账户
     * @param customerId
     * @param subName 如果存在则返回，否则创建一个账户再返回
     * @param negativeFlag 该账户的余额可否为负
     */
    @Override
    public AccountVo createAccountIfNeed(String customerId, String subName,boolean negativeFlag){
        if(customerId == null)
            throw new BaseException(ErrCode.PARAMS_ERR);
        AccountVo accountvo = getAccount(customerId,subName);
        if(accountvo != null)
            return accountvo;


        Account account = new Account();
        account.setCustomerId(customerId);
        account.setSubName(subName);
        account.setAvailableAmount(BigDecimal.ZERO);
        account.setFrozenAmount(BigDecimal.ZERO);
        account.setCreateTime(System.currentTimeMillis());
        account.setUpdateTime(System.currentTimeMillis());
        account.setNegativeFlag(negativeFlag);
        accountMapper.insertSelective(account);
        Account newOne = accountMapper.selectByPrimaryKey(account.getId());
        return toVo(newOne);
    }

    /**
     * 创建新账户
     * @param customerId
     * @param subNameList
     */
    @Override
    public void createAccount(String customerId,List<String> subNameList,boolean negativeFlag){
        if(subNameList == null )
            throw new BaseException(ErrCode.PARAMS_ERR);
        for (String subName : subNameList){
           createAccountIfNeed(customerId,subName,negativeFlag);
        }
    }


    /**
     * 冻结账户的部分金额
     * @param type
     * @param accountId
     * @param attachId
     * @param amount
     * @param remark
     */
    @Override
    public void frozen(Integer type
            , Long accountId
            , Long attachId
            , BigDecimal amount
            , String remark)   {

        Account account = accountMapper.selectByPrimaryKeyForUpdate(accountId);
        if (account == null){
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        }

        BigDecimal beforeAvail = account.getAvailableAmount();
        BigDecimal beforeFrozen = account.getFrozenAmount();

        account.setAvailableAmount(account.getAvailableAmount().subtract(amount));
        account.setFrozenAmount(account.getFrozenAmount().add(amount));
        account.setUpdateTime(System.currentTimeMillis());

        if ((!account.getNegativeFlag() && account.getAvailableAmount().compareTo(BigDecimal.ZERO) < 0)
                || account.getFrozenAmount().compareTo(BigDecimal.ZERO) < 0 ){
            throw new BalanceNotEnoughException();
        }

        accountMapper.updateByPrimaryKey(account);
        this.insertAccountLog(account.getCustomerId(),
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

    /**
     * 解冻账户的部分金额
     * @param type
     * @param accountId
     * @param attachId
     * @param amount
     * @param remark
     */
    @Override
    public void unFrozen(Integer type
            , Long accountId
            , Long attachId
            , BigDecimal amount
            , String remark) {


        Account account = accountMapper.selectByPrimaryKeyForUpdate(accountId);
        if (account == null){
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        }

        BigDecimal beforeAvail = account.getAvailableAmount();
        BigDecimal beforeFrozen = account.getFrozenAmount();

        account.setAvailableAmount(account.getAvailableAmount().add(amount));
        account.setFrozenAmount(account.getFrozenAmount().subtract(amount));
        account.setUpdateTime(System.currentTimeMillis());


        if ((!account.getNegativeFlag() && account.getAvailableAmount().compareTo(BigDecimal.ZERO) < 0)
                || account.getFrozenAmount().compareTo(BigDecimal.ZERO) < 0 ){
            throw new IllegalStateException("Insufficient balance ! ");
        }

        accountMapper.updateByPrimaryKey(account);
        this.insertAccountLog(account.getCustomerId(),
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

    /**
     * 增加账户的可用金额
     * @param type
     * @param attachId
     * @param accountId
     * @param amount
     * @param remark
     * @throws RecordNotExist
     */
    @Override
    public void addAvailable(Integer type
            ,Long attachId
            , Long accountId
            , BigDecimal amount
            , String remark) {

        Account account = accountMapper.selectByPrimaryKeyForUpdate(accountId);
        if (account == null){
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        }


        BigDecimal beforeAvail = account.getAvailableAmount();
        BigDecimal beforeFrozen = account.getFrozenAmount();

        account.setAvailableAmount(account.getAvailableAmount().add(amount));
        account.setUpdateTime(System.currentTimeMillis());

        if ((!account.getNegativeFlag() && account.getAvailableAmount().compareTo(BigDecimal.ZERO) < 0)
                || account.getFrozenAmount().compareTo(BigDecimal.ZERO) < 0 ){
            throw new IllegalStateException("Insufficient balance ! ");
        }

        accountMapper.updateByPrimaryKey(account);
        this.insertAccountLog(account.getCustomerId(),
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

    /**
     * 扣减账户的可用金额
     * @param type
     * @param attachId
     * @param accountId
     * @param amount
     * @param remark
     * @throws RecordNotExist
     */
    @Override
    public void subAvailable(Integer type
            , Long attachId
            , Long accountId
            , BigDecimal amount
            , String remark)   {
        Account account = accountMapper.selectByPrimaryKeyForUpdate(accountId);
        if (account == null){
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        }


        BigDecimal beforeAvail = account.getAvailableAmount();
        BigDecimal beforeFrozen = account.getFrozenAmount();

        account.setAvailableAmount(account.getAvailableAmount().subtract(amount));
        account.setUpdateTime(System.currentTimeMillis());



        if ((!account.getNegativeFlag() && account.getAvailableAmount().compareTo(BigDecimal.ZERO) < 0)
                || account.getFrozenAmount().compareTo(BigDecimal.ZERO) < 0 ){
            throw new BalanceNotEnoughException();
        }

        accountMapper.updateByPrimaryKey(account);
        this.insertAccountLog(account.getCustomerId(),
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


    /**
     * 扣减冻结金额
     * @param type
     * @param attachId
     * @param accountId
     * @param amount
     * @param remark
     * @return
     * @throws RecordNotExist
     */
    @Override
    public void subFrozen(Integer type
            , Long attachId
            , Long accountId
            , BigDecimal amount
            , String remark){
        Account account = accountMapper.selectByPrimaryKeyForUpdate(accountId);
        if (account == null){
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        }


        BigDecimal beforeAvail = account.getAvailableAmount();
        BigDecimal beforeFrozen = account.getFrozenAmount();

        account.setFrozenAmount(account.getFrozenAmount().subtract(amount));
        account.setUpdateTime(System.currentTimeMillis());

        if ((!account.getNegativeFlag() && account.getAvailableAmount().compareTo(BigDecimal.ZERO) < 0)
                || account.getFrozenAmount().compareTo(BigDecimal.ZERO) < 0 ){
            throw new IllegalStateException("Insufficient balance ! ");
        }

        accountMapper.updateByPrimaryKey(account);
        this.insertAccountLog(account.getCustomerId(),
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


    /**
     * 分页方式获取账户明细
     * @param accountLogQueryVo
     * @return
     */
    @Override
    public  List<AccountLogVo> getAccountLogs(AccountLogQueryVo accountLogQueryVo){
        List<AccountLog> logs = accountLogMapper.page(accountLogQueryVo);
        return toLogVoList(logs);
    }

    @Override
    public List<AccountLogVo> getAccountLogsAsc(AccountLogQueryVo accountLogQueryVo) {
        List<AccountLog> logs = accountLogMapper.pageAsc(accountLogQueryVo);
        return toLogVoList(logs);    }

    @Override
    public int countAccountLogs(AccountLogQueryVo accountLogQueryVo) {
        return accountLogMapper.count(accountLogQueryVo);
    }

    @Override
    public void lockAccounts(Long[] ids) {
        if(ids.length == 0)
            return;

        List<Long> idList = new LinkedList<>();
        for(Long id : ids)
            idList.add(id);

        accountMapper.selectByIdsForUpdate(idList);
    }

    @Override
    public AccountVo getAndLock(Long accountId) {
        if(accountId == null)
            throw new BaseException(ErrCode.UNKNOW_ERR);
        Account a = accountMapper.selectByPrimaryKeyForUpdate(accountId);
        return toVo(a);
    }
}
