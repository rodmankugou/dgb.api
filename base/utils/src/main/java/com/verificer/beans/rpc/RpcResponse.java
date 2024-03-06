package com.verificer.beans.rpc;

import java.io.Serializable;

/**
 * Created by 35336 on 2020/12/24.
 */
public class RpcResponse<E>  implements Serializable {

    //公用错误标志
    private static final String PARAMS_ERR = "PARAMS_ERR";
    private static final String PARAMS_PRECISION_ERROR = "PARAMS_PRECISION_ERROR";
    private static final String UNKNOW_ERR = "UNKNOW_ERR";
    private static final String SERVICE_BUSY = "SERVICE_BUSY";
    private static final String OP_FORBIDING = "OP_FORBIDING";
    private static final String ACCOUNT_ERR = "ACCOUNT_ERR";

    private Boolean success;
    private String errTag;
    private E payload;

    public static RpcResponse errorResp(String errTag){
        return new RpcResponse<>(errTag);
    }
    public static RpcResponse successResp(){
        return new RpcResponse<>(true);
    }
    public static RpcResponse successResp(Serializable payload){
        return new RpcResponse(true,null,payload);
    }
    public static RpcResponse paramsError(){
        return errorResp(PARAMS_ERR);
    }

    public static RpcResponse paramsPrecisionError(){
        return errorResp(PARAMS_PRECISION_ERROR);
    }
    public static RpcResponse unknowError(){
        return errorResp(UNKNOW_ERR);
    }
    public static RpcResponse serviceBusy(){
        return errorResp(SERVICE_BUSY);
    }
    public static RpcResponse opForbiding(){
        return errorResp(OP_FORBIDING);
    }
    public static RpcResponse accountErr(){
        return errorResp(ACCOUNT_ERR);
    }

    public  RpcResponse(E payload) {
        this.success = true;
        this.errTag = "";
        this.payload = payload;
    }

    private RpcResponse(String errTag) {
        this.success = false;
        this.errTag = errTag;
    }

    public RpcResponse(Boolean success, String errTag , E payload) {
        this.success = success;
        this.errTag = errTag;
        this.payload = payload;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrTag() {
        return errTag;
    }

    public void setErrTag(String errTag) {
        this.errTag = errTag;
    }

    public E getPayload() {
        return payload;
    }

    public void setPayload(E payload) {
        this.payload = payload;
    }
}
