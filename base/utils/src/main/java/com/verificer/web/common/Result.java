package com.verificer.web.common;



import com.verificer.web.common.response.CommonResponseCode;
import com.verificer.web.common.response.MobileMessageCode;

import java.io.Serializable;

/**
 * 29.
 */
public class Result implements Serializable {
    private static final long serialVersionUID = 4209841131428225150L;

    public static final int SUCCESS = 0;

    public static final int FAILED = 1;

    public static final int NOT_SET_PAY_PWD = 2;

    public static final int NEED_LOGIN = -1;
    public static final int UODATING = -2;
    public static final int OTHERLOGIN = -3;
    /**
     * 状态值0：成功 1：失败
     */
    private int state;

    /**
     * 返回消息
     */
    private String message;
    /**
     * 返回内容
     */
    private Object data;

    private int  precision;

    public Result() {
    }

    public Result(int state) {
        this.state = state;
    }

    public Result(int state, String message) {
        this.state = state;
        this.message = message;
    }

    public Result(int state, String message, String data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public static Result responseWithCode(CommonResponseCode homeCommonError, String message) {
        Result result = new Result();
        result.setData(homeCommonError.code() == 1);
        result.setState(homeCommonError.code());
        result.setMessage(message);
        result.setPrecision(0);
        return result;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public static Result failed() {
        Result result = new Result();
        result.setState(FAILED);
        result.setMessage(MobileMessageCode.FAILED.getMessage());
        return result;
    }

    public static Result simpleSuccess() {
        return new Result(SUCCESS, MobileMessageCode.SUCCESS.getMessage());
    }
}
