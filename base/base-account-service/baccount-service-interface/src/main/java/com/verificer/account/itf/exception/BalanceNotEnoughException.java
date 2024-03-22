package com.verificer.account.itf.exception;

import com.verificer.ErrCode;
import com.verificer.common.exception.SBaseException;

public class BalanceNotEnoughException extends SBaseException {
    public BalanceNotEnoughException( ) {
        super(ErrCode.BIZ_BALANCE_NOT_ENOUGH,null);
    }


    public BalanceNotEnoughException(String errMsg ) {
        super(ErrCode.BIZ_BALANCE_NOT_ENOUGH,null,errMsg);

    }

}
