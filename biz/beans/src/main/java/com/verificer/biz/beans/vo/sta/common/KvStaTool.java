package com.verificer.biz.beans.vo.sta.common;

import com.verificer.utils.RandomUtils;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.decimal.SBigDecimalUtils;
import com.verificer.utils.reflect.SBeanUtils;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class KvStaTool {

    static String[] NAMES = {"金枕头","猫山王","青尼"};

    public static KvChartVo genGoodsSta(int size){
        List<KvStaVo> list = new LinkedList<>();
        for(int i=0; i<size; i++){
            KvStaVo vo = new KvStaVo();
            String goodsName = NAMES[RandomUtils.getInt(0,2)] + SStringUtils.generateRandomNumSequence(4);
            BigDecimal count = new BigDecimal(RandomUtils.getInt(300,2000));
            list.add(vo);
        }

        BigDecimal total = BigDecimal.ZERO;
        for(KvStaVo sta : list){
            if(sta.getValue() != null)
                total = total.add(sta.getValue());
        }


        return new KvChartVo(total,list);

    }
}
