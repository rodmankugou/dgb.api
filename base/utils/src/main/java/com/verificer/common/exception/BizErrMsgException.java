package com.verificer.common.exception;

public class BizErrMsgException extends RuntimeException{
    private Object[] errParams;
    private String msgTemplate;

    public String getMsgTemplate() {
        return msgTemplate;
    }

    public void setMsgTemplate(String msgTemplate) {
        this.msgTemplate = msgTemplate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     *
     */
    private static final long serialVersionUID = -5774875451141220570L;

    public BizErrMsgException() {
        super();
    }



    public BizErrMsgException(String msgTemplate, Object[] errParams){
        this.msgTemplate = msgTemplate;
        this.errParams = errParams;
    }

    public BizErrMsgException(String msgTemplate){
        this.msgTemplate = msgTemplate;
    }

    public BizErrMsgException(String msgTemplate, String message){
        super(message);
        this.msgTemplate = msgTemplate;
    }

    public Object[] getErrParams() {
        return errParams;
    }

    public void setErrParams(Object[] errParams) {
        this.errParams = errParams;
    }
}
