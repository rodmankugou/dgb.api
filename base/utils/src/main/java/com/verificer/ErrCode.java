package com.verificer;

/**
 * Created by 35336 on 2020/12/25.
 */
public class ErrCode {
    //公用
    public static final String INVALIDATE_CLIENT_TYPE = "INVALIDATE_CLIENT_TYPE";
    public static final String HTTP_HEAD_ERROR = "HTTP_HEAD_ERROR";
    public static final String SYSTEM_UPDATING = "SYSTEM_UPDATING";
    public static final String OTHER_LOGIN = "OTHER_LOGIN";
    public static final String FORCE_LOGOUT_AFTER_FROZEN = "FORCE_LOGOUT_AFTER_FROZEN";
    public static final String NEED_ACTIVATION = "NEED_ACTIVATION";
    public static final String COMMON_SUCCESS = "COMMON_SUCCESS";
    public static final String PARAMS_ERR = "PARAMS_ERR";
    public static final String NEED_LOGIN = "NEED_LOGIN";
    public static final String NEED_SIGN = "NEED_SIGN";
    public static final String INVALIDATE_SIGN = "INVALIDATE_SIGN";
    public static final String INVALIDATE_API_KEY = "INVALIDATE_API_KEY";
    public static final String SERVER_ERROR = "SERVER_ERROR";
    public static final String PERMISSION_DENIED = "PERMISSION_DENIED";
    public static final String PARAMS_PRECISION_ERROR = "PARAMS_PRECISION_ERROR";
    public static final String UNKNOW_ERR = "UNKNOW_ERR";
    public static final String SERVICE_BUSY = "SERVICE_BUSY";
    public static final String OP_FORBIDING = "OP_FORBIDING";
    public static final String ACCOUNT_ERR = "ACCOUNT_ERR";
    public static final String ACCOUNT_NOT_EXIST = "ACCOUNT_NOT_EXIST";
    public static final String USER_NOT_AUTH_ID_CARD = "USER_NOT_AUTH_ID_CARD";
    public static final String SEND_ACCOUNT_VALUE_IS_ILLEGAL = "SEND_ACCOUNT_VALUE_IS_ILLEGAL";
    public static final String USERNAME_NOT_EXIST = "USERNAME_NOT_EXIST";
    public static final String LOGIN_PASSWORD_ERROR = "LOGIN_PASSWORD_ERROR";
    public static final String RECORD_NOT_EXIST = "RECORD_NOT_EXIST";
    public static final String STATUS_ERROR = "STATUS_ERROR";
    public static final String STAFF_NOT_EXIST = "STAFF_NOT_EXIST";
    public static final String VERIFY_INFO_NOT_EXIST = "VERIFY_INFO_NOT_EXIST";
    public static final String CAN_NOT_REVIEW_REPEATEDLY = "CAN_NOT_REVIEW_REPEATEDLY";
    public static final String YOU_HAS_ACTIVATION = "YOU_HAS_ACTIVATION";

    public static final String VERIFIED_STATUS_CANNOT_REVIEW = "VERIFIED_STATUS_CANNOT_REVIEW";

    public static final String STAFF_NOT_ENABLE = "STAFF_NOT_ENABLE";
    public static final String STAFF_USER_NAME_HAS_EXIST = "STAFF_USER_NAME_HAS_EXIST";
    public static final String STAFF_NEW_PWD_EQUALS_OLD_PWD = "STAFF_NEW_PWD_EQUALS_OLD_PWD";


    //图片验证码
    public static final String IMAGE_CODE_CHECK_FAILED = "IMAGE_CODE_CHECK_FAILED";
    //短信/邮件验证码
    public static final String VERIFY_CODE_FAILED = "VERIFY_CODE_FAILED";
    //用户
    public static final String USER_NOT_EXIST = "USER_NOT_EXIST";
    public static final String LOGIN_FAILED = "LOGIN_FAILED";
    public static final String ACCOUNT_PATTERN_ERROR = "ACCOUNT_PATTERN_ERROR";
    public static final String MANY_LOGIN_FAILED = "MANY_LOGIN_FAILED";
    public static final String LOGIN_REST_ONE_TIMES = "LOGIN_REST_ONE_TIMES";
    public static final String LOGIN_REST_TWO_TIMES = "LOGIN_REST_TWO_TIMES";
    public static final String USER_ACCOUNT_FROZEN = "USER_ACCOUNT_FROZEN";
    public static final String GOOGLE_CHECK_FAILED = "GOOGLE_CHECK_FAILED";
    public static final String REG_MAIL_IS_EXISTING = "REG_MAIL_IS_EXISTING";
    public static final String MAIL_IS_EXISTING = "MAIL_IS_EXISTING";
    public static final String MOBILE_FORMAT_ERROR = "MOBILE_FORMAT_ERROR"; //MOBILE_FORMAT_ERR
    public static final String EMAIL_FORMAT_ERROR = "EMAIL_FORMAT_ERROR"; //EMAIL_FORMAT_ERROR
    public static final String MOBILE_IS_EXISTING = "MOBILE_IS_EXISTING";
    public static final String WALLET_ADDRESS_IS_EXISTING = "WALLET_ADDRESS_IS_EXISTING";
    public static final String REPWD_NOT_MATCH = "REPWD_NOT_MATCH";
    public static final String INVITER_IS_ERROE = "INVITER_IS_ERROE";
    public static final String LOGIN_PASSWORD_NOT_MATCH = "LOGIN_PASSWORD_NOT_MATCH";
    public static final String PAY_PWD_NOT_MATCH = "PAY_PWD_NOT_MATCH";
    public static final String PAY_PWD_ERROR = "PAY_PWD_ERROR";
    public static final String PAY_PWD_HAD_SET = "PAY_PWD_HAD_SET";
    public static final String MOBILE_CODE_SEND_FAST = "MOBILE_CODE_SEND_FAST";
    public static final String ACCOUNT_VALUE_IS_ILLEGAL = "ACCOUNT_VALUE_IS_ILLEGAL";
    public static final String MOBILE_NOT_AUTH = "MOBILE_NOT_AUTH";
    public static final String EMIAL_NOT_AUTH = "EMIAL_NOT_AUTH";
    public static final String USER_HAD_MOBILE_AUTH = "USER_HAD_MOBILE_AUTH";
    public static final String USER_HAD_MAIL_AUTH = "USER_HAD_MAIL_AUTH";
    public static final String GOOGLE_AUTH_EXIST = "GOOGLE_AUTH_EXIST";
    public static final String GOOGLE_AUTH_NOTEXIST = "GOOGLE_AUTH_NOTEXIST";
    public static final String PWD_UPDATE_FAIL = "PWD_UPDATE_FAIL";
    public static final String BANK_LIMIT_LIMIT = "BANK_LIMIT_LIMIT";
    public static final String USER_BANK_CARD_EXITING = "USER_BANK_CARD_EXITING"; //USER_BANK_EXITING
    public static final String USER_NOT_SET_PAY_PWD = "USER_NOT_SET_PAY_PWD";
    public static final String TRADE_PWD_NOT_RIGHT = "TRADE_PWD_NOT_RIGHT";
    public static final String SEND_VERIFY_FAILED = "SEND_VERIFY_FAILED";
    public static final String FORGET_PWD_CODE_ERR_LIMITED = "FORGET_PWD_CODE_ERR_LIMITED";
    public static final String FORGET_PWD_CODE_ERR_TIP = "FORGET_PWD_CODE_ERR_TIP";
    public static final String NEW_PWD_EQ_OLD = "NEW_PWD_EQ_OLD";
    public static final String LOGIN_PWD_ERR_LIMITED = "LOGIN_PWD_ERR_LIMITED";
    public static final String LOGIN_PWD_ERR_TIP = "LOGIN_PWD_ERR_TIP";
    public static final String RECAPTCHA_INCORRET = "RECAPTCHA_INCORRET";
    public static final String TRADE_GOOGLE_NOT_RIGHT = "TRADE_GOOGLE_NOT_RIGHT";
    public static final String USER_NOT_VERIFED = "USER_NOT_VERIFED";
    public static final String TRADE_PWD_NOT_SET = "TRADE_PWD_NOT_SET";
    public static final String GOOGLE_CODE_IS_NULL = "GOOGLE_CODE_IS_NULL";
    public static final String ACCOUNT_IS_ILLEGAL = "ACCOUNT_IS_ILLEGAL";
    public static final String BIZ_BALANCE_NOT_ENOUGH = "BIZ_BALANCE_NOT_ENOUGH";
    public static final String OVER_FOLW_MIN = "OVER_FOLW_MIN";
    public static final String BIZ_WITHDRAW_CANCEL_ERROR = "BIZ_WITHDRAW_CANCEL_ERROR";
    public static final String ACCOUNT_UN_FORZEN_ERROR = "ACCOUNT_UN_FORZEN_ERROR";

    public static final String ID_HAS_USED  = "ID_HAS_USED";
    public static final String KYC_NAME_NO_MATCH  = "KYC_NAME_NO_MATCH";
    public static final String KYC_BIRTHDAY_NOT_MATCH  = "KYC_BIRTHDAY_NOT_MATCH";
    public static final String JUMIO_NOTIFY_ID_COUNTRY_NO_MATCH  = "JUMIO_NOTIFY_ID_COUNTRY_NO_MATCH";
    public static final String KYC_NOT_SUPPORT_ID_TYPE  = "KYC_NOT_SUPPORT_ID_TYPE";
    public static final String ID_EXPIRE_LESS_THAN_A_MONTH  = "ID_EXPIRE_LESS_THAN_A_MONTH";

    public static final String JUMIO_NOTIFY_PARAM_ID_COUNTRY_CAN_NOT_BE_EMPTY  = "JUMIO_NOTIFY_PARAM_ID_COUNTRY_CAN_NOT_BE_EMPTY";
    public static final String JUMIO_NOTIFY_PARAM_ID_DOB_CAN_NOT_BE_EMPTY  = "JUMIO_NOTIFY_PARAM_ID_DOB_CAN_NOT_BE_EMPTY";

    //业务相关
    public static final String CURRENCY_NOT_EXIST = "CURRENCY_NOT_EXIST";
    public static final String ADDRESS_NOT_VALID = "ADDRESS_NOT_VALID";
    public static final String CURRENCY_NOT_ALLOW_WITHDRAW = "CURRENCY_NOT_ALLOW_WITHDRAW";
    public static final String CURRENCY_NOT_ALLOW_RECHARGE = "CURRENCY_NOT_ALLOW_RECHARGE";
    public static final String CURRENCY_RECHARGE_MIN_LIMIT = "CURRENCY_RECHARGE_MIN_LIMIT";


    //订单
    public static final String ORDER_NOT_EXIST = "ORDER_NOT_EXIST";
    public static final String ORDER_STATUS_ERR = "ORDER_STATUS_ERR";
    public static final String MATCHER_ERR = "MATCHER_ERR";

    //充币提币
    public static final String RECHARGE_ADDRESS_NO_EXIST = "RECHARGE_ADDRESS_NO_EXIST";


    public static final String NOT_A_DEPARTMENT_PRINCIPAL = "NOT_A_DEPARTMENT_PRINCIPAL";
    public static final String USER_NAME_NOT_EXIST = "USER_NAME_NOT_EXIST";

    //组织架构
    public static final String CANNOT_ADD_THE_SANE_EMPLOYEE_REPEATEDLY = "CANNOT_ADD_THE_SANE_EMPLOYEE_REPEATEDLY";

    public static final String CANNOT_IMPORT_ENPTY_FILE = "CANNOT_IMPORT_ENPTY_FILE";

    //超过最大可分配数量
    public static final String EXCEED_DISTRIBUTION_QUANTITY = "EXCEED_DISTRIBUTION_QUANTITY";
    //不能重复授权
    public static final String DUPLICATE_WARRANT = "DUPLICATE_WARRANT";
    //只可授权给部门负责人
    public static final String CAN_ONLY_BE_WARRANT_TO_DEPARTMENT_PRINCIPAL = "CAN_ONLY_BE_WARRANT_TO_DEPARTMENT_PRINCIPAL";

    public static final String UNBOUND_CRM_SYS_ACCOUNT = "UNBOUND_CRM_SYS_ACCOUNT";
    public static final String CALL_FAILED = "CALL_FAILED";


    public static final String ILLEGAL_ACTIVATION_LINK = "ILLEGAL_ACTIVATION_LINK";
    public static final String ACTIVATION_LINK_TIME_OUT = "ACTIVATION_LINK_TIME_OUT";


    public static final String COMP_CERT_SUBMIT_ERROR_STATUS = "COMP_CERT_SUBMIT_ERROR_STATUS";
    public static final String CAN_NOT_EDIT_WHEN_REVIEWING = "CAN_NOT_EDIT_WHEN_REVIEWING";

    public static final String KYC_ONLY_CAN_REVIEW_WHEN_IN_STATUS_WAIT_REVIEW = "KYC_ONLY_CAN_REVIEW_WHEN_IN_STATUS_WAIT_REVIEW";


    public static final String THE_COOPERATION_IS_EXISTED = "THE_COOPERATION_IS_EXISTED";
    public static final String CAN_NOT_REMOVE_OWNER = "CAN_NOT_REMOVE_OWNER";


    public static final String PROJECT_ONLY_CAN_REVIEW_WHEN_IN_STATUS_WAIT_REVIEW = "PROJECT_ONLY_CAN_REVIEW_WHEN_IN_STATUS_WAIT_REVIEW";
    public static final String SUPP_REIM_ORDER_ONLY_CAN_REVIEW_WHEN_IN_STATUS_WAIT_REVIEW = "SUPP_REIM_ORDER_ONLY_CAN_REVIEW_WHEN_IN_STATUS_WAIT_REVIEW";
    public static final String MAIN_REIM_ORDER_ERR_COIN_HAS_NOT_ISSUED = "MAIN_REIM_ORDER_ERR_COIN_HAS_NOT_ISSUED";
    public static final String SUPP_REIM_ORDER_ERR_COIN_HAS_NOT_ISSUED = "SUPP_REIM_ORDER_ERR_COIN_HAS_NOT_ISSUED";
    public static final String MAIN_REIM_ORDER_ONLY_CAN_REVIEW_WHEN_IN_STATUS_WAIT_BANK_REVIEW = "MAIN_REIM_ORDER_ONLY_CAN_REVIEW_WHEN_IN_STATUS_WAIT_BANK_REVIEW";
    public static final String MAIN_REIM_ORDER_ONLY_CAN_REVIEW_WHEN_IN_STATUS_WAIT_OWNER_REVIEW = "MAIN_REIM_ORDER_ONLY_CAN_REVIEW_WHEN_IN_STATUS_WAIT_OWNER_REVIEW";


    public static final String COIN_ERR_SYMBOL_HAS_EXISTS = "COIN_ERR_SYMBOL_HAS_EXISTS";
    public static final String COIN_ERR_FULL_NAME_HAS_EXISTS = "COIN_ERR_FULL_NAME_HAS_EXISTS";
    public static final String COIN_PRECISION_ERROR = "COIN_PRECISION_ERROR";
    public static final String COIN_CIRCULATION_ERROR = "COIN_CIRCULATION_ERROR";

    public static final String CAN_NOT_UPDATE_STARTED_PROJECT = "CAN_NOT_UPDATE_STARTED_PROJECT";
    //已经审核通过的项目不能修改，可以刷新页面并撤销并重新提交
    public static final String CAN_NOT_UPDATE_PASSED_PROJECT = "CAN_NOT_UPDATE_PASSED_PROJECT";
    //只有当前项目的意向合作银行才可以进行审批
    public static final String ONLY_INTENT_BANK_CAN_REVIEW = "ONLY_INTENT_BANK_CAN_REVIEW";

    public static final String PROJECT_INTENT_ONLY_CAN_REVIEW_WHEN_IN_STATUS_WAIT_REVIEW = "PROJECT_INTENT_ONLY_CAN_REVIEW_WHEN_IN_STATUS_WAIT_REVIEW";

    public static final String CAN_NOT_REPLY_CHOSE_BANK = "CAN_NOT_REPLY_CHOSE_BANK";
    public static final String PROJECT_NOT_PASS = "PROJECT_NOT_PASS";
    public static final String INTENT_NOT_PASS = "INTENT_NOT_PASS";

    public static final String TRAN_IN_ADDRESS_NOT_EXISTS = "TRAN_IN_ADDRESS_NOT_EXISTS";
    public static final String TRAN_IN_ADDRESS_TOKEN_ERROR = "TRAN_IN_ADDRESS_TOKEN_ERROR";
    public static final String TRAN_PROJECT_TOKEN_OUT_ACCOUNT_NOT_EXIST = "TRAN_PROJECT_TOKEN_OUT_ACCOUNT_NOT_EXIST";
    public static final String TRAN_PROJECT_TOKEN_IN_ACCOUNT_NOT_EXIST = "TRAN_PROJECT_TOKEN_IN_ACCOUNT_NOT_EXIST";
    public static final String TRAN_ONLY_ALLOW_TRAN_IN_PROJECT_PARTNERSHIP = "TRAN_ONLY_ALLOW_TRAN_IN_PROJECT_PARTNERSHIP";

    public static final String EXCHANGE_ONLY_CAN_REVIEW_WHEN_IN_STATUS_WAIT_REVIEW = "EXCHANGE_ONLY_CAN_REVIEW_WHEN_IN_STATUS_WAIT_REVIEW";
    public static final String PLEASE_SETUP_BANK_CARD_INFO = "PLEASE_SETUP_BANK_CARD_INFO";


    public static final String PRECISION_TO_HIGH = "PRECISION_TO_HIGH";
    public static final String TRADE_AMOUNT_CAN_NOT_MORE_THAN_MAX_LIMIT = "TRADE_AMOUNT_CAN_NOT_MORE_THAN_MAX_LIMIT";
    public static final String TRADE_AMOUNT_CAN_NOT_LESS_THAN_MIN_LIMIT = "TRADE_AMOUNT_CAN_NOT_LESS_THAN_MIN_LIMIT";

    public static final String VERIFY_CODE_SEND_LIMIT = "VERIFY_CODE_SEND_LIMIT";
    public static final String TRANSFER_VERIFY_CHECK_FAILED_LIMITED = "TRANSFER_VERIFY_CHECK_FAILED_LIMITED";
    public static final String TRANSFER_PAY_PWD_ERROR = "TRANSFER_PAY_PWD_ERROR";
    public static final String TRANSFER_EMAIL_CODE_ERROR = "TRANSFER_EMAIL_CODE_ERROR";


    public static final String COMPANY_NAME_CAN_NOT_REPEAT = "COMPANY_NAME_CAN_NOT_REPEAT";

    public static final String REASON_OF_REJECTION_CAN_NOT_BE_BLANK = "REASON_OF_REJECTION_CAN_NOT_BE_BLANK";

    public static final String CHAIN_SYNC_ERROR = "CHAIN_SYNC_ERROR";
    public static final String ONLY_CAN_ADD_ISSUE_AFTER_COIN_ON_THE_CHAIN = "ONLY_CAN_ADD_ISSUE_AFTER_COIN_ON_THE_CHAIN";


    public static final String PROJ_MEMBER_CAN_NOT_REMOVE_WHEN_HAS_BEEN_TRAN_OR_REIM = "PROJ_MEMBER_CAN_NOT_REMOVE_WHEN_HAS_BEEN_TRAN_OR_REIM";

    public static final String IMAGE_PERMISSION_DENIED = "IMAGE_PERMISSION_DENIED";
    public static final String TRAN_CAN_NOT_TRANS_TO_OWNER_ACCOUNT = "TRAN_CAN_NOT_TRANS_TO_OWNER_ACCOUNT";
    public static final String SETUP_BANK_CARD_VERIFY_CHECK_FAILED_LIMITED = "SETUP_BANK_CARD_VERIFY_CHECK_FAILED_LIMITED";
    public static final String SETUP_BANK_CARD_PAY_PWD_ERROR = "SETUP_BANK_CARD_PAY_PWD_ERROR";
    public static final String SETUP_BANK_CARD_EMAIL_CODE_ERROR = "SETUP_BANK_CARD_EMAIL_CODE_ERROR";


    //未翻译
    public static final String SELL_CREATE_ERR_NOT_IDLE = "SELL_CREATE_ERR_NOT_IDLE"; //NFT已在售卖中
    public static final String OFFER_CREATE_ERROR_NOT_ON_SALE = "OFFER_CREATE_ERROR_NOT_ON_SALE"; //NFT不处于售卖状态
    public static final String BID_CREATE_ERROR_NOT_ON_AUCTION = "BID_CREATE_ERROR_NOT_ON_AUCTION"; //NFT不处于售卖状态
    public static final String OFFER_CREATE_ERROR_PRICE_MUST_LARGER_THAN = "OFFER_CREATE_ERROR_PRICE_MUST_LARGER_THAN"; //需要比本次售卖的所有offer高价
    public static final String BID_CREATE_ERROR_PRICE_MUST_LARGER_THAN = "BID_CREATE_ERROR_PRICE_MUST_LARGER_THAN"; //需要比本次售卖的所有BID高价
    public static final String BID_CREATE_ERROR_LITTLE_THAN_STARTING_PRICE = "BID_CREATE_ERROR_LITTLE_THAN_STARTING_PRICE"; //需要比拍卖的起拍价高价
    public static final String BID_CREATE_ERROR_AUCTION_OVER = "BID_CREATE_ERROR_AUCTION_OVER"; //拍卖已经结束
    public static final String BID_PRICE_NOT_IN_ALLOWED_RANGE = "BID_PRICE_NOT_IN_ALLOWED_RANGE";
    public static final String OFFER_ACCEPT_ERROR_NOT_ON_SELL = "OFFER_ACCEPT_ERROR_NOT_ON_SELL"; //售卖不是正在进行中
    public static final String OFFER_ACCEPT_ERROR_OFFER_CANCELED = "OFFER_ACCEPT_ERROR_OFFER_CANCELED"; //offer已被取消
    public static final String OFFER_CANCEL_ERROR_STATUS_ERROR = "OFFER_CANCEL_ERROR_STATUS_ERROR"; //只能取消处于pending状态中的offer
    public static final String BID_CREATE_ERROR_REPEAT_BID = "BID_CREATE_ERROR_REPEAT_BID"; //只能取消处于pending状态中的offer


    public static final String ARTWORK_NOT_PENDING_REVIEW = "ARTWORK_NOT_PENDING_REVIEW";
    public static final String ARTWORK_ONLY_CAN_UPDATE_AT_AUDIT_FAILED = "ARTWORK_ONLY_CAN_UPDATE_AT_AUDIT_FAILED";
    public static final String ART_SUBMIT_ADDRESS_CONFLICT = "ART_SUBMIT_ADDRESS_CONFLICT";
    public static final String ART_MINT_STATUS_ERROR = "ART_MINT_STATUS_ERROR";

    public static final String SELL_IS_CLOSE = "SELL_IS_CLOSE";

    public static final String SERVICE_TIME_OUT = "SERVICE_TIME_OUT";


    public static final String BRAND_NAME_REPEAT = "BRAND_NAME_REPEAT";
    public static final String OP_GOODS_DEL_STATUS_ERR = "OP_GOODS_DEL_STATUS_ERR";
    public static final String OP_GOODS_RECOVER_STATUS_ERR = "OP_GOODS_RECOVER_STATUS_ERR";
    public static final String OP_GOODS_RUBBISH_STATUS_ERR = "OP_GOODS_RUBBISH_STATUS_ERR";
    public static final String OP_ADJUST_STAGE_STOCK_NOT_ENOUGH = "OP_ADJUST_STAGE_STOCK_NOT_ENOUGH";
    public static final String SHOP_BASE_URL_MUST_START_WITH_HTTPS = "SHOP_BASE_URL_MUST_START_WITH_HTTPS";
    public static final String POS_API_ID_ERR = "POS_API_ID_ERR";

}
