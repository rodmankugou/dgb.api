package com.verificer.base_user.service.impl;

import com.verificer.ErrCode;
import com.verificer.base_user.entity.WhiteListApply;
import com.verificer.base_user.mapper.WhiteListApplyMapper;
import com.verificer.base_user.service.CustomerService;
import com.verificer.base_user.service.WhiteListService;
import com.verificer.beans.*;
import com.verificer.common.exception.BaseException;
import com.verificer.enums.RegisterType;
import com.verificer.utils.reflect.SBeanUtils;
import com.verificer.utils.SStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 35336 on 2021/2/25.
 */
@Service("whiteListService")
public class WhiteListServiceImpl implements WhiteListService {
    @Autowired
    WhiteListApplyMapper whiteListApplyMapper;

    @Autowired
    CustomerService customerService;

    /**
     * 白名单申请
     * @param customerId 用户id
     * @param exchangeId 交易对id
     * @param applyType 白名单类型
     * @param note 备注
     * @param applyStaffId 提交申请的员工id
     * @param applyStaffName 提交申请的员工姓名
     */
    @Override
    public void whiteListApply(Long customerId, Integer exchangeId, WhiteListType applyType, String note, Long applyStaffId, String applyStaffName) {
        if(customerId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(exchangeId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(applyStaffId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(applyType == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        WhiteListApply whiteListApply = new WhiteListApply();
        whiteListApply.setCustomerId(customerId);
        whiteListApply.setExchangeId(exchangeId);
        whiteListApply.setNote(note);
        whiteListApply.setApplyStaffId(applyStaffId);
        whiteListApply.setApplyStaffName(applyStaffName);
        whiteListApply.setApplyStatus(WhiteListStatus.WAIT_AUDIT.getValue());
        whiteListApply.setApplyType(applyType.getValue());
        whiteListApply.setCreateTime(System.currentTimeMillis());

        whiteListApplyMapper.insertSelective(whiteListApply);

    }

    @Override
    public List<AdminWhiteListVo> list(String language, WhiteListQueryVo whiteListQueryVo) {
        List<WhiteListApply> whiteListApplies = whiteListApplyMapper.page(whiteListQueryVo);
        List<AdminWhiteListVo> voList = new LinkedList<>();
        for(WhiteListApply whiteListApply : whiteListApplies){
            AdminWhiteListVo vo = new AdminWhiteListVo();
            SBeanUtils.copyProperties2(whiteListApply,vo);
            AdminCustomerVo customerVo = customerService.getAdminCustomerVo(language,whiteListApply.getCustomerId());
            if(customerVo != null){
                vo.setFirstName(customerVo.getFirstName());
                vo.setLastName(customerVo.getLastName());

                if(RegisterType.EMAIL.equals(customerVo.getRegType())){
                    vo.setCustomerAccount(customerVo.getEmail());
                }else if(RegisterType.MOBILE.equals(customerVo.getRegType())){
                    vo.setCustomerAccount(customerVo.getMobile());
                }
                vo.setIdCardNum(customerVo.getIdCardNo());

                voList.add(vo);
            }
        }
        return voList;
    }

    @Override
    public int count(WhiteListQueryVo whiteListQueryVo) {
        return whiteListApplyMapper.count(whiteListQueryVo);
    }

    /**
     * 审核通过/驳回白名单
     * @param whiteListId 白名单id
     * @param status 状态
     * @param auditStaffId 审核员id
     * @param auditStaffName 审核员姓名
     */
    @Override
    public void review(Long whiteListId, WhiteListStatus status, Long auditStaffId, String auditStaffName) {
        if(whiteListId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(status == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(auditStaffId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(SStringUtils.isEmpty(auditStaffName)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        WhiteListApply whiteListApply = whiteListApplyMapper.selectByPrimaryKey(whiteListId);
        if(whiteListApply == null){
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        }
        if(WhiteListStatus.WAIT_AUDIT.getValue() != whiteListApply.getApplyStatus()){
            throw new BaseException(ErrCode.CAN_NOT_REVIEW_REPEATEDLY);
        }
        WhiteListApply tmp = new WhiteListApply();
        tmp.setId(whiteListId);
        tmp.setApplyStatus(status.getValue());
        tmp.setAuditStaffId(auditStaffId);
        tmp.setAuditStaffName(auditStaffName);
        whiteListApplyMapper.updateByPrimaryKeySelective(tmp);
    }
}
