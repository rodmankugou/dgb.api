package com.verificer.base_user.service.impl;

import com.verificer.ErrCode;
import com.verificer.I18nCode;
import com.verificer.base_user.entity.Customer;
import com.verificer.base_user.entity.CustomerActivation;
import com.verificer.base_user.mapper.CustomerActivationMapper;
import com.verificer.base_user.mapper.CustomerMapper;
import com.verificer.base_user.service.BaseCustomerService;
import com.verificer.base_user.service.EmailCustomerActivationService;
import com.verificer.common.exception.BaseException;
import com.verificer.message.service.EmailService;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.UuidUtils;
import com.verificer.web.common.i18n.I18nUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("emailCustomerActivationService")
public class EmailCustomerActivationServiceImpl implements EmailCustomerActivationService {
    @Autowired
    CustomerActivationMapper customerActivationMapper;

    @Autowired
    EmailService emailService;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    BaseCustomerService customerService;

    @Override
    public String sendActivationCode(Long customerId,String actiUrl,String lanuage) {
        if(customerId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        String code = UuidUtils.newUuid();

        //使当前用户的所有激活码失效
        customerActivationMapper.setDisableByCustomerId(customerId);


        CustomerActivation ca = new CustomerActivation();
        ca.setCustomerId(customerId);
        ca.setActivationCode(code);
        ca.setEnable(true);
        ca.setCreateTime(System.currentTimeMillis());
        customerActivationMapper.insertSelective(ca);

        Customer customer = customerMapper.selectByPrimaryKey(customerId);



        String subject = I18nUtils.getMessage(I18nCode.ACTIVATION_MAIL_SUBJECT,null,lanuage);
        actiUrl = actiUrl.replace("#code",code);
        actiUrl = actiUrl.replace("#email",customer.getEmail());
        String content = I18nUtils.getMessage(I18nCode.ACTIVATION_MAIL_CONTENT,null,lanuage);
        content = content.replaceAll("#activeUrl",actiUrl);
        emailService.sendEmail(customer.getEmail(), subject,content);

        return code;
    }

    @Override
    public Long verify(String code) {
        if(SStringUtils.isEmpty(code)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        CustomerActivation ca = customerActivationMapper.selectByCode(code);
        if(ca == null || !ca.getEnable()){
            throw new BaseException(ErrCode.ILLEGAL_ACTIVATION_LINK);
        }



        if((System.currentTimeMillis() - ca.getCreateTime()) > 30 * 60 * 1000L){
            throw new BaseException(ErrCode.ACTIVATION_LINK_TIME_OUT);
        }

        customerActivationMapper.setDisableByCustomerId(ca.getCustomerId());

        return ca.getCustomerId();

    }

    @Override
    public String getActivationCode(String email) {
        Customer customer = customerMapper.selectByEmail(email);
        if(customer == null)
            throw new BaseException(ErrCode.USER_NOT_EXIST);

        CustomerActivation ca = customerActivationMapper.selectLastByCustomerId(customer.getId());
        if(ca == null)
            return null;


        return ca.getActivationCode();
    }
}
