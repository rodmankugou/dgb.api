package com.verificer.utils.check;

import com.verificer.common.exception.BizErrMsgException;
import com.verificer.utils.SStringUtils;

import java.math.BigDecimal;

public class SCheckUtil {

    public static void notEmpty(Object val, String fieldName){
        if(val == null)
            throw new BizErrMsgException("Parameter " + fieldName +" can not be null");

        String errMsg = "Parameter " + fieldName +" can not be null";
        if(val instanceof String){
            if(SStringUtils.isEmpty((String) val))
                throw new BizErrMsgException(errMsg);
        }
    }

    public static void lgThan(Integer val,boolean eqFlag,Integer minVal,String fieldName){
        if(val == null)
            return;

        String errMsg = null;


        if(eqFlag){
            errMsg = "Parameter " + fieldName +" can not less than "+ val;
            if(val < minVal)
                throw new BizErrMsgException(errMsg);
        }else {
            errMsg = "Parameter " + fieldName +" must large than "+ val;
            if(val <= minVal)
                throw new BizErrMsgException(errMsg);
        }
    }

    public static void lgThanAndNotNull(Integer val,boolean eqFlag,Integer minVal,String fieldName){
        notEmpty(val,fieldName);

        lgThan(val,eqFlag,minVal,fieldName);
    }

    public static void leThan(Integer val,boolean eqFlag,Integer maxVal,String fieldName){
        if(val == null)
            return;

        String errMsg = null;

        if(eqFlag){
            errMsg = "Parameter " + fieldName +" can not large than "+ val;
            if(val > maxVal)
                throw new BizErrMsgException(errMsg);
        }else {
            errMsg = "Parameter " + fieldName +" must less than "+ val;
            if(val >= maxVal)
                throw new BizErrMsgException(errMsg);
        }
    }

    public static void leThanAndNotNull(Integer val,boolean eqFlag,Integer minVal,String fieldName){
        notEmpty(val,fieldName);

        leThan(val,eqFlag,minVal,fieldName);
    }

    public static void inRange(Integer val,boolean minEqFlag,Integer minVal,boolean maxEqFlag,Integer maxVal, String fieldName){
        if(val == null)
            return;

        String pMin = minEqFlag ? "[" : "(";
        String pMax = minEqFlag ? "]" : ")";
        try {
            leThan(val,maxEqFlag,maxVal,fieldName);
            lgThan(val,minEqFlag,minVal,fieldName);
        } catch (Exception e) {
            throw new BizErrMsgException("Parameter "+fieldName+" muse between "+pMin+minVal+","+maxVal+pMax);
        }
    }

    public static void inRangeAndNotNull(Integer val,boolean minEqFlag,Integer minVal,boolean maxEqFlag,Integer maxVal, String fieldName){
        notEmpty(val,fieldName);

        inRange(val,minEqFlag,minVal,maxEqFlag,maxVal,fieldName);
    }


    public static void lgThan(Long val, boolean eqFlag, Long minVal, String fieldName){
        if(val == null)
            return;

        String errMsg = null;


        if(eqFlag){
            errMsg = "Parameter " + fieldName +" can not less than "+ val;
            if(val < minVal)
                throw new BizErrMsgException(errMsg);
        }else {
            errMsg = "Parameter " + fieldName +" must large than "+ val;
            if(val <= minVal)
                throw new BizErrMsgException(errMsg);
        }
    }



    public static void lgThanAndNotNull(Long val,boolean eqFlag,Long minVal,String fieldName){
        notEmpty(val,fieldName);

        lgThan(val,eqFlag,minVal,fieldName);
    }

    public static void leThan(Long val,boolean eqFlag,Long maxVal,String fieldName){
        if(val == null)
            return;

        String errMsg = null;

        if(eqFlag){
            errMsg = "Parameter " + fieldName +" can not large than "+ val;
            if(val > maxVal)
                throw new BizErrMsgException(errMsg);
        }else {
            errMsg = "Parameter " + fieldName +" must less than "+ val;
            if(val >= maxVal)
                throw new BizErrMsgException(errMsg);
        }
    }

    public static void leThanAndNotNull(Long val,boolean eqFlag,Long minVal,String fieldName){
        notEmpty(val,fieldName);

        leThan(val,eqFlag,minVal,fieldName);
    }


    public static void inRange(Long val,boolean minEqFlag,Long minVal,boolean maxEqFlag,Long maxVal, String fieldName){
        if(val == null)
            return;

        String pMin = minEqFlag ? "[" : "(";
        String pMax = minEqFlag ? "]" : ")";
        try {
            leThan(val,maxEqFlag,maxVal,fieldName);
            lgThan(val,minEqFlag,minVal,fieldName);
        } catch (Exception e) {
            throw new BizErrMsgException("Parameter "+fieldName+" muse between "+pMin+minVal+","+maxVal+pMax);
        }
    }

    public static void inRangeAndNotNull(Long val,boolean minEqFlag,Long minVal,boolean maxEqFlag,Long maxVal, String fieldName){
        notEmpty(val,fieldName);

        inRange(val,minEqFlag,minVal,maxEqFlag,maxVal,fieldName);
    }


    public static void lgThan(BigDecimal val, boolean eqFlag, BigDecimal minVal, String fieldName){
        if(val == null)
            return;

        String errMsg = null;


        if(eqFlag){
            errMsg = "Parameter " + fieldName +" can not less than "+ val;
            if(val.compareTo(minVal) < 0)
                throw new BizErrMsgException(errMsg);
        }else {
            errMsg = "Parameter " + fieldName +" must large than "+ val;
            if(val.compareTo(minVal) <= 0)
                throw new BizErrMsgException(errMsg);
        }
    }

    public static void lgThanAndNotNull(BigDecimal val,boolean eqFlag,BigDecimal minVal,String fieldName){
        notEmpty(val,fieldName);

        lgThan(val,eqFlag,minVal,fieldName);
    }

    public static void leThan(BigDecimal val,boolean eqFlag,BigDecimal maxVal,String fieldName){
        if(val == null)
            return;

        String errMsg = null;

        if(eqFlag){
            errMsg = "Parameter " + fieldName +" can not large than "+ val;
            if(val.compareTo(maxVal) > 0)
                throw new BizErrMsgException(errMsg);
        }else {
            errMsg = "Parameter " + fieldName +" must less than "+ val;
            if(val.compareTo(maxVal) >= 0)
                throw new BizErrMsgException(errMsg);
        }
    }

    public static void leThanAndNotNull(BigDecimal val,boolean eqFlag,BigDecimal minVal,String fieldName){
        notEmpty(val,fieldName);

        leThan(val,eqFlag,minVal,fieldName);
    }


    public static void inRange(BigDecimal val,boolean minEqFlag,BigDecimal minVal,boolean maxEqFlag,BigDecimal maxVal, String fieldName){
        if(val == null)
            return;

        String pMin = minEqFlag ? "[" : "(";
        String pMax = minEqFlag ? "]" : ")";
        try {
            leThan(val,maxEqFlag,maxVal,fieldName);
            lgThan(val,minEqFlag,minVal,fieldName);
        } catch (Exception e) {
            throw new BizErrMsgException("Parameter "+fieldName+" muse between "+pMin+minVal+","+maxVal+pMax);
        }
    }

    public static void inRangeAndNotNull(BigDecimal val,boolean minEqFlag,BigDecimal minVal,boolean maxEqFlag,BigDecimal maxVal, String fieldName){
        notEmpty(val,fieldName);

        inRange(val,minEqFlag,minVal,maxEqFlag,maxVal,fieldName);
    }
}
