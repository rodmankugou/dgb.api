package com.verificer.web.common.response;


/**
 * 通用响应code
 */
public enum CommonResponseCode {

    COMMON_SUCCESS(1, "web.success", "成功"),
    COMMON_ILLEGAL_PARAM(2, "web.parameter.error", "参数异常"),
    COMMON_NEED_LOGIN(3, "未登录或登录超时"),
    COMMON_SERVER_ERROR(4,"web.sys.error","系统异常"),
    COMMON_INVALID_STATUS(5, "状态异常"),
    COMMON_NOT_IMPLEMENTED(6, "方法没有实现"),
    COMMON_IO_ERROR(7, "file.io.error", "文件读写发生错误"),
    COMMON_IO_POWER(8,"没有权限"),


    COMMON_RESOURCE_NOT_FOUND(8, "web.resource.not.found", "URL资源未找到"),
    COMMON_REQUEST_METHOD_NOT_SUPPORT(9, "请求方法不支持"),
    COMMON_REQUEST_MEDIA_TYPE_NOT_SUPPORT(10, "媒体类型不支持"),

    // 数据精度错误
    DIGITS_NOT_RIGHT(11,"precision.not.right","数据精度错误"),
    HOME_PWD_NOT_EQUAL_NO(92, "web.error", "目前禁止建仓"),
    HOME_PWD_NOT_EQUAL_BIG(93, "web.error", "目前禁止做空"),
    HOME_PWD_NOT_EQUAL_SMALL(94, "web.error", "目前禁止做多"),

    /** yangminglei
     * 用户节点服务
     */

    CFD_CUSTOMER_UPDATE(104,"update.customer.failed","更新节点用户失败"),

    CFD_CUSTOMER_UPDATE_ROTE(104,"update.customer.failed","更新用户合约返佣失败"),


    CFD_CUSTOMER_UPDATE_AGENT(198,"update.customer.agent.failed","该节点不是普通用户不能变更为代理商"),
    CFD_CUSTOMER_UPDATE_AGENT_ROTE(199,"update.customer.agent.rote.failed","该节点不是普通用户不能修改合约返佣"),

    CFD_ORDER_NO_RESUlT(104,"no.result.export","无数据导出"),

    CFD_NO_POWER(155,"account.has.no_power","账户无权登录,只有管理员才能登录"),

    /***
     * home-web
     * 1XX
     */
    HOME_PWD_NOT_EQUAL(90, "web.pwd.not.equal", "密码和确认密码不一致"),
    HOME_PWD_UPDATE_FAIL(99, "web.pwd.update.fail", "密码修改失败"),
    HOME_COMMON_ERROR(100, "web.error", "哎呀出错了"),
    HOME_LOGIN_FAILED(101, "web.login.failed", "用户名或密码错误"),
    HOME_REG_ACCOUNT_IS_NULL(110, "web.account.is.null", "账户不能为空"),
    HOME_REG_ACCOUNT_IS_ILLEGAL(111, "web.account.is.illegal", "账户信息不正确"),
    HOME_PASSWORD_NOT_EQUAL(102, "web.password.not.equal", "密码或交易密码不一致"),
    HOME_IMAGE_CODE_FAILED(103, "web.imageCode.failed", "图形验证码错误"),
    HOME_MOBILE_CODE_SEND_FAST(112, "web.mobileCode.send.fast", "验证码发送过快"),
    HOME_REG_CODE_FAILED(104, "web.mobileCode.failed", "短信验证码错误"),
    HOME_REG_CODE_NEW_FAILED(104, "web.mobileCode.new.failed", "新短信验证码错误"),
    HOME_REG_CODE_OLD_FAILED(134, "web.mobileCode.old.failed", "原手机号短信验证码错误"),
    HOME_MAIL_CODE_FAILED(105, "web.mailCode.failed", "邮箱验证码错误"),
    HOME_MAIL_OLD_CODE_FAILED(133, "web.mailCode.old.failed", "原邮箱验证码错误"),
    HOME_REGIST_SUCCESS(106, "web.regist.success", "注册成功"),
    HOME_REGIST_FAILED(107, "web.regist.failed", "注册失败"),
    HOME_MOBILE_IS_EXISTING(108, "web.mobile.is.existing", "手机号已被注册"),
    HOME_MAIL_IS_EXISTING(109, "web.mail.is.existing", "邮箱号已被注册"),
    HOME_PARAM_CANNOT_EMPTY(110, "web.parameter.cannot.empty", "参数不能为空"),
    HOME_NATION_ISNOT_AVAILABLE(111, "web.nationality.isnot.available", "国籍参数有误"),
    HOME_USER_NOT_VERIFED(112, "web.user.not.verifed", "用户未实名"),
    HOME_USER_HAS_VERIFED(113, "web.user.has.verifed", "用户已实名"),
    HOME_GOOGLE_AUTH_EXIST(114, "web.user.google.auth.exist", "google 身份认证已绑定"),
    HOME_GOOGLE_AUTH_NOTEXIST(115, "web.google.auth.notexist", "google 身份认证未绑定"),
    HOME_GOOGLE_CHECK_FAILED(116, "web.google.check.failed", "谷歌验证码错误"),
    HOME_VALIDATE_FAILED(117, "web.validate.failed", "图片验证码校验失败"),
    HOME_HAS_MOBILE_AUTH(118, "web.has.mobile.auth", "手机号已认证"),
    HOME_HAS_EMAIL_AUTH(119, "web.has.email.auth", "邮箱已认证"),
    HOME_MOBILE_NOT_AUTH(120, "web.mobile.not.auth", "手机号码未认证"),
    HOME_EMIAL_NOT_AUTH(121, "web.email.not.auth", "邮箱未认证"),
    HOME_USER_NOT_EXIST(122, "web.user.not.exist", "用户不存在"),
    HOME_DO_NOT_RESUBMIT(123, "web.do.not.resubmit", "请不要重复提交"),
    HOME_NO_RECORD_FUND(124, "web.no.record.fund", "找不到记录信息"),
    HOME_LOGIN_PASSWORD_ERROR(125, "web.login.pwd.error", "登陆密码错误"),
    HOME_LOGIN_PASSWORD_NOT_MATCH(126, "web.login.pwd.not.match", "登陆密码不符合规则"),
    HOME_PAY_PWD_NOT_MATCH(127, "web.pay.pwd.not.match", "支付密码不符合规则"),

    HOME_TRADE_AMOUNT_NOT_RIGHT(128, "web.trade.amount.not.right", "购买金额不正确"),

    HOME_TRADE_PRICE_NOT_RIGHT(129, "web.trade.price.not.right", "购买价格不正确"),

    HOME_TRADE_PWD_NOT_RIGHT(130, "web.trade.pwd.not.right", "支付密码不正确"),

    HOME_TRADE_GOOGLE_NOT_RIGHT(131, "web.trade.google.not.right", "谷歌验证码不正确"),

    HOME_REGCODE_FAILED(132, "web.regCode.failed", "注册验证码错误"),
    HOME_EMAIL_ERROR(133, "web.email.error", "原邮箱帐户错误"),
    HOME_MOBILE_ERROR(134, "web.mobile.error", "原手机帐户错误"),
    HOME_GOOGLE_CODE_IS_NULL(135, "web.google.code.is.null", "google验证码不能为空"),
    HOME_NEW_EMAIL_ERROR(136, "web.new.email.error", "新邮箱帐户错误"),
    HOME_REPWD_NOT_MATCH(137, "web.pwd.repwd.not.match", "密码和确认密码不匹配"),
    HOME_ACCOUNT_FROZEN(138, "web.account.has.been.frozen","您的账号已被冻结,请联系客服"),
    HOME_LOGIN_OPTIONAL(140,"web.login.optional.error","请登录后添加自选"),

    HOME_DAYLUCKY_COUNT_OUT(139, "web.daylucky.had.count.out", "您领取的奖励超限，不可再次领取"),

    HOME_API_COUNT_OUT(141, "每个用户最多创建5组API"),

    HOME_IP_FAILED(142, "IP校验失败"),

    HOME_TYPE_ERROR(143, "验证类型错误"),

    HOME_CODE_IS_NULL(144, "web.code.cannot.empty", "请输入验证码"),

    HOME_CODE_FAILED(145, "验证码错误"),

    HOME_NEED_API(146, "先创建API"),

    HOME_IP_LONG_OUT(147, "最多绑定4个ip"),

    HOME_TRADE_PWD_NOT_SET(148, "web.trade.pwd.not.set", "支付密码未设置"),

    HOME_MOBILEMAIL_CODE_FAILED(149, "web.mobile.mail.Code.failed", "短信或邮箱验证码错误"),

    HOME_MOBILE_FORMAT_ERROE(150, "web.mobile.format.error", "手机号格式错误"),

    HOME_EMIAL_FORMAT_ERROE(151, "web.emial.format.error", "邮箱格式错误"),

    HOME_INVITER_IS_NULL(152, "web.inviter.is.null", "邀请码不能为空"),

    HOME_INVITER_IS_ERROE(153, "web.inviter.is.error", "邀请码错误"),

    HOME_ADDRESS_NOT_RIGHT(154, "web.address.not.ok", "提币地址不正确"),

    HOME_MT5_INVESTOR_PASSWORD_NOT_MATCH(156, "web.mt5.investor.pwd.not.match", "MT5投资者密码不符合规则"),

    HOME_MT5_INVESTOR_PWD_NOT_EQUAL(157, "web.mt5.investor.pwd.not.equal", "MT5投资者密码和确认密码不一致"),

    HOME_MT5_PWD_NOT_MATCH(158, "web.mt5.pwd.not.match", "密码不符合规则"),

    HOME_MT5_IS_EXISTING(159, "web.mt5.is.existing", "MT5已注册"),

    HOME_MT5_ACCOUNT_EQUAL(160, "web.mt5.account.equal", "转入转出账号不能相同"),
    
    HOME_INSERT_COMPATE_PAIR(161, "web.trading.pair.not.complete", "请输入完整的交易对"),

    TRADE_LIMIT_PRICE_REQUIRED(162, "", "限价订单的价格为必填项",
            "The price of a limit order is required",
            "限价订单的价格为必填项",
            "限價訂單的價格為必填項"),
    /***
     * 交易系统相关
     * 2XX
     */
    BIZ_COMMON_ERROR(200,"biz.service.error","交易发生错误"),

    BIZ_EXCHANGE_NOT_EXITING(201,"biz.exchange.not.exiting","交易对不存在"),

    BIZ_SUB_ACCOUNT_NOT_EXITING(202,"biz.subaccount.not.exiting","子账户不存在"),

    BIZ_SUB_ACCOUNT_NOT_MATCH(202,"biz.subaccount.not.match","子账户错误"),

    BIZ_BALANCE_NOT_ENOUGH(212,"biz.balance.not.enough","余额不足"),

    BIZ_ADDRESS_NOT_EXITING(213,"biz.address.not.exiting","地址不存在"),

    BIZ_ADDRESS_NOT_BELONG(214,"biz.address.not.belong","地址不属于该用户"),

    BIZ_AMOUNT_LESS_THAN_MIN_LIMIT(215,"biz.exchange.amount.less.than.min.limit","交易金额小于最小金额"),

    BIZ_CURRENCY_NOT_ALLOW_TRADE(216,"biz.exchange.currency.not.allow.trade","币种不允许交易"),


    BIZ_ORDER_NOT_IN(217,"biz.order.status.not.in","订单状态不是在匹配中"),

    BIZ_PARTITION_TYPE_ERROR(218,"biz.order.partition.type.error","分区类型错误"),

    BIZ_CUSTOMER_NOT_ALLOW_TRADE(219,"biz.exchange.customer.not.allow.trade","无法交易，请联系客服"),
    BIZ_SYNC_BUY_SELL_QTY_NOE_ENOUGH(220,"biz.sync.buy.sell.qty.not.enough","交易量不足，市价委托成交失败!"),
    BIZ_SYNC_SELL_BUY_QTY_NOE_ENOUGH(221,"biz.sync.sell.buy.qty.not.enough","交易量不足，市价委托成交失败!"),

    /**
     * 用户服务
     * 3XX
     */
    USER_COMMON_ERROR(300,"user.service.error","用户服务发生错误"),

    USER_NOT_AUTH_ID_CARD(301,"user.service.not.authed.idcard","身份证没有实名"),

    USER_MOBILE_EXITING(302,"user.service.mobile.exiting","手机号已存在"),

    USER_EMAIL_EXITING(303,"user.service.mail.exiting","邮箱已存在"),

    USER_NOT_SET_PAY_PWD(304,"user.service.not.set.pay.pwd","没有设置交易密码"),
    CFD_LOST_PROFIT_INTEGER(502,"止盈止损只能为整数"),
    /**
     * 杠杆交易
     */
    LEVER_CUSTOMER_NOT_ALLOW_TRADE(501, "lever.exchange.not.allow.trade", "杠杆交易不支持此交易对"),

    DO_NOT_PLEASE_TWO(503, "do.not.please.two","请不要重复请求"),
    ADMIN_CAN_NOT_APPLY(504,"node.admin.can.not.apply","节点不允许申请义工"),
    GAIN_LIMIT(505,"价格已超过涨幅限制"),
    DECLINE_LIMIT(506,"价格已超过跌幅限制"),
    BIZ_CANCEL_ERROR(507,"order.cancel.failed","订单撤销失败"),
    HOME_BANK_LIMIT_LIMIT(444, "user.bank.maxlimit", "绑定的银行卡已经到达上限"),
    USER_BANK_EXITING(445,"user.bank.exiting","银行卡号已存在"),
    HAS_BIND_ALIPAY(190,"您已绑定支付宝","You have bound your alipay"),
    HAS_BIND_WECHAT(191,"您已绑定微信","You have bound your wechat"),
    PAYMENT_TYPE_INCORRECT(192,"支付类型不正确","Incorrect payment type"),
    HAS_UNBIND_ALIPAY(193,"您未绑定支付宝","Unbound your alipay"),
    HAS_UNBIND_WECHAT(194,"您未绑定微信","Unbound your wechat"),
    FINANCIAL_NO_FOUND(1961,"理财产品未找到","Products not found"),
    FINANCIAL_RANGE_OUT(197,"金额不在购买范围内","Purchase amount is out of range"),
    FINANCIAL_TIME_OUT(1991,"当前不在购买时间范围内","Currently not in purchase time range"),
    FINANCIAL_REMAINING_AMOUNT_OUT(198,"超出理财产品剩余额度","Exceed the remaining amount of wealth management products"),
    INVCODE_NOT_NULL(199,"邀请码错误","Invitation code error"),
    C2C_HAVE_ORDER(2000,"请先完成现有订单","Please complete the existing order first"),
    OVER_FOLW_MAX(200,"overflow.max","不可高于 "),
    OVER_FOLW_MIN(201,"overflow.min","不可低于"),
    USD_RECORD_STATUS_CHECKING(5000,"usd.record.status.checking","审核中"),
    USD_RECORD_STATUS_SUCCESS(5001,"usd.record.status.success","已完成"),
    USDRECHARGE_RECORD_STATUS_FAILED(5002,"usd.record.status.failed","已失败"),
    USDRECHARGE_RECORD_STATUS_CANCEL(5003,"usd.record.status.cancel","已取消"),
    USDTRANSFER_TO_HPAY(5004,"usd.transfer.toHpay","划转至HPay"),
    USDTRANSFER_TO(5005,"usd.transfer.to","划转划入"),
    FUNCTION_ACCOUNT_FROZEN(5006, "web.account.has.been.frozen","Your account has been frozen, please contact customer service"),
    FUNCTION_FROZEN(5007,"biz.exchange.currency.not.allow.trade","改功能暂不能使用,请联系客服"),
    HOME_MANY_LOGIN_FAILED(5008, "web.many.login.failed", "验证多次错误，请15分钟后再试或找回密码。"),
    HOME_NEW_LOGIN_FAILED(5009, "web.new.login.failed", "账号或登录密码错误，你还有"),
    HOME_NEW_LOGIN_MESSAGE(5010, "web.new.login.message", "次机会。"),
    HOME_RECAPTCHA_FAILED(5011, "web.new.recaptcha.message", "人机校验不通过。"),
    HOME_REDIS_VALUE_NOT_EXIEST(5011, "web.new.redisNotExist.message", "验证码失效。"),
    ;



    private int code;
    private String i18nKey;
    private String info;
    private String enUsMsg;
    private String zhCnMsg;
    private String zhTwMsg;


    private CommonResponseCode(int value, String info) {
        this.code = value;
        this.info = info;
    }

    private CommonResponseCode(int value, String i18nKey, String info) {
        this.code = value;
        this.i18nKey = i18nKey;
        this.info = info;
    }

    CommonResponseCode(int code, String i18nKey, String info, String enUsMsg, String zhCnMsg, String zhTwMsg) {
        this.code = code;
        this.i18nKey = i18nKey;
        this.info = info;
        this.enUsMsg = enUsMsg;
        this.zhCnMsg = zhCnMsg;
        this.zhTwMsg = zhTwMsg;
    }

    public static CommonResponseCode fromInt(int value) {
        for (CommonResponseCode type : CommonResponseCode.values()) {
            if (type.code == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid Status type code: " + value);
    }

    public String getInfo() {
        return info;
    }

    public String getI18nKey() {
        return i18nKey;
    }

    @Override
    public String toString() {
        return "[" + Integer.toString(this.code) + "] " + info;
    }

    public int code() {
        return this.code;
    }

    public String getMsg(String language){
        if("en_US".equals(language)){
            return enUsMsg;
        }else if("zh_TW".equals(language)){
            return zhTwMsg;
        }else{
            return zhCnMsg;
        }
    }

    public String getEnUsMsg() {
        return enUsMsg;
    }

    public String getZhCnMsg() {
        return zhCnMsg;
    }

    public String getZhTwMsg() {
        return zhTwMsg;
    }
}
