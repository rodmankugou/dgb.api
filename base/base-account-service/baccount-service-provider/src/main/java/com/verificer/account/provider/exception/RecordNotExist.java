package com.verificer.account.provider.exception;

/**
 * Created by Administrator on 2020/12/23.
 */
public class RecordNotExist extends Exception {
    public RecordNotExist() {
    }

    public RecordNotExist(String message) {
        super(message);
    }
}
