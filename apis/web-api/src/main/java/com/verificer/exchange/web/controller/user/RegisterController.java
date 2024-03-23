package com.verificer.exchange.web.controller.user;

import com.verificer.base_user.service.BaseCustomerService;
import com.verificer.beans.CustomerVo;
import com.verificer.beans.RegisterVo;
import com.verificer.beans.rpc.RpcResponse;
import com.verificer.exchange.web.controller.BaseController;
import com.verificer.exchange.web.security.filter.EscapeWrapper;
import com.verificer.utils.RedisUtil;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Api(value = "/", description = "注册", produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
@RequestMapping(value = "/register")
public class RegisterController extends BaseController {
    private static final Log logger = LogFactory.getLog(RegisterController.class);

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    BaseCustomerService baseCustomerService;


//
//    /**
//     * 邀请列表
//     *
//     * @return
//     */
//    @ApiOperation(
//            value = "注册",
//            response = Response.class,
//            httpMethod = "POST"
//    )
//    @ApiImplicitParams({
//            
//            @ApiImplicitParam(name = "account", value = "手机/邮箱",paramType = "form",required = true),
//            @ApiImplicitParam(name = "password", value = "密码",paramType = "form",required = true),
//            @ApiImplicitParam(name = "payPassword", value = "支付密码",paramType = "form",required = true),
//            @ApiImplicitParam(name = "inviteCode", value = "邀请码",paramType = "form",required = false),
//            @ApiImplicitParam(name = "code", value = "手机/邮箱验证码",paramType = "form",required = true),
//            @ApiImplicitParam(name = "imageId", value = "图片验证码id",paramType = "form",required = false),
//            @ApiImplicitParam(name = "imageCode", value = "图片验证码",paramType = "form",required = false),
//
//    })
//    @ResponseBody
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public Object register(HttpServletRequest request) throws Exception {
//        EscapeWrapper wrapper = new EscapeWrapper(request);
//        RegisterVo registerVo = new RegisterVo();
//        registerVo.setAccount(wrapper.getParameter("account"));
//        registerVo.setPasswd(request.getParameter("password"));
//        registerVo.setPayPasswd(wrapper.getParameter("payPassword"));
//        registerVo.setInviteCode(wrapper.getParameter("inviteCode"));
//        registerVo.setCode(wrapper.getParameter("code"));
//        registerVo.setImageId(wrapper.getParameter("imageId"));
//        registerVo.setImageCode(wrapper.getParameter("imageCode"));
//        CustomerVo customerVo = baseCustomerService.registerUser(false,registerVo);
//        //注册成功，创建账户
//        try {
//            RpcResponse<List<CurrencyVo>> resp = basicService.getAllCurrencys();
//            if(!resp.getSuccess()){
//                logger.error("注册成功后创建账户失败，原因："+resp.getErrTag());
//                return Response.simpleSuccess();
//            }
//
//            baseSupportService.setHelpEmailRegister(registerVo.getAccount());
//            List<String> subNames = new ArrayList<>();
//            for(CurrencyVo vo : resp.getPayload()){
//                subNames.add(AccountType.getExSubAccountName(vo.getSymbol()));
//            }
//            bizService.createAccount(customerVo.getId(),subNames);
//        } catch (Exception e) {
//            logger.error("注册成功后创建账户失败，原因："+e.getMessage(),e);
//        }
//        return Response.simpleSuccess();
//    }
}
