package com.verificer.biz.biz.service.core.user;

import com.verificer.beans.IdVo;
import com.verificer.biz.beans.vo.user.RefereeVo;
import com.verificer.biz.beans.vo.user.ReferrerStaVo;
import com.verificer.biz.beans.vo.user.UserWithdrawVo;
import com.verificer.biz.beans.vo.user.UserVo;
import com.verificer.biz.beans.vo.req.UserPageVo;
import com.verificer.biz.beans.vo.user.req.RefereeListReqVo;
import com.verificer.biz.beans.vo.user.req.RefereeStaReqVo;
import com.verificer.biz.beans.vo.user.req.ReferrerWithdrawPageReqVo;

import java.util.List;

public interface UserService {

    List<UserVo> userPage(UserPageVo qryVo);

    int userCount(UserPageVo qryVo);

    UserVo userDetail(IdVo idVo);

    /**
     * 引荐人的下线列表
     * @param reqVo
     * @return
     */
    List<RefereeVo> userRefereeList(RefereeListReqVo reqVo);

    /**
     * 引荐人的下线人数
     * @param reqVo
     * @return
     */
    int userRefereeCount(RefereeListReqVo reqVo);

    /**
     * 引荐人统计
     * @param reqVo
     * @return
     */
    ReferrerStaVo userRefereeSta(RefereeStaReqVo reqVo);


    /**
     * 引荐人提现记录
     * @param reqVo
     * @return
     */
    List<UserWithdrawVo> referrerWithdrawPage(ReferrerWithdrawPageReqVo reqVo);

    /**
     * 引荐人提现记录数
     * @param reqVo
     * @return
     */
    int referrerWithdrawCount(ReferrerWithdrawPageReqVo reqVo);
}
