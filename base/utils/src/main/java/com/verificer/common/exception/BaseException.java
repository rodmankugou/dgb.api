/**
 * 
 */
package com.verificer.common.exception;


import com.verificer.web.common.response.CommonResponseCode;

/**
 *
 */
public class BaseException extends RuntimeException{
	private Object[] errParams;
	private String errCode;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5774875451141220570L;

	public BaseException() {
		super();
	}

	public BaseException(String errCode,String message, Throwable cause) {
		super(message, cause);
		this.errCode = errCode;
	}

	public BaseException(String errCode,Object[] errParams){
		this.errCode = errCode;
		this.errParams = errParams;
	}

	public BaseException(String errCode){
		this.errCode = errCode;
	}

	public BaseException(String errCode, String message){
		super(message);
		this.errCode = errCode;
	}

	public Object[] getErrParams() {
		return errParams;
	}

	public void setErrParams(Object[] errParams) {
		this.errParams = errParams;
	}
}
