package com.verificer.base_user.handler.impl;

import com.verificer.ErrCode;
import com.verificer.I18nCode;
import com.verificer.base_user.entity.Customer;
import com.verificer.base_user.entity.National;
import com.verificer.base_user.handler.KycEventHandler;
import com.verificer.base_user.mapper.CustomerMapper;
import com.verificer.common.exception.BaseException;
import com.verificer.enums.RegisterType;
import com.verificer.message.service.EmailService;
import com.verificer.message.service.SmsService;
import com.verificer.utils.SStringUtils;
import com.verificer.web.common.i18n.I18nUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KycEventHandlerImpl implements KycEventHandler {
    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    EmailService emailService;

    @Autowired
    SmsService smsService;



    @Override
    public void onKycPass(Long customerId, National national, String userFullName) {
        if(customerId == null)
            throw new BaseException(ErrCode.PARAMS_ERR);

        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        if(customer == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);


        String language = national == null ? "en_US" : I18nUtils.getLanguageByNationCode(national.getNationCode());
        String params[] = null;
        String msg = "";
        if(customer.getRegType() != null){
            if(customer.getRegType() == RegisterType.MOBILE.getValue() )
                msg = I18nUtils.getMessage(I18nCode.CUS_VER_SUC_NOTIFY_SMS_CONTENT,params,language);
            else if(customer.getRegType() == RegisterType.EMAIL.getValue())
                msg = I18nUtils.getMessage(I18nCode.CUS_VER_SUC_NOTIFY_EMAIL_CONTENT,params,language);
            else
                throw new RuntimeException("Not support register type!");

        }


        sendCustomerNotify(customer,msg);



    }



    @Override
    public void onKycReject(Long customerId, National national, String rejectReason) {
        if(customerId == null)
            throw new BaseException(ErrCode.PARAMS_ERR);

        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        if(customer == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        String language = "en_US";
        if(national != null)
            language = I18nUtils.getLanguageByNationCode(national.getNationCode());
        String msg = "";
        String params[] = {rejectReason};

        if(customer.getRegType() == null)
            return;

        if(customer.getRegType() == RegisterType.MOBILE.getValue()){
            msg = I18nUtils.getMessage(I18nCode.CUS_VER_FAIL_NOTIFY_SMS_CONTENT,params,language);
        }
        else if(customer.getRegType() == RegisterType.EMAIL.getValue()){
            msg = I18nUtils.getMessage(I18nCode.CUS_VER_FAIL_NOTIFY_EMAIL_CONTENT,params,language);
        }
        else
            throw new RuntimeException("Not support register type!");

        sendCustomerNotify(customer,msg);
    }

    private void sendCustomerNotify(Customer customer,String msgContent){
        if(customer.getRegType() == null)
            return;
        if(RegisterType.EMAIL.getValue() == customer.getRegType() && !SStringUtils.isEmpty(customer.getEmail())){
            emailService.sendEmail(customer.getEmail(),msgContent);
        }else if(RegisterType.MOBILE.getValue() == customer.getRegType() && !SStringUtils.isEmpty(customer.getMobile())){
            smsService.sendSms(customer.getMobile(),msgContent);
        }else {
            //do nothing
        }
    }
}
