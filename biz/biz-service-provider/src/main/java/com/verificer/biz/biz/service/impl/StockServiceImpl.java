package com.verificer.biz.biz.service.impl;

import com.verificer.biz.beans.vo.CatVo;
import com.verificer.biz.beans.vo.MerStockVo;
import com.verificer.biz.beans.vo.SpecVo;
import com.verificer.biz.beans.vo.req.StockMerQryVo;
import com.verificer.biz.biz.mapper.StockMapper;
import com.verificer.biz.biz.service.StockService;
import com.verificer.utils.PriceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

@Service
@Transactional(rollbackFor = Exception.class)
public class StockServiceImpl implements StockService {

    @Autowired
    StockMapper mapper;


    @Override
    public List<MerStockVo> merStockList(StockMerQryVo qryVo){

        TreeMap<Long,List<MerStockVo>> goodsStockMap = new TreeMap<>();
        List<MerStockVo> specStockList = mapper.selectByRelId(qryVo.relId);
        for(MerStockVo ssv : specStockList){
            if(!goodsStockMap.containsKey(ssv.getGoodsId())){
                goodsStockMap.put(ssv.getGoodsId(),new LinkedList<>());
            }
            goodsStockMap.get(ssv.getGoodsId()).add(ssv);
            ssv.setPriceTxt(PriceUtils.format(ssv.getPrice()));
        }

        List<MerStockVo> list = new LinkedList<>();
        for(Long goodsId : goodsStockMap.keySet()){
            List<MerStockVo> specList = goodsStockMap.get(goodsId);

            //商品的库存信息
            MerStockVo gsv = new MerStockVo();
            gsv.setId(goodsId);
            gsv.setName(specList.get(0).getGoodsName());
            gsv.setImg(specList.get(0).getImg());
            gsv.setCount(sumCount(specList));

            String price = "";
            if(specList.size() == 1){
                price = PriceUtils.format(specList.get(0).getPrice());
            }else {
                BigDecimal min = null;
                BigDecimal max = null;
                for(MerStockVo spec : specList){
                    if(min == null || spec.getPrice().compareTo(min) < 0)
                        min = spec.getPrice();
                    if(max == null || spec.getPrice().compareTo(min) > 0)
                        max = spec.getPrice();
                }
                price = PriceUtils.format(min) + "-" + PriceUtils.format(max);
            }
            gsv.setPriceTxt(price);
            gsv.setSpecList(specList);
            list.add(gsv);
        }

        return list;
    }

    private BigDecimal sumCount(List<MerStockVo> specStockList){
        BigDecimal count = BigDecimal.ZERO;

        for(MerStockVo specStock : specStockList)
            count = count.add(specStock.getCount());

        return count;
    }
}
