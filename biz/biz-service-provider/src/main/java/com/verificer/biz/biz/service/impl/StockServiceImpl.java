package com.verificer.biz.biz.service.impl;

import com.verificer.GlobalConfig;
import com.verificer.biz.beans.vo.stock.MerStockStaVo;
import com.verificer.biz.beans.vo.stock.MerStockVo;
import com.verificer.biz.beans.vo.stock.StaMaxMinVo;
import com.verificer.biz.beans.vo.stock.req.StockMerQryVo;
import com.verificer.biz.biz.entity.Shop;
import com.verificer.biz.biz.entity.Stage;
import com.verificer.biz.biz.mapper.StockMapper;
import com.verificer.biz.biz.service.StockService;
import com.verificer.biz.biz.service.common.GoodsCommon;
import com.verificer.biz.biz.service.common.ShopCommon;
import com.verificer.biz.biz.service.common.StageCommon;
import com.verificer.utils.PriceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class StockServiceImpl implements StockService {

    @Autowired
    StockMapper mapper;

    @Autowired
    StageCommon stageCommon;

    @Autowired
    ShopCommon shopCommon;

    @Autowired
    GoodsCommon goodsCommon;


    @Override
    public List<MerStockStaVo> merStockStaPage(StockMerQryVo qryVo) {
        List<MerStockStaVo> list = mapper.stockStaPage(qryVo);
        List<StaMaxMinVo> mmList = mapper.staMaxMin(qryVo);
        Map<String,StaMaxMinVo> mmMap = new HashMap<>();
        for (StaMaxMinVo mm : mmList)
            mmMap.put(mm.getRelId(),mm);

        for(MerStockStaVo vo : list){
            if(vo.getStageFlag()){
                Stage stage = stageCommon.getById(vo.getRelId());
                vo.setName(stage.getName());
                vo.setCpName(stage.getCpName());
            }else {
                Shop shop = shopCommon.getById(vo.getRelId());
                vo.setName(shop.getName());
                vo.setCpName(shop.getCpName());
                StaMaxMinVo mm = mmMap.get(vo.getRelId());
                if(mm != null){
                    vo.setMaxSkuCount(mm.getMax());
                    vo.setMinSkuCount(mm.getMin());
                }else {
                    vo.setMaxSkuCount(BigDecimal.ZERO);
                    vo.setMinSkuCount(BigDecimal.ZERO);
                }

            }
        }
        return list;
    }

    @Override
    public int merStockStaCount(StockMerQryVo qryVo) {
        return mapper.stockStaCount(qryVo);
    }

    @Override
    public List<MerStockVo> merStockList(StockMerQryVo qryVo){

        TreeMap<Long,List<MerStockVo>> goodsStockMap = new TreeMap<>();
        List<MerStockVo> specStockList = mapper.selectByRelId(qryVo.getRelId());
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

            String price = goodsCommon.formatGoodsPriceBySpecPrices(specList, new GoodsCommon.ISpecPriceGetter<MerStockVo>() {
                @Override
                public BigDecimal getPrice(MerStockVo spec) {
                    return spec.getPrice();
                }
            });

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

    @Override
    public List<MerStockVo> merStockPage(StockMerQryVo qryVo) {
        List<MerStockVo> list = mapper.page(qryVo);

        for(MerStockVo vo : list){
            List<MerStockVo> specList = mapper.selectByRefIdAndGoodsId(vo.getRelId(),vo.getGoodsId());
            vo.setSpecList(specList);
            for(MerStockVo spec : specList)
                spec.setPriceTxt(spec.getPrice().setScale(GlobalConfig.PREC, RoundingMode.HALF_UP).toPlainString());
            String price = goodsCommon.formatGoodsPriceBySpecPrices(list, new GoodsCommon.ISpecPriceGetter<MerStockVo>() {

                @Override
                public BigDecimal getPrice(MerStockVo spec) {
                    return spec.getPrice();
                }
            });
            vo.setPriceTxt(price);
            vo.setCount(sumCount(specList));
        }
        return list;
    }

    @Override
    public int merStockCount(StockMerQryVo qryVo) {
        return mapper.count(qryVo);
    }
}
