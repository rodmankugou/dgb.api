package com.verificer.base_user.service;

public interface EmailCustomerActivationService {

    /**
     *
     * @param customerId
     * @param language
     * @param actiUrl
     * @return 返回验证码
     */
    String sendActivationCode(Long customerId,String actiUrl,String language);

    /**
     *
     * @param code
     * @return 返回改验证码要激活的用户id,否则报错
     */
    Long verify(String code);

    /**
     * 根据email获取用户的激活码
     * @param email
     * @return
     */
    String getActivationCode(String email);
}
