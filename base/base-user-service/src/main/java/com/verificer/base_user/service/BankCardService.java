package com.verificer.base_user.service;

import com.verificer.beans.bankcard.BackCardAddReqVo;
import com.verificer.beans.bankcard.BankcardVo;

import java.util.List;

/**
 * Created by 35336 on 2020/12/27.
 */
public interface BankCardService {
    /**
     * 获取用户的银行卡列表
     * @param customerId 用户id
     * @return
     */
    List<BankcardVo> listCustomerBankcards(Long customerId);

    /**
     * 添加银行卡
     * @param customerId
     * @param reqVo
     */
    void addBankcard(Long customerId,BackCardAddReqVo reqVo);

    /**
     * 删除银行卡
     * @param customerId 银行卡所属用户id
     * @param cardId 银行卡id
     */
    void deleteBankcard(Long customerId,Long cardId);
}
