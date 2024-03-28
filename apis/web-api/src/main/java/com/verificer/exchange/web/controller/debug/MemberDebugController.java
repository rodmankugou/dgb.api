package com.verificer.exchange.web.controller.debug;

import com.verificer.account.itf.AccTools;
import com.verificer.account.itf.IntegralTools;
import com.verificer.beans.IdVo;
import com.verificer.beans.account.AccountVo;
import com.verificer.biz.beans.vo.user.UserVo;
import com.verificer.biz.biz.service.BizService;
import com.verificer.exchange.web.controller.BaseController;
import com.verificer.exchange.web.security.annotation.DebugController;
import com.verificer.tools.db.DbTools;
import com.verificer.tools.db.entity.LongIdPrivateKey;
import com.verificer.utils.RandomUtils;
import com.verificer.utils.SDateUtil;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.check.SCheckUtil;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;

/**
 * Created by 35336 on 2021/2/26.
 */
@Api(tags = "会员Tools")
@RequestMapping("/debug/member")
@RestController
@DebugController
public class MemberDebugController extends BaseController {

    @Autowired
    BizService bizService;

    static class MUser  extends LongIdPrivateKey {
        private Long id;
        private Boolean memberFlag;
        private Long memberETime;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Boolean getMemberFlag() {
            return memberFlag;
        }

        public void setMemberFlag(Boolean memberFlag) {
            this.memberFlag = memberFlag;
        }

        public Long getMemberETime() {
            return memberETime;
        }

        public void setMemberETime(Long memberETime) {
            this.memberETime = memberETime;
        }
    }



    @ApiOperation(
            value = "设置是否会员，该接口设置的会员不会触发结算逻辑，仅仅是修改是否会员的状态",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID，用户登录后会返回UserId",paramType = "form",required = true),
            @ApiImplicitParam(name = "memberFlag", value = "是否设置为会员，1-是 0-否",paramType = "form",required = true),
            @ApiImplicitParam(name = "eTime", value = "会员到期时间，1-是 0-否，默认值为一年后",paramType = "form",required = false),

    })
    @ResponseBody
    @RequestMapping(value = "/upd", method = RequestMethod.POST)
    public Response insertLog(@RequestParam Long userId,@RequestParam Boolean memberFlag, @RequestParam(required = false) Long eTime ) throws IOException, SQLException {
        if(eTime == null)
            eTime = SDateUtil.offsetDate(new Date(System.currentTimeMillis()),0,0,1).getTime();

        MUser user = new MUser();
        user.setId(userId);
        user.setMemberETime(eTime);
        user.setMemberFlag(memberFlag);
        DbTools.updateSelectiveByPrivateKey("dbg","user",user);

        return Response.simpleSuccess();
    }



}
