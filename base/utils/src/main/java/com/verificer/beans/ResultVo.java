package com.verificer.beans;

import java.io.Serializable;
import java.util.Map;

/**
 * 远程调用结果
 */
public class ResultVo implements Serializable{

    private boolean success;

    private String message;

    private Map<String, Object> data;

    private String i18nKey;

    private int code;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getI18nKey() {
        return i18nKey;
    }

    public void setI18nKey(String i18nKey) {
        this.i18nKey = i18nKey;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ResultVo{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", i18nKey='" + i18nKey + '\'' +
                ", code=" + code +
                '}';
    }
}
