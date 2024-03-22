package com.verificer.common.exception;

public class SBaseException extends RuntimeException{
    private Object[] errParams;
    private String errCode;

    public SBaseException(String errCode, Object[] errParams) {
        this.errCode = errCode;
        this.errParams = errParams;
    }

    public SBaseException(String errCode, Object[] errParams,String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.errParams = errParams;
    }

    public Object[] getErrParams() {
        return errParams;
    }

    public void setErrParams(Object[] errParams) {
        this.errParams = errParams;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public BaseException toBaseException(){
        return new BaseException(this.getErrCode(),null,this);
    }
}
