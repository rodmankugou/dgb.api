package com.verificer.exchange.web.controller.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.verificer.base_user.service.BaseCustomerService;
import com.verificer.beans.*;
import com.verificer.enums.CustomerVerifiedStatus;
import com.verificer.exchange.web.controller.BaseController;
import com.verificer.exchange.web.controller.FileUploadController;
import com.verificer.exchange.web.security.annotation.AllowUnActivation;
import com.verificer.exchange.web.security.annotation.NeedLogin;
import com.verificer.exchange.web.vo.OtherInfoVo;
import com.verificer.exchange.web.vo.UserInforVo;
import com.verificer.utils.FastJson;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.web.SecurityUtil;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController extends FileUploadController {
    @Autowired
    BaseCustomerService baseCustomerService;

    @ApiOperation(
            value = "获取当前用户信息",
            response = UserInforVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "veri-ex-client", value = "app/web",paramType = "header",required = true),
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public Response getUserInfo(){
        UserIdentity userIdentity = SecurityUtil.currentLogin();
        CustomerVo customerVo = baseCustomerService.getCustomerVo(userIdentity.getId());
        UserInforVo userInfo = new UserInforVo();
        userInfo.setId(customerVo.getId());
        userInfo.setRealNameState(customerVo.getIdInfoStatus().getValue());
        if(customerVo.getIdInfoStatus().getValue() == CustomerVerifiedStatus.NOT_PASS.getValue()){
            CustomerVerifiedVo cvo = baseCustomerService.getVerifiedInfoByCustomerId(customerVo.getId(),getLanguage());
            if(cvo != null)
                userInfo.setKycRejectReason(cvo.getRejectReason());
        }
        userInfo.setTransPassState(customerVo.getHasPayPassword() ? 1 : 0);
        userInfo.setMobileAuth(customerVo.getMobileAuth());
        userInfo.setMailAuth(customerVo.getMailAuth());
        userInfo.setGoogleSecretAuth(customerVo.getGoogleSecretAuth());
        userInfo.setGoogleAuthIsOpen(customerVo.getGoogleAuthIsOpen());
        userInfo.setWithdrawGoogleAuthIsOpen(customerVo.getWithdrawGoogleAuthIsOpen());
        userInfo.setMobile(customerVo.getMobile());
        userInfo.setEmail(customerVo.getEmail());
        userInfo.setRegTime(customerVo.getRegTime());
        if(customerVo.getLastLoginTime() != null)
            userInfo.setLastLoginTime(customerVo.getLastLoginTime());
        userInfo.setLastLoginIp(customerVo.getLastLoginIP());

        if(SStringUtils.isEmpty(customerVo.getLinks())){
            userInfo.setLinks(new HashMap<>());
        }else {
            Map<String,String> map = FastJson.parseMap(customerVo.getLinks(),String.class,String.class);
            userInfo.setLinks(map);
        }
        userInfo.setNickName(customerVo.getNickName());
        userInfo.setRealName(customerVo.getRealName());
        userInfo.setAvatar(customerVo.getAvatar());
        userInfo.setRemark(customerVo.getRemark());
        userInfo.setWalletAddress(customerVo.getWalletAddress());

        //暂时还不明白这两个字段的具体含义
        userInfo.setAllowedRecharge(true);
        userInfo.setAllowedWithdraw(false);

        return Response.dataSuccess(userInfo);
    }


    @ApiOperation(
            value = "获取用户信息",
            response = UserInforVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "veri-ex-client", value = "app/web",paramType = "header",required = true),
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
            @ApiImplicitParam(name = "id", value = "用户id",paramType = "form",required = true),
    })
    @ResponseBody
    @RequestMapping(value = "/other/info", method = RequestMethod.POST)
    public Response getOtherInfo(@RequestParam Long id){
        CustomerVo customerVo = baseCustomerService.getCustomerVo(id);
        OtherInfoVo userInfo = new OtherInfoVo();
        userInfo.setId(customerVo.getId());
        userInfo.setEmail(customerVo.getEmail());
        userInfo.setRegTime(customerVo.getRegTime());

        if(SStringUtils.isEmpty(customerVo.getLinks())){
            userInfo.setLinks(new HashMap<>());
        }else {
            Map<String,String> map = FastJson.parseMap(customerVo.getLinks(),String.class,String.class);
            userInfo.setLinks(map);
        }
        userInfo.setNickName(customerVo.getNickName());
        userInfo.setRealName(customerVo.getRealName());
        userInfo.setAvatar(customerVo.getAvatar());
        userInfo.setRemark(customerVo.getRemark());
        userInfo.setWalletAddress(customerVo.getWalletAddress());


        return Response.dataSuccess(userInfo);
    }

    @ApiOperation(
            value = "修改用户信息",
            response = UserInfoUpdVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "veri-ex-client", value = "app/web",paramType = "header",required = true),
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/info/update", method = RequestMethod.POST)
    public Response updateUserInfo(UserInfoUpdVo updVo){
        UserIdentity userIdentity = SecurityUtil.currentLogin();
        baseCustomerService.updateUserInfo(userIdentity.getId(),updVo);
        return Response.simpleSuccess();
    }


    @ApiOperation(
            value = "上传头像文件",
            response = Response.class,
            httpMethod = "POST",
            notes = "按照文件上传的顺序返回上传后的文件url"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "veri-ex-client", value = "app/web",paramType = "header",required = true),
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
            @ApiImplicitParam(name = "file", value = "图片",paramType = "multi-part",required = true),
    })
    @ResponseBody
    @NeedLogin
    @AllowUnActivation
    @RequestMapping(value = "/avatar/upload", method = RequestMethod.POST)
    public Object submitVerifiedInfo(HttpServletRequest request) {
        List<String> urls =ossfilesUpload(request,"/user/avatar/");
        return Response.dataSuccess(urls);
    }


    @ApiOperation(
            value = "提交实名认证资料",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "veri-ex-client", value = "app/web",paramType = "header",required = true),
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
            @ApiImplicitParam(name = "nationalId", value = "国家/地区id",paramType = "form",required = false),
            @ApiImplicitParam(name = "firstName", value = "名字",paramType = "form",required = true),
            @ApiImplicitParam(name = "lastName", value = "姓氏",paramType = "form",required = true),
            @ApiImplicitParam(name = "idCardNo", value = "证件号码",paramType = "form",required = true),
            @ApiImplicitParam(name = "idCardFrontPhoto", value = "证件照正面照",paramType = "form",required = true),
            @ApiImplicitParam(name = "idCardBackPhoto", value = "证件照信息页照片",paramType = "form",required = false),
            @ApiImplicitParam(name = "idCardRealPhoto", value = "证件与真人合照",paramType = "form",required = false),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/kyc/submit", method = RequestMethod.POST)
    public Object submitVerifiedInfo( Long nationalId,
                                     @RequestParam String firstName,
                                     @RequestParam String lastName,
                                     @RequestParam String idCardNo,
                                     @RequestParam String idCardFrontPhoto,
                                      String idCardBackPhoto,
                                      String idCardRealPhoto) {
        UserIdentity userIdentity = SecurityUtil.currentLogin();
        baseCustomerService.submitVerifiedInfo(userIdentity.getId(),nationalId,firstName,lastName, IdCardType.PASSPORT,idCardNo,idCardFrontPhoto,idCardBackPhoto,idCardRealPhoto);
        return Response.simpleSuccess();
    }
}
