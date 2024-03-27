package com.verificer.biz.biz.service.cache.exception;

public class CacheInitFailedException extends Exception{
    public CacheInitFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheInitFailedException(String message) {
        super(message);
    }
}
