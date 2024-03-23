package com.verificer.exchange.admin.unittest.data.revise;

import com.amazonaws.services.rekognition.model.Face;
import com.verificer.biz.beans.vo.revise.req.ReviseFromVo;
import com.verificer.exchange.admin.unittest.Tools;
import com.verificer.tools.db.DbTools;
import com.verificer.tools.db.IReader;
import com.verificer.utils.FastJson;

import java.math.BigDecimal;

public class ReviseRunner {

    public static void run() throws Exception{
        Long stockId = Tools.getTableMaxId("dbg","stock");

        ReviseFromVo reqVo = new ReviseFromVo();
        reqVo.setId(stockId);
        reqVo.setAddFlag(true);
        reqVo.setCount(new BigDecimal(2));
        Tools.callApi("revise/create", FastJson.toJson(reqVo));
    }

    public static void main(String args[]) throws Exception{
        run();
    }
}
