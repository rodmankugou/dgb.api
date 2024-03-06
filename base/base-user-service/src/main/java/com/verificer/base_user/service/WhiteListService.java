package com.verificer.base_user.service;

import com.verificer.beans.AdminWhiteListVo;
import com.verificer.beans.WhiteListQueryVo;
import com.verificer.beans.WhiteListStatus;
import com.verificer.beans.WhiteListType;

import java.util.List;

/**
 * Created by 35336 on 2021/2/25.
 */
public interface WhiteListService {
    /**
     * 白名单申请
     * @param customerId 用户id
     * @param exchangeId 交易对id
     * @param applyType 白名单类型
     * @param note 备注
     * @param applyStaffId 提交申请的员工id
     * @param applyStaffName 提交申请的员工姓名
     */
    public void whiteListApply(Long customerId, Integer exchangeId, WhiteListType applyType, String note, Long applyStaffId, String applyStaffName);


    /**
     * 分页查询白名单列表
     * @param language
     * @param whiteListQueryVo
     * @return
     */
    List<AdminWhiteListVo> list(String language, WhiteListQueryVo whiteListQueryVo);

    /**
     * 统计分页数量
     * @param whiteListQueryVo
     * @return
     */
    int count(WhiteListQueryVo whiteListQueryVo);


    /**
     * 审核通过/驳回白名单
     * @param whiteListId 白名单id
     * @param status 状态
     * @param auditStaffId 审核员id
     * @param auditStaffName 审核员姓名
     */
    void review(Long whiteListId, WhiteListStatus status, Long auditStaffId, String auditStaffName);
}
