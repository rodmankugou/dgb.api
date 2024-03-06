package com.verificer.exchange.web.controller.common;

import com.verificer.biz.biz.service.BizService;
import com.verificer.web.common.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    BizService bizService;
//
//    /**
//     * 获取平台在合约中用于操作资产的地址
//     */
//    @RequestMapping("/platform/address")
//    public Response getPlatFormAddress(){
//        String address = bizService.getPlatFormAddress();
//        return Response.dataSuccess(address);
//    }
}
