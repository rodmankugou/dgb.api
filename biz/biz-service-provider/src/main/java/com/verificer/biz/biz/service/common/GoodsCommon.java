package com.verificer.biz.biz.service.common;

import com.verificer.ErrCode;
import com.verificer.biz.beans.vo.stock.MerStockVo;
import com.verificer.biz.biz.entity.Goods;
import com.verificer.biz.biz.entity.Spec;
import com.verificer.biz.biz.mapper.GoodsMapper;
import com.verificer.biz.biz.mapper.SpecMapper;
import com.verificer.biz.biz.service.GoodsService;
import com.verificer.biz.biz.service.impl.GoodsServiceImpl;
import com.verificer.common.exception.BaseException;
import com.verificer.common.exception.BizErrMsgException;
import com.verificer.utils.PriceUtils;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class GoodsCommon {
    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    SpecMapper specMapper;

    @Autowired
    GoodsService goodsService;

    public String getSpecFullName(Long specId){
        SCheckUtil.notEmpty(specId,"Spec Id");
        Spec spec = specMapper.selectByPrimaryKey(specId);
        if(spec == null)
            return "";
        Goods goods = goodsMapper.selectByPrimaryKey(spec.getGoodsId());
        String goodsName = goods != null ? goods.getName() : "";

        return goodsName + "[ "+spec.getName()+" ]"  ;

    }

    public String getGoodsName(Long goodsId){
        SCheckUtil.notEmpty(goodsId,"Goods Id");

        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);

        return goods == null ? "" : goods.getName();

    }

    public String getSpecName(Long specId){
        SCheckUtil.notEmpty(specId,"Spec Id");

        Spec spec = specMapper.selectByPrimaryKey(specId);

        return spec == null ? "" : spec.getName();

    }

    public String getSpecImg(Long specId){
        SCheckUtil.notEmpty(specId,"Spec Id");

        Spec spec = specMapper.selectByPrimaryKey(specId);

        return spec == null ? null : spec.getImg();

    }

    /**
     * 获取规格，已删除的规格也能获取
     * @param specId
     * @return
     */
    public Spec getSpecIgnoreDel(Long specId){
        SCheckUtil.notEmpty(specId,"Spec Id");
        return specMapper.selectByPrimaryKey(specId);
    }

    public Goods getGoodsIgnoreDel(Long goodsId){
        SCheckUtil.notEmpty(goodsId,"Goods Id");
        return goodsMapper.selectByPrimaryKey(goodsId);
    }

    /**
     * 商品是否按SKU方式进行库存
     * @param goodsId
     * @return
     */
    public Boolean isGoodsSku(Long goodsId){
        SCheckUtil.notEmpty(goodsId,"Goods Id");
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        if(goods == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        return goods.getSkuFlag();
    }


    public boolean isGoodsOnSale(Goods goods) {
        return goodsService.isGoodsOnSale(goods);
    }

    public Long getGoodsId(Long specId) {
        Spec spec = specMapper.selectByPrimaryKey(specId);
        if(spec == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        return  spec.getGoodsId();
    }



    public static  interface ISpecPriceGetter<T>{
        BigDecimal getPrice(T spec);
    }

    public <T> String formatGoodsPriceBySpecPrices(List<T> specList, ISpecPriceGetter<T> priceGetter){
        String price = "";
        if(specList.size() == 1){
            price = PriceUtils.format(priceGetter.getPrice(specList.get(0)));
        }else {
            BigDecimal min = null;
            BigDecimal max = null;
            for(T spec : specList){
                BigDecimal sPrice = priceGetter.getPrice(spec);
                if(min == null || sPrice.compareTo(min) < 0)
                    min = sPrice;
                if(max == null || sPrice.compareTo(min) > 0)
                    max = sPrice;
            }
            price = PriceUtils.format(min) + "-" + PriceUtils.format(max);
        }
        return price;
    }
}
