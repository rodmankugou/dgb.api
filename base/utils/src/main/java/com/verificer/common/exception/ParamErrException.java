/**
 * 
 */
package com.verificer.common.exception;


/**
 *
 */
public class ParamErrException extends BizErrMsgException{




	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5774875451141220570L;

	public ParamErrException() {
		super();
	}

	

	public ParamErrException(String msgTemplate, Object[] errParams){
		super(msgTemplate, errParams);
	}

	public ParamErrException(String msgTemplate){
		super(msgTemplate);
	}

	public ParamErrException(String msgTemplate, String message){
		super(msgTemplate,message);
	}


}
