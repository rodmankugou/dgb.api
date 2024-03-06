package com.verificer.utils;


import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class SUint256Utils {
    public static BigDecimal toBigDecimal(Uint256 uint256){
        return new BigDecimal(uint256.getValue().toString());
    }

    public static BigDecimal toAmount(Uint256 uint256,int precision,int keepPrecision){
        BigDecimal amount = toBigDecimal(uint256);
        amount = amount.divide(BigDecimal.TEN.pow(precision, MathContext.DECIMAL64)).setScale(keepPrecision, RoundingMode.HALF_UP);
        return amount;
    }

}
