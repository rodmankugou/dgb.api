package com.verificer.web.common.response;

import com.alibaba.dubbo.rpc.RpcContext;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.ThreadLocalUtil;

import java.util.Map;

/**
 * 30.
 */
public enum MobileMessageCode {

	SUCCESS(1, "成功","success","成功"),
	FAILED(2, "失败","failed","失敗"),
	INVCODE_NOT_NULL(199,"邀请码错误","Invitation code error","邀請碼錯誤"),
	NEED_LOGIN(500,"需要登录","Your session is expired, please login again","需要登錄"),
	EMAIL_HAS_REGISTER(3, "该邮箱已被注册","Please use an unregistered email to bind","該郵箱已被註冊"),
	MAIL_NOT_EXIST(4, "邮箱不存在","mail does not exist","郵箱不存在"),
	VERIFY_CODE_EXPIRED(5, "验证码过期","verification code verification failed","驗證碼過期"),
	VERIFY_INFO_CODE_ERROR(6, "短信验证码有误","The code you entered is incorrect or expired","短訊驗證碼有誤"),
	WRONG_PASSWORD(7, "密码错误","password error!","密碼錯誤"),
	OLD_PASSWORD_WRONG(8, "旧密码错误","The password and the original password are different.","舊密碼錯誤"),
	CURRENCY_NOT_EXIST(9, "币种不存在","Currency does not exist","幣種不存在"),
	USER_NOT_EXIST(10, "用户不存在","User does not exist","使用者不存在"),
	BALANCE_INSUFFICIENT(11, "余额不足","Insufficient balance !","餘額不足"),
	ADDRESS_NOT_EXIST(12, "地址不存在","Address does not exist","位址不存在"),
	BLOW_SINGLE_THRESHOLD(13, "低于单笔最低限额","Less than a single minimum","低於單筆最低限額"),
	NUMBER_ILLEGAL(14, "数量不合法","Quantity failed","數量不合法"),
	SUBMIT_SUCCESS(15, "提交成功","Submit success","提交成功"),
	SUBMIT_FAILED(16, "提交失败","Submit failed","提交失敗"),
	ORDER_NOT_EXIST(17, "订单不存在","Order does not exist","訂單不存在"),
	RECHARGE_COIN(18, "充币","Recharge","充幣"),
	WITHDRAW_COIN(19, "提币","Withdraw","提幣"),
	RECHARGE_SUCCESS(20, "充币成功","Recharge success","充幣成功"),
	WITHDRAW_SUCCESS(21, "提币成功","Withdraw success","提幣成功"),
	UNDER_REVIEW(22, "审核中","In the review","審核中"),
	RECHARGE_FAILED(23, "充币失败","Recharge failed","充幣失敗"),
	WITHDRAW_FAILED(24, "提币失败","Withdraw failed","提幣失敗"),
	LOGIN_TIMEOUT(25, "登录超时","Login timed out.","登錄超時"),
	ILLEGAL_LOGIN(26, "非法登录","The illegal login","非法登錄"),
	NOT_VERIFIED(27, "未实名","The user does not have a real name","未實名"),
	RATE_NOT_DEFINED(28, "汇率未定义","Exchange rate does not exist","匯率未定義"),
	OVER_QUOTA(29, "超过额度","More than limit","超過額度"),
	NOT_SET(30, "未设置","Is not set","未設置"),
	REAL_NAME(31, "已实名","User has been certified","已實名"),
	PAY_PASSWORD_ERROR(32, "交易密码错误","Transaction Password error","交易密碼錯誤"),
	REVIEW_REJECT(33, "审核驳回","Review the rejected","審核駁回"),
	MODIFY(34, "修改","Change","修改"),
	INSUFFICIENT_FEE(35, "手续费不足","Handling Fees not enough","手續費不足"),
	WITHDRAW_LIMIT_RECHED(36, "您的今日提现额度已达上限","Your withdrawal limit for today has reached the maximum","您的今日提現額度已達上限"),
	BAD_ID_NUMBER(37, "身份证号码有误","Wrong ID card number","身份證號碼有誤"),
	ID_CARD_HAS_USED(38, "该身份证已被使用","The ID card has been used","該身份證已被使用"),
	NOT_FEE_PERIOD(39, "您不在免费期内，请预留充足的手续费","If you are not in the free period, please reserve a sufficient handling charge","您不在免費期內，請預留充足的手續費"),
	PROMOTION_NOT_EXIST(40, "推广码不存在","The promotion code does not exist","推廣碼不存在"),
	IDENTITY_NOT_CERIFIED(41, "身份未认证","Identity not authenticated","身份未認證"),
	AUTH_UNDER_REVIEW(42, "身份证认证正在审核","Id card authentication is under review","身份證認證正在審核"),
	AUTH_NOT_PASS(43, "身份认证不通过","Id card authentication failed","身份認證不通過"),
	ACCOUNT_FROZEN(44, "您的账号已被冻结，请联系客服","Your account has been frozen, please contact customer service","您的帳號已被凍結，請聯繫客服"),
	VERIFY_IMG_CODE_INCORRET(45, "图形验证码不正确","Graph identifying code error","圖形驗證碼不正確"),
	OVER_REDEMPTION_LIMIT(46, "超过兑换额度","Exceeding the exchange limit","超過兌換額度"),
	MAX_EXCHANGE_REACHED(47, "已达到最大兑换额度","The maximum exchange limit has been reached","已達到最大兌換額度"),
	NO_VERIFY_NOT_EXCHANGE(48, "未实名不能兑换","No exchange without real name","未實名不能兌換"),
	HAS_REDEEM(49, "LOS已经被兑换完啦","LOS has been converted","LOS已經被兌換完啦"),
	NO_TRANS_NO_WITHDRAW(50, "未交易不能提币","No money can be withdrawn without transaction","未交易不能提幣"),
	NO_VERIFY_NO_EXCHANGE(51, "未实名不能兑换","No exchange without real name","未實名不能兌換"),
	IDCARD_NOT_MATCH_NAME(52, "身份证号和姓名不符","The ID card doesn't match the name","身份證號和姓名不符"),
	MSG_REACH_MAX_TIME(53, "手机语音短信超过当日最大次数","Mobile phone voice messages exceeded the maximum number of times","手機語音短訊超過當日最大次"),

	/**********************/
	PARAM_CANNOT_EMPTY(54,"参数不能为空","Parameter cannot be empty","參數不能為空"),
	ACCOUNT_ILLEGAL(55,"帐号不合法","Account invalid","賬號不合法"),
	ACCOUNT_HAS_REGISTER(56,"帐号已注册","Account has already been registered","賬號已註冊"),
	PARAM_VERIFY_FAILED(57,"参数校验失败","Parameter error","參數校驗失敗"),
	HAS_BIND_BANKCARD(58,"您已绑定银行卡","You have bound your bank card","您已綁定銀行卡"),
	NOT_ALLOW_TRADE(59,"当前不允许交易","Trading is not allowed at this time","當前不允許交易"),
	NOT_BIND_BANKCARD(60,"未绑定银行卡","Unbound bank card","未綁定銀行卡"),
	ADMIN_CAN_NOT_APPLY(61,"节点不允许申请义工","Node is not allowed to apply for volunteers","節點不允許申請義工"),

	EMAIL_CODE_ERROR(70, "邮箱验证码有误","The code you entered is incorrect or expired","郵箱驗證碼有誤"),
	MOBILE_CODE_ERROR(71, "手机验证码有误","The code you entered is incorrect or expired","手機驗證碼有誤"),
	GOOGLE_CODE_ERROR(72, "谷歌验证码有误","Google identifying code error","谷歌驗證碼有誤"),
	MOBILE_HAS_REGISTER(73, "该手机号已被注册","Please use an unregistered mobile number to bind","該手機號已被註冊"),
	VERIFY_IMAGE_CODE_ERROR(74, "图片验证码有误,请刷新后重试!","Picture verification code is wrong, please refresh and try again!","圖片驗證碼有誤，請刷新後重試"),
	VERIFY_CODE_SEND_FAST(75, "验证码发送过频繁","Verification codes are sent too often","驗證碼發送過頻繁"),
	WRONG_GESTURE(76, "手势密码未开启","Gesture password not open","手勢密碼未開啟"),

	/***********
	 * 交易
	 */
	TRADE_MARKET_NOT_EXITING(100,"币种不存在","Currencies do not exist","幣種不存在"),
	TRADE_TYPE_NOT_RIGHT(101,"买卖类型异常","Trading type anomaly","買賣類型異常"),
	PRECISION_NOT_RIGHT(102,"价格或数量精度错误","Price or quantity accuracy error","價格或數量精度錯誤"),

	VERIFY_INFO_MOBILE_EMAIL_CODE_ERROR(103, "短信或邮箱验证码有误","The code you entered is incorrect or expired","短訊或郵箱驗證碼有誤"),
	VERIFY_INFO_PASSWORD_ERROR(293, "密码不符合要求","The password does not meet the requirements","密碼不符合要求"),
    //新增
    NEW_INFO_MOBILE_QWCFTJ(104,"请勿重复提交","Please do not submit again","請勿重複提交"),
    NEW_INFO_MOBILE_XDSBL(105,"下单失败","Place the order failed","下單失敗"),
    NEW_INFO_MOBILE_YZMCW(106,"验证码错误"," The code you entered is incorrect or expired","驗證碼錯誤"),
    NEW_INFO_MOBILE_YZMJYSB(107,"验证码校验失败","Verification code verification failed","驗證碼校驗失敗"),
    NEW_INFO_MOBILE_YZMYSX(108,"验证码已失效","The verification code is invalid","驗證碼已失效"),
    NEW_INFO_MOBILE_SCTBNWK(109,"上传图不能为空","The uploaded image cannot be empty","上傳圖不能為空"),
    NEW_INFO_MOBILE_ZNSCSZTP(110,"只能上传三张图片","Only three pictures can be uploaded","只能上傳三張圖片"),
    NEW_INFO_MOBILE_YJCZSHDZP(111,"已经存在待审核的照片","Photos already exist for review","已經存在待審核的照片"),
    NEW_INFO_MOBILE_SMXXBZQ(112,"实名信息不正确","The real name information is incorrect","實名資訊不正確"),
    NEW_INFO_MOBILE_DDSH(113,"等待审核","Waiting for audit","等待審核"),
    NEW_INFO_MOBILE_ZJHYJBZY(114,"证件号已经被占用","The ID card has been occupied","證件號已經被佔用"),
    NEW_INFO_MOBILE_SMXXBZQQ(115,"实名信息不正确","The real name information is incorrect","實名資訊不正確"),
    NEW_INFO_MOBILE_JGYJCGZF(116,"价格已超过涨幅限制","Prices have exceeded the limit on price increases","價格已超過漲幅限"),
    NEW_INFO_MOBILE_JGYJCGDF(117,"价格已超过跌幅限制","Prices have exceeded the fall limit","價格已超過跌幅限制"),
    NEW_INFO_MOBILE_JGJDCW(118,"价格精度错误","Price accuracy error","價格精度錯誤"),
    NEW_INFO_MOBILE_SLJDCW(119,"数量精度错误","Quantity accuracy error","數量精度錯誤"),
    NEW_INFO_MOBILE_WTCG(120,"委托成功","Entrust the success","委託成功"),
    NEW_INFO_MOBILE_CXTJCG(121,"撤销提交成功","Rescinded submission successful","撤銷提交成功"),
    NEW_INFO_MOBILE_CXTJSB(122,"撤销提交失败","Rescinded submission failed","撤銷提交失敗"),
    NEW_INFO_MOBILE_DDHYHBPP(123,"订单和用户不匹配","The order and the user don't match","訂單和使用者不匹配"),
    NEW_INFO_MOBILE_DDYCX(124,"订单已撤销","Order cancelled","訂單已撤銷"),
    NEW_INFO_MOBILE_CXSQTJCG(125,"撤销申请提交成功","The withdrawal application was submitted successfully","撤銷申請提交成功"),
    NEW_INFO_MOBILE_CXSQSB(126,"撤销申请失败","Application for cancellation failed","撤銷申請失敗"),
    NEW_INFO_MOBILE_DZBZQ(127,"地址不正确","Incorrect address","位址不正確"),
    NEW_INFO_MOBILE_BZBYXTB(128,"币种不允许提币","Currency withdrawal is not allowed","幣種不允許提幣"),
    NEW_INFO_MOBILE_TBBQBFHGF(129,"提币标签不符合规范","Tag does not comply with the specification","提幣標籤不符合規範"),
    NEW_INFO_MOBILE_TBBQBNK(130,"提币标签不能为空","Tag cannot be empty","提幣標籤不能為空"),
    NEW_INFO_MOBILE_ZHBZQ(131,"账户不正确","Incorrect account","賬戶不正確"),
    NEW_INFO_MOBILE_BZXLXXWK(132,"币种系列信息为空","Currency series information is empty","幣種系列資訊為空"),
    NEW_INFO_MOBILE_TBDZBGF(133,"提币地址不符合规范","Withdrawal address does not comply with the specification","提幣位址不符合規範"),
    NEW_INFO_MOBILE_HQDZSB(134,"获取地址失败","Failed to get address","獲取位址失敗"),
    NEW_INFO_MOBILE_YJTJGSQ(135,"已提交过申请","An application has been submitted","已提交過申請"),
    NEW_INFO_MOBILE_YEBZBYXTB(136,"余额不足1000，不允许申请","If the balance is less than 1000, no application is allowed","餘額不足1000，不允許申請"),
    NEW_INFO_MOBILE_TBSLXYZXXZ(137,"提币数量小于最小提币限制","The withdrawal quantity is less than the minimum withdrawal limit","提幣數量小於最小提幣限制"),
    NEW_INFO_MOBILE_TBJEXYBZZXJE(138,"提币金额小于币种最小单次提币金额","The withdrawal amount is less than the minimum withdrawal amount","提幣金額小於幣種最小單次提幣金額"),
    EW_INFO_MOBILE_YJSXRZGL(139,"已经实名认证过了","It's been authenticated","已經實名認證過了"),
    NNEW_INFO_MOBILE_BSSHZT(140,"用户实名认证状态不是处理待审核状态","The authentication status of the user's real name is not the processing status to be audited","使用者實名認證狀態不是處理待審核狀態"),
    NEW_INFO_MOBILE_XDCG(141,"下单成功","Place an order successfully","下單成功"),
    NEW_INFO_MOBILE_HQCBDZCW(142,"获取充币地址错误","Error getting coin address","獲取充幣位址錯誤"),
    //合约部分

    NEW_INFO_MOBILE_HZJEBZQ(143,"划转金额不正确","Transfer amount is incorrect","劃轉金額不正確"),
    NEW_INFO_MOBILE_HZBZBCZ(144,"划转币种不存在","Transfer currency does not exist","劃轉幣種不存在"),
    NEW_INFO_MOBILE_HYZHBCZ(145,"合约账户不存在","The contract account does not exist","合約賬戶不存在"),
    NEW_INFO_MOBILE_ZZHBCZ(146,"子账户不存在","The subaccount does not exist","子賬戶不存在"),
    NEW_INFO_MOBILE_ZZHZZBZ(147,"子账户资金不足","The sub-account is underfunded","子賬戶資金不足"),
    NEW_INFO_MOBILE_ZHZZBZ(148,"账户资金不足","Underfunded account","賬戶資金不足"),
    NEW_INFO_MOBILE_FXLGDBYXHZ(149,"风险率过低，不允许划转","Risk rate is too low, do not allow to delimit","風險率過低，不允許劃轉"),
    NEW_INFO_MOBILE_SYKYZCBNSYW(150,"剩余可用资产不能少于100万","The remaining available assets must not be less than $1 million","剩餘可用資產不能少於100萬"),
    NEW_INFO_MOBILE_CCCJFZ(151,"超出出金阈值","Exceeding the gold extraction threshold","超出出金閾值"),
    NEW_INFO_MOBILE_ZJHZ(152,"资金划转","Funds transfer","資金劃轉"),
    NEW_INFO_MOBILE_HZCG(153,"划转成功","Transfer success","劃轉成功"),
    NEW_INFO_MOBILE_HZSB(154,"划转失败","Transfer failed","劃轉失敗"),
    NEW_INFO_MOBILE_ZJHZZ(155,"资金划转中","Transfer of funds","資金劃轉中"),
    NEW_INFO_MOBILE_MTZHZZBZ(156,"MT5账户资金不足","The MT5 account is underfunded","MT5賬戶資金不足"),
    NEW_INFO_MOBILE_JYLXYZX(157,"交易量小于最小交易量","Trading volume is less than the minimum trading volume","交易量小於最小交易量"),
    NEW_INFO_MOBILE_JYLDYZDJYL(158,"交易量大于最大交易量","Trading volume is greater than the maximum trading volume","交易量大於最大交易量"),
    NEW_INFO_MOBILE_QXZZQGGBS(159,"请选择正确的杠杆倍数","Please select the correct leverage multiple","請選擇正確的槓桿倍數"),
    NEW_INFO_MOBILE_CGZDSCL(160,"超过最大持仓量","Exceed the maximum open position","超過最大持倉量"),
    NEW_INFO_MOBILE_BYXXD(161,"不允许下单","No exOrders allowed","不允許下單"),
    NEW_INFO_MOBILE_MZZSBXXYZKJ(162,"买涨止损必须小于最新做空价","Buy stop loss must be less than the latest short price","買漲止損必須小於最新做空價"),
    NEW_INFO_MOBILE_MZZYBXDYZXZKJ(163,"买涨止盈必须大于最新做空价","Buy - up earnings must be greater than the latest short price","買漲止盈必須大於最新做空價"),
    NEW_INFO_MOBILE_MDZSBXDYZKJ(164,"买跌止损必须大于最新做多价","Buy stop loss must be greater than the latest long price","買跌止損必須大於最新做多價"),
    NEW_INFO_MOBILE_MDZYXYZKJ(165,"买跌止盈必须小于最新做多价","Buy to stop profit must be less than the latest long price","買跌止盈必須小於最新做多價"),
    NEW_INFO_MOBILE_KYZJBZQCXXD(166,"可用金额不足，请重新下单","The available amount is insufficient, please place a new order","可用金額不足，請重新下單"),
    NEW_INFO_MOBILE_FXLGDBYXXD(167,"风险率过低，不允许下单","Risk rate is too low to allow exOrders","風險率過低，不允許下單"),
    NEW_INFO_MOBILE_DQJDBYXXD(168,"当前节点不允许下单","The current node is not allowed to place exOrders","當前節點不允許下單"),
    NEW_INFO_MOBILE_DCSLBNDYJCT(169,"对冲数量不能大于净头寸，不允许下单","The amount of hedging shall not be greater than the net position","對沖數量不能大於凈頭寸，不允許下單"),
    NEW_INFO_MOBILE_DCSLBDDY(170,"对冲数量不能大于净头寸，不允许下单","The amount of hedging shall not be greater than the net position","對沖數量不能大於凈頭寸，不允許下單"),
    NEW_INFO_MOBILE_JLBCZ(171,"记录不存在","The record does not exist","記錄不存在"),
    NEW_INFO_MOBILE_WQCZ(172,"无权操作","Have the right to operate","無權操作"),
    NEW_INFO_MOBILE_DDWDCZ(173,"订单无法操作","Order cannot be operated","訂單無法操作"),
    NEW_INFO_MOBILE_XGCG(174,"修改成功","Modify the success","修改成功"),
    NEW_INFO_MOBILE_WTJGBNGYKCJ(175,"委托价格不能低于当前开仓价","The commission price cannot be lower than the current opening price","委託價格不能低於當前開倉價"),
    NEW_INFO_MOBILE_ZSBXSRHUSJDS(176,"止损必须输入比委托价格低的价格","A stop loss must be entered at a price lower than the commission price","止損必須輸入比委託價格低的價格"),
    NEW_INFO_MOBILE_ZYBXSRFNSSFD(177,"止盈必须输入比委托价格高的价格","To stop profits, a price higher than the commission price must be entered","止盈必須輸入比委託價格高的價格"),
    NEW_INFO_MOBILE_WTJGBNFSFSD(178,"委托价格不能高于当前开仓价","The commission price cannot be higher than the current opening price","委託價格不能高於當前開倉價"),
    NEW_INFO_MOBILE_ZSBXJFDSJFSDF(179,"止损必须输入比委托价格高的价格","A stop loss must be entered at a price higher than the commission price","止損必須輸入比委託價格高的價格"),
    NEW_INFO_MOBILE_ZYBXSRFKDSFKJDSF(180,"止盈必须输入比委托价格低的价格","To stop profits, a price lower than the commission price must be entered","止盈必須輸入比委託價格低的價格"),
    NEW_INFO_MOBILE_WQCXGDD(181,"无权撤销该订单","No right to cancel the order","無權撤銷該訂單"),
    NEW_INFO_MOBILE_DDYWCWFCX(182,"该订单已完成 无法撤销","The order has been completed and cannot be cancelled","該訂單已完成 無法撤銷"),
    NEW_INFO_MOBILE_DDYCXQBYCFCX(183,"该订单已撤销 请不要重复撤销","Please do not cancel the order again","該訂單已撤銷 請不要重複撤銷"),
    NEW_INFO_MOBILE_CXCG(184,"撤销成功","Revocation of success","撤銷成功"),

	HAS_BIND_ALIPAY(190,"您已绑定支付宝","You have bound your alipay","您已綁定支付寶"),
	HAS_BIND_WECHAT(191,"您已绑定微信","You have bound your wechat","您已綁定微信"),
	PAYMENT_TYPE_INCORRECT(192,"支付类型不正确","Incorrect payment type","支付類型不正確"),
	HAS_UNBIND_ALIPAY(193,"您未绑定支付宝","Unbound your alipay","您未綁定支付寶"),
	HAS_UNBIND_WECHAT(194,"您未绑定微信","Unbound your wechat","您未綁定微信"),
   /* NEW_INFO_MOBILE_XXXX(185,"XXXX","yingwen"),
    NEW_INFO_MOBILE_XXXX(185,"XXXX","yingwen"),
    NEW_INFO_MOBILE_XXXX(187,"XXXX","yingwen"),
    NEW_INFO_MOBILE_XXXX(188,"XXXX","yingwen"),
    NEW_INFO_MOBILE_XXXX(189,"XXXX","yingwen"),*/
   CFD_SWATCH_NO(195,"目前禁止建仓","No warehouse building at present","目前禁止建倉"),
   CFD_SWATCH_NO_FALL(196,"目前禁止做空","No shorting at present","目前禁止做空"),
   CFD_SWATCH_NO_RISE(197,"目前禁止做多","No long at present","目前禁止做多"),
   ACCOUNT_ERROR_EMAIL(198,"请输入正确的邮箱地址","Please enter the correct email address","請輸入正確的郵箱位址"),
   ACCOUNT_ERROR_PHONE(199,"请输入正确的手机号码","Please enter the correct mobile number","請輸入正確的手機號碼"),
   STOP_LOSE_ERROR(200,"止损价格必须小于最新价格","Stop loss price must be less than the latest price","止損價格必須小於最新價格"),
   STOP_RISE_ERROR(201,"止盈价格必须大于最新价格","Earnings stop price must be greater than the latest price","止盈價格必須大於最新價格"),

	FINANCIAL_NO_FOUND(205, "理财产品未找到", "Products not found","理財產品未找到"),
	FINANCIAL_RANGE_OUT(202, "金额不在购买范围内", "Purchase amount is out of range","金額不在購買範圍內"),
	FINANCIAL_TIME_OUT(203, "当前不在购买时间范围内", "Currently not in purchase time range","當前不在購買時間範圍內"),
	FINANCIAL_REMAINING_AMOUNT_OUT(204, "超出理财产品剩余额度", "Exceed the remaining amount of wealth management products","超出理財產品剩餘額度"),

	C2C_HAVE_ORDER(205,"请先完成现有订单","Please complete the existing order first","請先完成現有訂單"),
	BANK_LIMIT_LIMIT(333, "您的银行卡绑定数量已达上限","Your  bank card has been limit, please delete some bank card","您的銀行卡綁定數量已達上限"),
	INVOKE_HPAY_ERROR(206,"调用HPAY服务返回异常","Hpay service error","調用HPAY服務返回異常"),
	BIZ_CURRENCY_NOT_ALLOW_TRADE(207,"币种不允许交易","This function is not available now, please contact customer service.","幣種不允許交易"),
	OVER_FOLW_MAX(208,"不可高于","Cannot exceed maximum","不可高於"),
	OVER_FOLW_MIN(209,"不可低于","Cannot below minimum","不可低於"),
	USD_RECORD_STATUS_CHECKING(210,"审核中","Reviewing","審核中"),
	USD_RECORD_STATUS_SUCCESS(211,"已完成","Done","已完成"),
	USDRECHARGE_RECORD_STATUS_FAILED(212,"已失败","Failed","已失敗"),
	USDRECHARGE_RECORD_STATUS_CANCEL(213,"已取消","Cancelled","已取消"),
	HOME_TRADE_PWD_NOT_SET(214,"支付密码未设置","Trade password not set !","支付密碼未設置"),
	HOME_TRADE_PWD_ERROR(215,"支付密码不正确","Trade password is incorrect!","支付密碼不正確"),
	TRANSFER_NOT_ALLOWED(216,"无法交易，请联系客服","This function is not available now, please contact customer service.","無法交易，請聯繫客服"),
	USDTRANSFER_TO(217,"划转划入","Transfer from HPay","劃轉划入"),
	USDTRANSFER_TO_HPAY(218,"划转至HPAY","Transfer to HPay","劃轉至HPAY"),
	EXCHANGE_NOT_EXIST(219,"交易对不存在","Exchange not exist","交易對不存在"),
	TRADE_BELOW_MINIMUM(220,"交易金额小于最小金额","Trade amount cannot below minimum","交易金額小於最小金額"),
	WRONG_MANY_PASSWORD(221, "验证多次错误，请15分钟后再试或找回密码。","Multiple password input failed, please retry after 15 minutes.","驗證多次錯誤，請15分鐘后再試或找回密碼"),
	WRONG_NEW_PASSWORD(222, "账号或登录密码错误，你还有 ","Incorrect input of account or password, you can still try ","賬號或登錄密碼錯誤,你還有 "),
	WRONG_NEW_MESSAGE(223, " 次机会"," time(s)."," 次機會"),
	HOME_PAY_PWD_ERROR(224,"支付密码不符合要求","Payment password does not meet the requirements!","支付密碼不符合要求"),
	VERIFY_INFO_MOBILE_EMAIL_CODE_EXIST(225, "验证码失效 ","Invalid Verification code","驗證碼失效 "),
	RECAPTCHA_INCORRET(226, "图形验证码错误，请重试","Recaptcha failed, please retry","圖形驗證碼錯誤，請重試"),
	TRATE_ERROR(227, "交易发生错误","Trade error,please contact customer service.","交易錯誤"),
	TRADE_SYNC_PENDING_SELL_QTY_NOT_ENOUGH(228, "交易量不足，市价委托成交失败！","Insufficient volume, failed to make market trade!","交易量不足，市價委託成交失敗!"),
	TRADE_SYNC_PENDING_BUY_QTY_NOT_ENOUGH(229, "交易量不足，市价委托成交失败！","Insufficient volume, failed to make market trade!","交易量不足，市價委託成交失敗!"),;


	private Integer code;

	private String message;

	private String messageUs;
	private String messageTw;

	MobileMessageCode(Integer code, String message , String messageUs,String messageTw) {
		this.code = code;
		this.message = message;
		this.messageUs=messageUs;
		this.messageTw = messageTw;
	}

	public String getMessageUs() {
		return messageUs;
	}

	public String getMessageTw() {
		return messageTw;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
        Map<String, String> attachments = RpcContext.getContext().getAttachments();
		String language555 = attachments.get("LOCAL_LANGUAGE");
		String language = ThreadLocalUtil.get("LOCAL_LANGUAGE");

		if(!SStringUtils.isEmpty(language) && language.equals("en_US")){
//			ThreadLocalUtil.remove("LOCAL_LANGUAGE");
			return  messageUs;
		}else if(!SStringUtils.isEmpty(language) && language.equals("zh_TW")){
//			ThreadLocalUtil.remove("LOCAL_LANGUAGE");
			return  messageTw;
		}else{
			if(null!=language555 && language555.equals("en_US")){
				return  messageUs;
			}
           	return  message;
		}
	}


	public static String getMessageByCode(Integer code) {
		MobileMessageCode[] enums = values();

		Map<String, String> attachments = RpcContext.getContext().getAttachments();
		String language555 = attachments.get("LOCAL_LANGUAGE");
		String language = ThreadLocalUtil.get("LOCAL_LANGUAGE");

		MobileMessageCode dirEnum = null;

		for (MobileMessageCode codeEnum : enums) {
			if (codeEnum.code.equals(code)) {
				dirEnum = codeEnum;
			}
		}

		if(!SStringUtils.isEmpty(language) && language.equals("en_US")){
			return  dirEnum.messageUs;
		}else{
			if(null!=language555 && language555.equals("en_US")){
				return dirEnum.messageUs;
			}
			return  dirEnum.message;
		}
	}

    public String getMessage22(String requestLanguage) {
		Map<String, String> attachments = RpcContext.getContext().getAttachments();
		String language555 = attachments.get("LOCAL_LANGUAGE");
        System.out.println("requestLanguage mobileQQQ2=="+requestLanguage);
        if(null!=requestLanguage && requestLanguage.equals("en_US")){
            return  messageUs;
        }else  if(null!=requestLanguage && requestLanguage.equals("zh_TW")){
			return  messageTw;
		}else{
			if(null!=language555 && language555.equals("en_US")){
				return  messageUs;
			}
            return  message;
        }
    }

    /*public String getMessage(HttpServletRequest request) {
        //HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestLanguage3333 = request.getHeader("language");//请求头获取language
        System.out.println("requestLanguage33333====="+requestLanguage3333);
        if(null!=requestLanguage3333 && requestLanguage3333.equals("en_US")){
            return  messageUs;
        }else{
            return  message;
        }
    }*/

}
