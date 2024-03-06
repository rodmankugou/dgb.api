package com.verificer.base_user.service.impl;

import com.verificer.ErrCode;
import com.verificer.base_user.entity.Customer;
import com.verificer.base_user.entity.CustomerBankcard;
import com.verificer.base_user.mapper.CustomerBankcardMapper;
import com.verificer.base_user.mapper.CustomerMapper;
import com.verificer.base_user.service.BankCardService;
import com.verificer.beans.CustomerVo;
import com.verificer.beans.bankcard.BackCardAddReqVo;
import com.verificer.beans.bankcard.BankcardVo;
import com.verificer.common.exception.BaseException;
import com.verificer.enums.IdInfoStatus;
import com.verificer.utils.SBeanUtils;
import com.verificer.utils.SStringUtils;
import com.verificer.web.common.response.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 35336 on 2020/12/27.
 */
@Service("bankCardService")
public class BankcardServiceImpl implements BankCardService {

    @Autowired
    CustomerBankcardMapper customerBankcardMapper;

    @Autowired
    CustomerMapper customerMapper;

    /**
     * 获取用户的银行卡列表
     * @return
     */
    @Override
    public List<BankcardVo> listCustomerBankcards(Long customerId) {
        if(customerId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        List<BankcardVo> voList = new ArrayList<>();
        List<CustomerBankcard> cardList = customerBankcardMapper.selectByCustomerId(customerId);
        for(CustomerBankcard card : cardList){
            BankcardVo vo = new BankcardVo();
            SBeanUtils.copyProperties2(card,vo);
            voList.add(vo);
        }
        return voList;
    }

    /**
     * 添加银行卡
     * @param customerId
     * @param reqVo
     */
    public void addBankcard(Long customerId,BackCardAddReqVo reqVo){
        if(customerId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(SStringUtils.isAnyEmpty(reqVo.getBankName(),reqVo.getSubBankName(),reqVo.getCardNumber()
            ,reqVo.getSwift(), reqVo.getCardOwner())){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        List<CustomerBankcard> cardList = customerBankcardMapper.selectByCustomerId(customerId);
        if(cardList!=null && cardList.size()>=10) {
            throw new BaseException(ErrCode.BANK_LIMIT_LIMIT);
        }

        if(cardList!=null&&cardList.size()>0){
            for(CustomerBankcard card : cardList){
                if(card.getCardNumber().equals(reqVo.getCardNumber())){
                    throw new BaseException(ErrCode.USER_BANK_CARD_EXITING);
                }
            }
        }

        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        if (!customer.getEnable()) {
            throw new BaseException(ErrCode.USER_ACCOUNT_FROZEN);
        }

        if (!customer.getIdInfoStatus().equals(IdInfoStatus.PASS.getValue())) {
            throw new BaseException(ErrCode.USER_NOT_AUTH_ID_CARD);
        }

        CustomerBankcard card = new CustomerBankcard();
        card.setBankName(reqVo.getBankName());
        card.setCardNumber(reqVo.getCardNumber());
        card.setSubBankName(reqVo.getSubBankName());
        card.setCreateTime(System.currentTimeMillis());
        card.setCustomerId(customerId);
        if(!SStringUtils.isEmpty(customer.getRealName())){
            card.setCardOwner(customer.getRealName());
        }else {
            card.setCardOwner(reqVo.getCardOwner());
        }
        card.setRoutingNumber(reqVo.getRoutingNumber());
        card.setSwift(reqVo.getSwift());
        card.setStatus(0);
        customerBankcardMapper.insertSelective(card);
    }

    /**
     * 删除银行卡
     * @param customerId 银行卡所属用户id
     * @param cardId 银行卡id
     */
    @Override
    public void deleteBankcard(Long customerId, Long cardId) {
        if(cardId == null || customerId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        customerBankcardMapper.delCustomerCardById(customerId,cardId);
    }
}
