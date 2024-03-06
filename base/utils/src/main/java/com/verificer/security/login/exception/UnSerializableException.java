package com.verificer.security.login.exception;

/**
 * Created by 35336 on 2020/12/29.
 */
public class UnSerializableException extends Exception{
    public UnSerializableException() {
    }

    public UnSerializableException(String message) {
        super(message);
    }

    public UnSerializableException(String message, Throwable cause) {
        super(message, cause);
    }
}
