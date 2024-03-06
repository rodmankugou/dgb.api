package com.verificer.exchange.web.controller;

import com.verificer.beans.bankcard.BackCardAddReqVo;
import com.verificer.exchange.web.controller.BaseController;
import com.verificer.utils.FastJson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/xss")
public class XssTestController extends BaseController {


    @RequestMapping("/test")
    public String test(String v1 , String v2, BigDecimal v3){
        Map map = new HashMap<String,String>();
        map.put("v1",v1);
        map.put("v2",v2);
        map.put("v3",v3.scale());
        return FastJson.toJson(map);
    }

}
