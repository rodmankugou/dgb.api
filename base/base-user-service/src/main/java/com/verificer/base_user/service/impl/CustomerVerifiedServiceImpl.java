package com.verificer.base_user.service.impl;

import com.verificer.ErrCode;
import com.verificer.base_user.config.UsrSrvConfig;
import com.verificer.base_user.entity.Customer;
import com.verificer.base_user.entity.CustomerVerified;
import com.verificer.base_user.entity.JumioTransaction;
import com.verificer.base_user.entity.National;
import com.verificer.base_user.handler.KycEventHandler;
import com.verificer.base_user.mapper.CustomerMapper;
import com.verificer.base_user.mapper.CustomerVerifiedMapper;
import com.verificer.base_user.mapper.JumioTransactionMapper;
import com.verificer.base_user.mapper.NationalMapper;
import com.verificer.base_user.service.CustomerVerifiedHandler;
import com.verificer.base_user.service.CustomerVerifiedService;
import com.verificer.base_user.service.NationalService;
import com.verificer.beans.*;
import com.verificer.common.exception.BaseException;
import com.verificer.enums.CustomerVerifiedStatus;
import com.verificer.enums.IdInfoStatus;
import com.verificer.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by liujinhua on 2021/1/1.
 */
@Service("customerVerifiedService")
@Transactional(rollbackFor = Exception.class)
public class CustomerVerifiedServiceImpl implements CustomerVerifiedService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerVerifiedService.class);


    @Autowired
    NationalService nationalService;

    @Autowired
    CustomerVerifiedMapper customerVerifiedMapper;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    NationalMapper nationalMapper;


    @Autowired
    JumioTransactionMapper jumioTransactionMapper;

    @Autowired
    UsrSrvConfig usrSrvConfig;

    @Autowired
    CustomerVerifiedHandler customerVerifiedHandler;

    @Autowired
    KycEventHandler kycEventHandler;



    /**
     * 提交实名认证资料
     *
     * @param customerId       用户id
     * @param nationalId       国家/地区id
     * @param firstName        名字
     * @param lastName         姓氏
     * @param idCardType       证件类型
     * @param idCardNum        证件号码
     * @param idCardFrontPhoto 证件正面照url
     * @param idCardBackPhoto  证件背面照url
     * @param idCardRealPhoto  证件与真人合照url
     */
    @Override
    public void submit(Long customerId,Long nationalId, String firstName, String lastName,IdCardType idCardType,
                       String idCardNum, String idCardFrontPhoto, String idCardBackPhoto,
                       String idCardRealPhoto) {


        if (SStringUtils.isAnyEmpty(firstName, lastName, idCardNum)) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        if (nationalId != null && !nationalService.checkExistAndEnable(nationalId)) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        boolean isAdd = false;
        CustomerVerified cv = customerVerifiedMapper.selectByCustomerId(customerId);
        if(cv == null){
            isAdd = true;
            cv = new CustomerVerified();
            cv.setCreateTime(System.currentTimeMillis());
        }
        cv.setNationalId(nationalId);
        cv.setCustomerId(customerId);
        cv.setFirstName(firstName);
        cv.setLastName(lastName);
        cv.setIdCardNum(idCardNum);
        cv.setIdCardType(idCardType.getValue());
        cv.setIdCardFrontPhoto(idCardFrontPhoto);
        cv.setIdCardBackPhoto(idCardBackPhoto);
        cv.setIdCardRealPhoto(idCardRealPhoto);
        cv.setStatus(CustomerVerifiedStatus.UNDER_REVIEW.getValue());
        cv.setJumioSubmitTime(System.currentTimeMillis());

        if(isAdd){
            customerVerifiedMapper.insertSelective(cv);
        }else {
            customerVerifiedMapper.updateByPrimaryKey(cv);
        }

        synCusStatusAfterSubmit(customerId);
    }

    private void synCusStatusAfterSubmit(Long customerId){
        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        if(customer == null){
            throw new BaseException(ErrCode.USER_NOT_EXIST);
        }
        Customer ctmp = new Customer();
        ctmp.setId(customerId);
        ctmp.setIdInfoStatus(IdInfoStatus.WAITING.getValue());
        customerMapper.updateByPrimaryKeySelective(ctmp);
    }

    private void afterSubmitKycInfo(Long customerId){
        synCusStatusAfterSubmit(customerId);

        CustomerVerified cv = customerVerifiedMapper.selectByCustomerId(customerId);
        if(cv != null){
            customerVerifiedMapper.deleteByPrimaryKey(cv.getId());
        }

        JumioTransaction jumioTransaction = jumioTransactionMapper.selectLastByOutCustomerId(customerId+"");
        if(jumioTransaction != null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        jumioTransaction.setHasSubmittedToJumio(true);
        jumioTransactionMapper.updateByPrimaryKey(jumioTransaction);

        cv = new CustomerVerified();
        cv.setCustomerId(customerId);
        cv.setStatus(CustomerVerifiedStatus.UNDER_REVIEW.getValue());
        cv.setNationalId(jumioTransaction.getNationalId());
        cv.setFirstName(jumioTransaction.getFirstName());
        cv.setLastName(jumioTransaction.getLastName());
        cv.setBirthday(jumioTransaction.getBirthday());
        cv.setCreateTime(System.currentTimeMillis());
        cv.setJumioSubmitTime(System.currentTimeMillis());

        customerVerifiedMapper.insert(cv);


    }

    @Override
    public JumioTransactionVo submitByJumioType(Long customerId, String birthday, Long nationalId, String firstName, String lastName) {
        if (nationalId == null) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(customerId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(SStringUtils.isEmpty(birthday))
            throw new BaseException(ErrCode.PARAMS_ERR);
        if(SStringUtils.isEmpty(firstName))
            throw new BaseException(ErrCode.PARAMS_ERR);
        if(SStringUtils.isEmpty(lastName))
            throw new BaseException(ErrCode.PARAMS_ERR);


        JumioTransaction transaction = new JumioTransaction();
        transaction.setCustomerId(customerId);
        transaction.setOutCustomerId(customerId+"");
        transaction.setInnerTransactionId(UuidUtils.newUuid());
        transaction.setJumioTransactionId(UuidUtils.newUuid());
        transaction.setBirthday(birthday);
        transaction.setNationalId(nationalId);
        transaction.setFirstName(firstName);
        transaction.setLastName(lastName);
        transaction.setCreateTime(System.currentTimeMillis());
        transaction.setHasSubmittedToJumio(false);
        transaction.setHasCallback(false);
        transaction.setHasProcessTimeOut(false);
        jumioTransactionMapper.insertSelective(transaction);

        JumioTransactionVo vo = new JumioTransactionVo();
        vo.setId(transaction.getId());
        vo.setInnerTransactionId(transaction.getInnerTransactionId());
        vo.setOutCustomerId(transaction.getOutCustomerId());


        return vo;


    }


    /**
     * 获取用户的实名认证信息
     *
     * @param customerId
     * @param language   语言
     * @return
     */
    @Nullable
    public CustomerVerifiedVo getByCustomerId(Long customerId, String language) {
        if(customerId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        if(SStringUtils.isEmpty(language)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        CustomerVerified cv = customerVerifiedMapper.selectByCustomerId(customerId);
        return entityToVo(cv,language);
    }

    private String getUserFullName(National national,String firstName, String lastName){
        String userFullName = "";
        if(national == null)
            return firstName + " " + lastName;
        if(national.getNationCode().equals("CHN")
                || national.getNationCode().equals("TWN")
                || national.getNationCode().equals("HKG")
                || national.getNationCode().equals("VNM")
                || national.getNationCode().equals("KOR")
                || national.getNationCode().equals("MAC"))
            userFullName = lastName+firstName;
        else
            userFullName = firstName + " " + lastName;
        return userFullName;
    }

    @Override
    public void verifiedReview(Long customerId, CustomerVerifiedStatus status, String note) {
        if(customerId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(status == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        CustomerVerified customerVerified = customerVerifiedMapper.selectByCustomerId(customerId);
        if(customerVerified == null){
            throw new BaseException(ErrCode.VERIFY_INFO_NOT_EXIST);
        }

        if(CustomerVerifiedStatus.UNDER_REVIEW.getValue() != customerVerified.getStatus()
            && CustomerVerifiedStatus.MANUAL_REVIEW.getValue() != customerVerified.getStatus()){
            throw new BaseException(ErrCode.VERIFIED_STATUS_CANNOT_REVIEW);
        }

        //修改用户表中的冗余字段
        CustomerVerified tmp = new CustomerVerified();
        tmp.setId(customerVerified.getId());
        tmp.setStatus(status.getValue());
        tmp.setRejectReasonMsg(note);
        customerVerifiedMapper.updateByPrimaryKeySelective(tmp);

        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        National national = nationalMapper.selectByPrimaryKey(customerVerified.getNationalId());

        Customer c = new Customer();
        c.setId(customerId);
        if(CustomerVerifiedStatus.PASS.equals(status)){
            c.setIdInfoStatus(IdInfoStatus.PASS.getValue());

            String userFullName = getUserFullName(national,customerVerified.getFirstName(),customerVerified.getLastName());
            kycEventHandler.onKycPass(customerId,national,userFullName);

        }else {
            c.setIdInfoStatus(IdInfoStatus.NOT_PASS.getValue());
            kycEventHandler.onKycReject(customerId,national,note);

        }
        c.setIdCardNo(customerVerified.getIdCardNum());

        if(customer.getNationalId() != null){
            if(national != null){
                if(national.getCountryCode() == 86 ||
                        national.getCountryCode() == 852 ||
                        national.getCountryCode() == 853 ||
                        national.getCountryCode() == 886){
                    //如果是中国大陆或者港澳台地区的用户
                    c.setRealName(customerVerified.getLastName() + customerVerified.getFirstName());
                }else {
                    c.setRealName( customerVerified.getFirstName() + " " + customerVerified.getLastName());
                }
            }
        }

        customerMapper.updateByPrimaryKeySelective(c);
    }

    @Override
    public List<AdminCustVeriVo> list(String language, CustVeriQueryVo queryVo) {
        List<CustomerVerified> list = customerVerifiedMapper.page(queryVo);
        List<AdminCustVeriVo> voList = new LinkedList<>();
        for(CustomerVerified cv : list){
            AdminCustVeriVo vo = new AdminCustVeriVo();
            vo.setJumioSubmitTime(cv.getJumioSubmitTime() == null ? cv.getCreateTime() : cv.getJumioSubmitTime());
            SBeanUtils.copyProperties2(cv,vo);
            if(cv.getNationalId() != null)
                vo.setNationalName(nationalService.getName(cv.getNationalId(),language));
            Customer customer = customerMapper.selectByPrimaryKey(cv.getCustomerId());
            if(customer != null){
                vo.setEmail(customer.getEmail());
                vo.setMobile(customer.getMobile());

            }

            voList.add(vo);
        }
        return voList;
    }

    private CustomerVerifiedVo entityToVo(CustomerVerified customerVerified,String language){
        if(customerVerified == null)
            return null;

        CustomerVerifiedVo vo = new CustomerVerifiedVo();
        SBeanUtils.copyProperties2(customerVerified,vo);
        if(vo.getNationalId() != null)
            vo.setNationalName(nationalService.getName(vo.getNationalId(),language));
        vo.setRejectReason(customerVerified.getRejectReasonMsg());
        return vo;
    }

    @Override
    public int count(CustVeriQueryVo queryVo) {
        return customerVerifiedMapper.count(queryVo);
    }

    @Override
    public void jumioKycSubmitSuccess(Long tranId) {
        if(tranId == null)
            throw new BaseException(ErrCode.PARAMS_ERR);

        JumioTransaction t = jumioTransactionMapper.selectByPrimaryKey(tranId);
        if(t == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);


        boolean isAdd = false;
        CustomerVerified old = customerVerifiedMapper.selectByCustomerId(t.getCustomerId());
        CustomerVerified cv = new CustomerVerified();;
        if(old == null){
            isAdd = true;
        }else {
            cv.setId(old.getId());
        }

        cv.setCreateTime(System.currentTimeMillis());
        cv.setCustomerId(t.getCustomerId());
        cv.setNationalId(t.getNationalId());
        cv.setBirthday(t.getBirthday());
        cv.setFirstName(t.getFirstName());
        cv.setLastName(t.getLastName());
        cv.setJumioSubmitTime(System.currentTimeMillis());
        cv.setStatus(CustomerVerifiedStatus.UNDER_REVIEW.getValue());

        if(isAdd){
            customerVerifiedMapper.insertSelective(cv);
        }else {
            customerVerifiedMapper.updateByPrimaryKey(cv);
        }

        afterSubmitKycInfo(t.getCustomerId());
    }

    /**
     * 从jumio中下载kyc照片并上传到OSS
     * @return
     */
    private String retrieveAndUploadToOss(String fileUrl){
//        if(SStringUtils.isEmpty(fileUrl))
//            return null;
//
//        String authStr = "Basic "+ Base64Utils.encode(usrSrvConfig.getJumioToken()+":"+usrSrvConfig.getJumioSecret());
//        System.out.println(authStr);
//        Map<String,String> headerMap = new HashMap<>();
//        headerMap.put("Authorization",authStr);
//
//        File file = SFileUtils.downloadHttpFile(fileUrl,".jpg",headerMap);
//
//        String path = HwObsClientUtil.uploadObject2OSS(file,"/jumio");
//        HwObsClientUtil.delteTempFile(file);
//
//        return path;
        return null;
    }

    @Override
    public void handleJumioNotify(JumioNotifyVo notifyVo) {
        String outCustomerId = notifyVo.getCustomerId();
        if(outCustomerId == null)
            throw new BaseException(ErrCode.PARAMS_ERR);

        JumioTransaction transaction = jumioTransactionMapper.selectLastByOutCustomerId(outCustomerId);
        if(transaction == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        Long customerId = transaction.getCustomerId();
        CustomerVerified customerVerified = customerVerifiedMapper.selectByCustomerId(customerId);
        if(customerVerified == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        National national = nationalMapper.selectByPrimaryKey(customerVerified.getNationalId());

        //保存kyc信息
        if(!SStringUtils.isEmpty(notifyVo.getIdExpiry())){
            customerVerified.setIdCardExpireTime(SDateUtil.parse_yyyy_MM_dd(notifyVo.getIdExpiry()).getTime());
        }
        customerVerified.setJumioBizId(notifyVo.getJumioIdScanReference());
        customerVerified.setJumioBirthday(notifyVo.getIdDob());
        customerVerified.setJumioFirstName(notifyVo.getIdFirstName());
        customerVerified.setJumioLastName(notifyVo.getIdLastName());
        customerVerified.setIdCardRealPhoto(retrieveAndUploadToOss(notifyVo.getIdScanImageFace()));
        customerVerified.setIdCardBackPhoto(retrieveAndUploadToOss(notifyVo.getIdScanImageBackside()));
        customerVerified.setIdCardFrontPhoto(retrieveAndUploadToOss(notifyVo.getIdScanImage()));
        customerVerified.setIdCardNum(notifyVo.getIdNumber());
        Integer idCardType = null;
        if("PASSPORT".equals(notifyVo.getIdType()))
            idCardType = IdCardType.PASSPORT.getValue();
        else if("ID_CARD".equals(notifyVo.getIdType()))
            idCardType = IdCardType.ID_CARD.getValue();
        else
            idCardType = null;
        customerVerified.setIdCardType(idCardType);



        //检验不通过
        if(!"APPROVED_VERIFIED".equals(notifyVo.getVerificationStatus())){
            if("DENIED_FRAUD".equals(notifyVo.getVerificationStatus())){
                customerVerifiedHandler.jumioVerifyFailed(customerVerified,"Denied fraud",transaction);
                kycEventHandler.onKycReject(customerId,national,"Denied fraud");

            }else if("DENIED_UNSUPPORTED_ID_TYPE".equals(notifyVo.getVerificationStatus())){
                customerVerifiedHandler.jumioVerifyFailed(customerVerified,"Denied unsupported ID type",transaction);
                kycEventHandler.onKycReject(customerId,national,"Denied unsupported ID type");

            }else if("DENIED_UNSUPPORTED_ID_COUNTRY".equals(notifyVo.getVerificationStatus())){
                customerVerifiedHandler.jumioVerifyFailed(customerVerified,"Denied unsupported ID country",transaction);
                kycEventHandler.onKycReject(customerId,national,"Denied unsupported ID country");

            }else if("ERROR_NOT_READABLE_ID".equals(notifyVo.getVerificationStatus())){
                customerVerifiedHandler.jumioVerifyFailed(customerVerified,"Error, not readable ID",transaction);
                kycEventHandler.onKycReject(customerId,national,"Error, not readable ID");

            }else if("NO_ID_UPLOADED".equals(notifyVo.getVerificationStatus())){
                customerVerifiedHandler.jumioVerifyFailed(customerVerified,"No ID upload",transaction);
                kycEventHandler.onKycReject(customerId,national,"No ID upload");

            }

            return;
        }


        /**以下为Jumio身份校验通过的情况**/

        //校验到期时间
        if(!SStringUtils.isEmpty(notifyVo.getIdExpiry())){
            Long expireTime = SDateUtil.parse_yyyy_MM_dd(notifyVo.getIdExpiry()).getTime();
            if(expireTime - System.currentTimeMillis() < 30 * 24L * 60L * 60L * 1000L){
                customerVerifiedHandler.jumioVerifyFailed(customerVerified,"ID expire less than a month",transaction);
                kycEventHandler.onKycReject(customerId,national,"ID expire less than a month");

                throw new BaseException(ErrCode.ID_EXPIRE_LESS_THAN_A_MONTH);
            }
        }

        //检查证件类型
        if(!"PASSPORT".equals(notifyVo.getIdType()) && !"ID_CARD".equals(notifyVo.getIdType())){
            customerVerifiedHandler.jumioVerifyFailed(customerVerified,"Kyc not support ID type, only support，only ID cards and passports are supported",transaction);
            kycEventHandler.onKycReject(customerId,national,"Kyc not support ID type, only support，only ID cards and passports are supported");

            throw new BaseException(ErrCode.KYC_NOT_SUPPORT_ID_TYPE);
        }

        //判断国籍是否相同
        if(SStringUtils.isEmpty(notifyVo.getIdCountry())){
            customerVerifiedHandler.tranManualReview(customerVerified,"Country not match, the country recognized by jumio is null",transaction);
            kycEventHandler.onKycReject(customerId,national,"Country not match");

            throw new BaseException(ErrCode.JUMIO_NOTIFY_PARAM_ID_COUNTRY_CAN_NOT_BE_EMPTY);
        }
        if(!national.getNationCode().equals(notifyVo.getIdCountry())){
            National nat = nationalMapper.selectByNationCode(notifyVo.getIdCountry());
            String jCountry = nat != null ? nat.getNationalityEn() : notifyVo.getIdCountry();
            customerVerifiedHandler.tranManualReview(customerVerified,"Country not match, the country recognized by jumio is " + jCountry,transaction);
            kycEventHandler.onKycReject(customerId,nat,"Country not match");

            throw new BaseException(ErrCode.JUMIO_NOTIFY_ID_COUNTRY_NO_MATCH);
        }

        //判断生日
        if((SStringUtils.isEmpty(notifyVo.getIdDob())))
            throw new BaseException(ErrCode.JUMIO_NOTIFY_PARAM_ID_DOB_CAN_NOT_BE_EMPTY);
        if(!"HKG".equals(notifyVo.getIdCountry()) && !"IND".equals(notifyVo.getIdCountry())){
            //HkG 和 IND这两个国家的ID的出生日期可能不完整
            if(!notifyVo.getIdDob().equals(customerVerified.getBirthday())){
                customerVerifiedHandler.tranManualReview(customerVerified,"Birthday not match, the birthday recognized by jumio is" + notifyVo.getIdDob(),transaction);
                kycEventHandler.onKycReject(customerId,national,"Birthday not match");
                throw new BaseException(ErrCode.KYC_BIRTHDAY_NOT_MATCH);
            }
        }

        //匹配姓名
        String firstName = SStringUtils.isEmpty(customerVerified.getFirstName()) ? "" : customerVerified.getFirstName();
        String lastName = SStringUtils.isEmpty(customerVerified.getLastName()) ? "" : customerVerified.getLastName();
        String fullName = firstName + lastName;
        String fullName2 = lastName + firstName;
        String jFirstName = "N/A".equals(notifyVo.getIdFirstName()) ? "" : notifyVo.getIdFirstName();
        String jLastName = "N/A".equals(notifyVo.getIdLastName()) ? "" : notifyVo.getIdLastName();
        if(!fullName.equals(jFirstName + jLastName) && !fullName2.equals(jFirstName + jLastName)){
            //考虑到中国的情况
            customerVerifiedHandler.tranManualReview(customerVerified,"Name not match, the name recognized by jumio is firstName =" + notifyVo.getIdFirstName()+",lastName = " + notifyVo.getIdLastName(),transaction);
            throw new BaseException(ErrCode.KYC_NAME_NO_MATCH);
        }


        //判断证件是否多次使用
        if(!usrSrvConfig.isDebug() && false){
            CustomerVerified cv = customerVerifiedMapper.selectByNationalIdAndIdCardNum(customerVerified.getNationalId(),notifyVo.getIdNumber());
            if(cv != null && !cv.getId().equals(customerVerified.getId())){
                customerVerifiedHandler.jumioVerifyFailed(customerVerified,"The ID has been used",transaction);
                kycEventHandler.onKycReject(customerId,national,"The ID has been used");

                throw new BaseException(ErrCode.ID_HAS_USED);
            }
        }

        customerVerifiedHandler.kycSuccess(customerVerified,transaction);

        kycEventHandler.onKycPass(customerId,national,getUserFullName(national,firstName,lastName));
//        //判断Screening结果
//        if(SStringUtils.isEmpty(notifyVo.getAdditionalChecks()))
//            throw new BaseException(ErrCode.JUMIO_NOTIFY_PARAM_ADDITIONAL_CHECKS_CAN_NOT_BE_EMPTY);
//        ScreeningRst screeningRst = FastJson.fromJson(notifyVo.getAdditionalChecks(), ScreeningRst.class);
//        if(screeningRst != null && screeningRst.getWatchlistScreening() != null
//                && screeningRst.getWatchlistScreening().getSearchStatus().equals("DONE")
//                && screeningRst.getWatchlistScreening().getSearchResults().equals("0")
//        ){
//            //认证成功
//            kycSuccess(customerVerified);
//        }else {
//            //人工审核
//            tranManualReview(customerVerified,"");
//        }

    }





    static class ScreeningRst{
        private ScreeningItem watchlistScreening;

        public ScreeningItem getWatchlistScreening() {
            return watchlistScreening;
        }

        public void setWatchlistScreening(ScreeningItem watchlistScreening) {
            this.watchlistScreening = watchlistScreening;
        }
    }

    static class ScreeningItem{
        private String searchDate;
        private String searchId;
        private String searchReference;
        private String searchResultUrl;
        private String searchResults;
        private String searchStatus;

        public String getSearchDate() {
            return searchDate;
        }

        public void setSearchDate(String searchDate) {
            this.searchDate = searchDate;
        }

        public String getSearchId() {
            return searchId;
        }

        public void setSearchId(String searchId) {
            this.searchId = searchId;
        }

        public String getSearchReference() {
            return searchReference;
        }

        public void setSearchReference(String searchReference) {
            this.searchReference = searchReference;
        }

        public String getSearchResultUrl() {
            return searchResultUrl;
        }

        public void setSearchResultUrl(String searchResultUrl) {
            this.searchResultUrl = searchResultUrl;
        }

        public String getSearchResults() {
            return searchResults;
        }

        public void setSearchResults(String searchResults) {
            this.searchResults = searchResults;
        }

        public String getSearchStatus() {
            return searchStatus;
        }

        public void setSearchStatus(String searchStatus) {
            this.searchStatus = searchStatus;
        }
    }

    @Override
    public void processExpireKyc() {
        CustomerVerified customerVerified = customerVerifiedMapper.selectExpireKyc(System.currentTimeMillis());
        if(customerVerified == null)
            return;

        logger.info("处理证件超时的kyc，customerId="+customerVerified.getCustomerId());
        customerVerified.setStatus(CustomerVerifiedStatus.NOT_PASS.getValue());
        customerVerified.setRejectReasonMsg(customerVerified.getRejectReasonMsg()+"\n"+"Id card expired!");
        customerVerifiedMapper.updateByPrimaryKey(customerVerified);

        //修改用户的kyc状态
        Customer temp = new Customer();
        temp.setIdInfoStatus(IdInfoStatus.UNSUBMIT.getValue());
        temp.setId(customerVerified.getCustomerId());
        customerMapper.updateByPrimaryKeySelective(temp);

    }

    @Override
    public void processExpireJumioTransaction() {
        Long time = System.currentTimeMillis() - 10 * 60*1000L;
        JumioTransaction tran = jumioTransactionMapper.selectCallbackTimeoutTran(time);
        if(tran == null)
            return;

        logger.info("处理callback超时的Jimio Transaction,id="+tran.getId()+",customerId="+tran.getCustomerId());

        //对于后续继续发起了kyc认证的情况
        JumioTransaction customerLastTran = jumioTransactionMapper.selectLastByOutCustomerId(tran.getOutCustomerId());
        if(customerLastTran.getId() > tran.getId()){
            tran.setHasProcessTimeOut(true);
            jumioTransactionMapper.updateByPrimaryKey(tran);
            return;
        }

        //修改kyc记录的状态
        CustomerVerified customerVerified = customerVerifiedMapper.selectByCustomerId(tran.getCustomerId());
        if(customerVerified != null){
            customerVerified.setStatus(CustomerVerifiedStatus.NOT_PASS.getValue());
            customerVerified.setJumioRemark("Verify failed");
            customerVerifiedMapper.updateByPrimaryKey(customerVerified);
        }


        //修改客户的Kyc状态
        Customer temp = new Customer();
        temp.setId(tran.getCustomerId());
        if(tran.getHasSubmittedToJumio()){
            temp.setIdInfoStatus(IdInfoStatus.NOT_PASS.getValue());
        }else {
            temp.setIdInfoStatus(IdInfoStatus.UNSUBMIT.getValue());
        }
        customerMapper.updateByPrimaryKeySelective(temp);

        //修改JumioTransaction状态
        tran.setHasProcessTimeOut(true);
        jumioTransactionMapper.updateByPrimaryKey(tran);

    }


}
