package com.verificer.base_user.service;

import com.verificer.beans.*;
import com.verificer.enums.CustomerVerifiedStatus;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by liujinhua on 2021/1/1.
 */
public interface CustomerVerifiedService {

    /**
     * 提交实名认证资料
     * @param customerId 用户id
     * @param nationalId 国家/地区id
     * @param firstName 名字
     * @param lastName 姓氏
     * @param idCardType 证件类型
     * @param idCardNum 证件号码
     * @param idCardFrontPhoto 证件正面照url
     * @param idCardBackPhoto 证件背面照url
     * @param idCardRealPhoto 证件与真人合照url
     */
    void submit(Long customerId,Long nationalId, String firstName, String lastName,IdCardType idCardType,
                String idCardNum, String idCardFrontPhoto,String idCardBackPhoto,
                String idCardRealPhoto);

    /**
     * 提交kyc认证资料-jumio验证方式
     * @param customerId
     * @param birthday
     * @param nationalId
     * @param firstName
     * @param lastName
     */
    JumioTransactionVo submitByJumioType(Long customerId, String birthday, Long nationalId, String firstName, String lastName);

    /**
     * 获取用户的实名认证信息
     * @param customerId
     * @param language 语言
     * @return
     */
    @Nullable
    CustomerVerifiedVo getByCustomerId(Long customerId , String language);


    /**
     *
     * @param customerId 用户id
     * @param status 审核状态
     * @param note 备注
     */
    void verifiedReview(Long customerId, CustomerVerifiedStatus status, String note);


    /**
     * 实名认证列表
     * @param language
     * @param queryVo
     * @return
     */
    List<AdminCustVeriVo> list(String language, CustVeriQueryVo queryVo);

    /**
     * 符合条件的实名认证数
     * @param queryVo
     * @return
     */
    int count(CustVeriQueryVo queryVo);


    /**
     *  成功提交kyc资料到jumio后调用的接口
     * @param tranId
     */
    void jumioKycSubmitSuccess(Long tranId);


    /**
     * 处理jumio的回调
     * @param notifyVo
     */
    void handleJumioNotify(JumioNotifyVo notifyVo);

    /**
     * 检查过时的身份认证
     */
    void processExpireKyc();

    /**
     * 检查超时无通知的Jumio事物
     */
    void processExpireJumioTransaction();
}


