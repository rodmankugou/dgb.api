package com.verificer.exchange.admin.unittest.data.evaluate;

import com.amazonaws.services.rekognition.model.Face;
import com.verificer.biz.beans.vo.evaluate.EvaluateReviewVo;
import com.verificer.exchange.admin.unittest.Tools;
import com.verificer.utils.FastJson;

import java.sql.SQLException;

public class EvaluateRunner {

    public static void run() throws SQLException {

//        Tools.callApi("/debug/evaluate/create","{}");
//        Tools.callApi("/evaluate/page","{}");

        EvaluateReviewVo vo = new EvaluateReviewVo();
        vo.setId(Tools.getTableMaxId("dbg","evaluate"));
        vo.setPassFlag(true);
        Tools.callApi("evaluate/review", FastJson.toJson(vo));

    }

    public static void main(String arg[]) throws Exception {
        run();
    }
}
