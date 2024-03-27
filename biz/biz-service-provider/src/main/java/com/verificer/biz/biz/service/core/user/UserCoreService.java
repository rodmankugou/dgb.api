package com.verificer.biz.biz.service.core.user;

import com.verificer.beans.WxLoginReqVo;
import com.verificer.beans.account.AccountVo;
import com.verificer.biz.beans.vo.req.UserSetRefVo;
import com.verificer.biz.beans.vo.user.req.BindMobileVo;
import com.verificer.biz.beans.vo.user.req.SetAvatarVo;
import com.verificer.biz.beans.vo.user.req.SetNicknameVo;
import com.verificer.biz.biz.entity.User;

public interface UserCoreService {

    Long wxLogin(WxLoginReqVo reqVo);


    User selectByPosMemberId(Long customerUid);




    /**
     * 设置引荐人
     * @param reqVo
     */
    void userSetReferrer(UserSetRefVo reqVo);

    void bindPosMemberId(Long memberOrdId, Long posMemberId);

    /**
     * 绑定手机号码
     * @param reqVo
     */
    void userBindMobile(BindMobileVo reqVo);

    void userSetAvatar(SetAvatarVo reqVo);

    void userSetNickname(SetNicknameVo reqVo);

    public int clearExpireMember() ;

    AccountVo createAccountIfNeed(String customerId, String subName);

    boolean userIsMember(Long userId);
}
