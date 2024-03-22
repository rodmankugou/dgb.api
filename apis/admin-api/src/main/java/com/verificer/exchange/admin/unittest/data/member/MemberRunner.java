package com.verificer.exchange.admin.unittest.data.member;

import com.verificer.biz.beans.vo.req.UserSetRefVo;
import com.verificer.exchange.admin.unittest.Tools;
import com.verificer.utils.FastJson;
import com.verificer.utils.SBeanUtils;

import java.math.BigDecimal;
import java.sql.SQLException;

public class MemberRunner {

    public static void run() throws SQLException {
//        Tools.init("dbg","member/init.sql");
//
//        Tools.callApi("/debug/user/creatFinishWithdraw","{}");

//        Tools.callApi("/user/page","{}");
//        Tools.callApi("/user/detail","{\"id\":1}");


//        setRef();


//        Tools.callApi("/user/refereePage","{\"userId\":"+1+"}");
//        Tools.callApi("/user/referrer/withdrawPage","{\"userId\":"+1+"}");
//        Tools.callApi("/user/refereeSta","{\"userId\":"+1+"}");
//        Tools.callApi("/referrer/withdraw/page","{}");
//        Tools.callApi("/member/sta","{}");
//        Tools.callApi("/member/rank","{}");
//        Tools.callApi("/member/page","{}");
        Tools.callApi("/member/integral/getRate","{}");




    }

    private static void setRef() throws SQLException {
        Long id = Tools.getTableMaxId("dbg","user");
        UserSetRefVo srv = new UserSetRefVo();
        srv.setId(id);
        srv.setCommission(new BigDecimal(20));
        srv.setEnableFlag(true);
        Tools.callApi("/user/setReferrer", FastJson.toJson(srv));
        Tools.callApi("/user/detail","{\"id\":"+id+"}");

    }


    public static void main(String args[]) throws SQLException {
        run();
    }
}
