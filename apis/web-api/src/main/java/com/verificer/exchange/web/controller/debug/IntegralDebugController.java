package com.verificer.exchange.web.controller.debug;

import com.verificer.account.itf.AccTools;
import com.verificer.account.itf.IntegralTools;
import com.verificer.beans.EmptyVo;
import com.verificer.beans.IdVo;
import com.verificer.beans.account.AccountVo;
import com.verificer.biz.beans.vo.user.UserVo;
import com.verificer.biz.biz.service.BizService;
import com.verificer.exchange.web.controller.BaseController;
import com.verificer.exchange.web.security.annotation.DebugController;
import com.verificer.tools.db.DbTools;
import com.verificer.utils.FastJson;
import com.verificer.utils.RandomUtils;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.check.SCheckUtil;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by 35336 on 2021/2/26.
 */
@Api(tags = "积分")
@RequestMapping("/debug/integral")
@RestController
@DebugController
public class IntegralDebugController extends BaseController {

    @Autowired
    BizService bizService;




    @ApiOperation(
            value = "插入积分记录",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID，用户登录后会返回UserId",paramType = "form",required = true),

    })
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response insertLog(@RequestParam Long userId) throws IOException {
        String sql = "insert into `account_log` ( `op_type`, `before_available`, `op_amount`, `after_available`, `after_frozen`, `sub_name`, `remark`, `before_frozen`, `customer_id`, `create_time`, `attach_id`, `account_id`) " +
                "values ( '11', '100', '2.000000000000000000', '@after', '2.000000000000000000', 'USR_INTEGRAL', '@remark', '0.000000000000000000', '1', '1711080219407', '17', '@accId');";

        SCheckUtil.notEmpty(userId,"userId");
        UserVo user = bizService.userDetail(new IdVo(userId));
        AccountVo accountVo = bizService.createAccountIfNeed(user.getUid(), AccTools.USR_INTEGRAL);

        BigDecimal after = new BigDecimal(100+ RandomUtils.getInt(10,300));
        sql = sql.replaceAll("@after",after.toPlainString());
        String remark = IntegralTools.format("积分获取","订单编号"+ SStringUtils.generateRandomNumSequence(18));
        sql = sql.replaceAll("@remark",remark);
        sql = sql.replaceAll("@accId",accountVo.getId().toString());

        try {
            DbTools.executeUpdate("dbg",sql);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }

        return Response.simpleSuccess();
    }



}
