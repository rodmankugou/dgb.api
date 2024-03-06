package com.verificer.exchange.web.controller;

import com.verificer.base_user.service.BaseCustomerService;
import com.verificer.beans.CustomerVo;
import com.verificer.biz.beans.enums.ChainTaskTypeEnum;
import com.verificer.biz.beans.vo.*;
import com.verificer.biz.biz.service.BizService;
import com.verificer.message.utils.ActiveCodeUtil;
import com.verificer.tools.TI18nTools;
import com.verificer.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/debug")
public class DebugController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(DebugController.class);

    @Autowired
    BaseCustomerService baseCustomerService;

    @Autowired
    ActiveCodeUtil activeCodeUtil;

    @Autowired
    BizService bizService;

    @Value("#{configProperties['IS_DEBUG']}")
    private boolean isDebug;







}
