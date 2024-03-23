
package com.verificer.web.common.response;


import com.verificer.ErrCode;
import com.verificer.web.common.i18n.I18nUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.*;

/**
 * 公共的响应
 */
@ApiModel(value = "所有接口的通用返回")
public class Response {
    private static Map<String,Integer> errorCodeMap ;
    private static final int COMMON_SUCCESS = 1;
    private static final int PARAMS_ERR = 2;
    private static final int NEED_LOGIN = 3;
    private static final int SERVER_ERROR = 4;
    private static final int NEED_SIGN = 5;
    private static final int INVALIDATE_SIGN = 6;
    private static final int PERMISSION_DENIED = 8;
    private static final int NEED_ACTIVATION = 9;
    private static final int REG_MAIL_IS_EXISTING = 10;
    private static final int NEED_BIND_MOBILE  = 11;
    private static final int NEED_MEMBER  = 12;

    static {
        errorCodeMap  = new Hashtable<>();
        errorCodeMap.put(ErrCode.COMMON_SUCCESS,COMMON_SUCCESS);
        errorCodeMap.put(ErrCode.PARAMS_ERR,PARAMS_ERR);
        errorCodeMap.put(ErrCode.NEED_LOGIN,NEED_LOGIN);
        errorCodeMap.put(ErrCode.FORCE_LOGOUT_AFTER_FROZEN,NEED_LOGIN);
        errorCodeMap.put(ErrCode.SERVER_ERROR,SERVER_ERROR);
        errorCodeMap.put(ErrCode.PERMISSION_DENIED,PERMISSION_DENIED);
        errorCodeMap.put(ErrCode.NEED_SIGN,NEED_SIGN);
        errorCodeMap.put(ErrCode.INVALIDATE_SIGN,INVALIDATE_SIGN);
        errorCodeMap.put(ErrCode.NEED_ACTIVATION,NEED_ACTIVATION);
        errorCodeMap.put(ErrCode.REG_MAIL_IS_EXISTING,REG_MAIL_IS_EXISTING);
        errorCodeMap.put(ErrCode.NEED_BIND_MOBILE,NEED_BIND_MOBILE);
        errorCodeMap.put(ErrCode.NEED_MEMBER,NEED_MEMBER);
    }



    // 是否成功
    @ApiModelProperty(name = "result",value = "是否成功，true-成功 false-失败")
    private boolean result;

    // 消息描述
    @ApiModelProperty("消息描述")
    private String message;

    // 状态码
    @ApiModelProperty("状态码 1-成功 2-参数错误 3-需要重新登录 4-服务器异常 5-签名不合法 6-需要重新签名 8-权限不足")
    private int code = SERVER_ERROR;

    // 总个数
    @ApiModelProperty("总数据条数，需要分页的接口才有返回")
    private int total;

    // 返回数据
    @ApiModelProperty("返回的具体数据")
    private Object data ;

    private Response(String errCode, String message,Object data,Integer total) {
        this(errCode,message,data,total,null);
    }

    private Response(String errCode, String message,Object data,Integer total,Object[] params) {
        this.code = errorCodeMap.containsKey(errCode) ? errorCodeMap.get(errCode) : SERVER_ERROR;
        this.message = message;
        this.data = data;
        this.total = total == null ? 0 : total;
        this.result = code == COMMON_SUCCESS ? true : false;
        if(message == null){
            this.message = I18nUtils.getMessage(errCode,params);
        }
    }

    private Response(String errCode) {
        this(errCode,null,null,null);
    }


    public static Response simpleSuccess() {
        return new Response(ErrCode.COMMON_SUCCESS);
    }

    public static Response listSuccess(int total, List<?> list) {
        return new Response(ErrCode.COMMON_SUCCESS, null, list, total);
    }

    public static Response dataSuccess(Object respData) {
        return new Response(ErrCode.COMMON_SUCCESS, null, respData,null);
    }

    public static Response paramFailed(String msgTemplate, Object[] params){
        return new Response(ErrCode.PARAMS_ERR,msgTemplate,null,null,params);
    }

    public static Response failed(String errTag) {
        return new Response(errTag);
    }
    public static Response failed(String errTag,Object[] params) {

        return new Response(errTag,null,null,null,params);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static Map<String, Integer> getErrorCodeMap() {
        return errorCodeMap;
    }

    public static void setErrorCodeMap(Map<String, Integer> errorCodeMap) {
        Response.errorCodeMap = errorCodeMap;
    }

    public static int getCommonSuccess() {
        return COMMON_SUCCESS;
    }

    public static int getServerError() {
        return SERVER_ERROR;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public static Response needLoginResponse() {
        return Response.failed(ErrCode.NEED_LOGIN);
    }
}
