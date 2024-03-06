package com.verificer.base_user.service.impl;

import com.verificer.base_user.entity.Customer;
import com.verificer.base_user.entity.CustomerVerified;
import com.verificer.base_user.entity.JumioTransaction;
import com.verificer.base_user.mapper.CustomerMapper;
import com.verificer.base_user.mapper.CustomerVerifiedMapper;
import com.verificer.base_user.mapper.JumioTransactionMapper;
import com.verificer.base_user.service.CustomerVerifiedHandler;
import com.verificer.enums.CustomerVerifiedStatus;
import com.verificer.enums.IdInfoStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerVerifiedHandlerImpl implements CustomerVerifiedHandler {
    @Autowired
    CustomerVerifiedMapper customerVerifiedMapper;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    JumioTransactionMapper jumioTransactionMapper;


    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    @Override
    public void jumioVerifyFailed(CustomerVerified customerVerified, String jumioRemark,JumioTransaction transaction){
        //更改transaction状态
        transaction.setHasCallback(true);
        jumioTransactionMapper.updateByPrimaryKey(transaction);

        customerVerified.setStatus(CustomerVerifiedStatus.NOT_PASS.getValue());
        customerVerified.setJumioRemark(jumioRemark);
        customerVerified.setRejectReasonMsg(jumioRemark);
        customerVerifiedMapper.updateByPrimaryKey(customerVerified);

        Customer temp = new Customer();
        temp.setId(customerVerified.getCustomerId());
        temp.setIdInfoStatus(IdInfoStatus.NOT_PASS.getValue());
        customerMapper.updateByPrimaryKeySelective(temp);
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW )
    @Override
    public void tranManualReview(CustomerVerified customerVerified,String jumioRemark,JumioTransaction transaction){

        //更改transaction状态
        transaction.setHasCallback(true);
        jumioTransactionMapper.updateByPrimaryKey(transaction);

        customerVerified.setStatus(CustomerVerifiedStatus.MANUAL_REVIEW.getValue());
        customerVerified.setJumioRemark(jumioRemark);
        customerVerifiedMapper.updateByPrimaryKey(customerVerified);
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW )
    @Override
    public void kycSuccess(CustomerVerified customerVerified,JumioTransaction transaction){

        //更改transaction状态
        transaction.setHasCallback(true);
        jumioTransactionMapper.updateByPrimaryKey(transaction);

        customerVerified.setStatus(CustomerVerifiedStatus.PASS.getValue());
        customerVerifiedMapper.updateByPrimaryKey(customerVerified);


        Customer temp = new Customer();
        temp.setId(customerVerified.getCustomerId());
        temp.setIdInfoStatus(IdInfoStatus.PASS.getValue());
        customerMapper.updateByPrimaryKeySelective(temp);
    }
}
