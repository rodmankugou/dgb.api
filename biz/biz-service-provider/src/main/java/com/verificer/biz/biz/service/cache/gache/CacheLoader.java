package com.verificer.biz.biz.service.cache.gache;


import com.verificer.biz.biz.entity.*;
import com.verificer.biz.biz.service.*;
import com.verificer.biz.biz.service.cache.gache.mer.impl.StageStore;
import com.verificer.biz.biz.service.cache.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class CacheLoader implements ICacheLoader {

    @Autowired
    ShopService shopService;

    @Autowired
    StageService stageService;

    @Autowired
    StockService stockService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    SpecService specService;

    @Autowired
    CatService catService;

    @Autowired
    BrandService brandService;

    @Autowired
    GoodsStaService goodsStaService;

    @Override
    public EffMerSet loadEffMers() {
        List<Shop> sList = shopService.getAllEffShop();
        List<Stage> cList = stageService.getAllEffStage();

        List<Stock> stockList = stockService.getAllStock();
        Map<String,List<CacStock>> stockMap = buildStockMap(stockList);


        List<CacShop> csList = new LinkedList<>();
        for(Shop shop : sList){
            CacShop cs = new CacShop();
            csList.add(cs);
            cs.setId(shop.getId());
            cs.setName(shop.getName());
            cs.setLongitude(shop.getLongitude());
            cs.setLatitude(shop.getLatitude());
            List<CacStock> stList = stockMap.get(cs.getId());
            cs.setStockList(stList != null ? stList : new LinkedList<>());

        }

        List<CacStage> ccList = new LinkedList<>();
        for(Stage stage : cList){
            CacStage cs = new CacStage();
            ccList.add(cs);
            cs.setId(stage.getId());
            cs.setName(stage.getName());
            cs.setLongitude(stage.getLongitude());
            cs.setLatitude(stage.getLatitude());
            List<CacStock> stList = stockMap.get(cs.getId());
            cs.setStockList(stList != null ? stList : new LinkedList<>());
        }


        return new EffMerSet(csList,ccList);
    }

    private Map<String,List<CacStock>> buildStockMap(List<Stock> stocks){
        Map<String,List<CacStock>> map = new HashMap<>();
        for(Stock stock : stocks){
            if(!map.containsKey(stock.getRelId())){
                map.put(stock.getRelId(),new LinkedList<>());
            }
            CacStock cs = new CacStock();
            cs.setSpecId(stock.getSpecId());
            cs.setStock(stock.getCount());
            map.get(stock.getRelId()).add(cs);
        }
        return map;
    }





    @Override
    public List<CacGoods> loadEffGoods() {
        List<Goods> gList = goodsService.getAllEffGoods();
        List<Spec> sList = specService.getAll();
        List<Category> catLit = catService.getAll();
        List<Brand> brandList = brandService.getAll();
        List<GoodsSta> gsList = goodsStaService.getAll(true);

        List<CacGoods> cgList = new LinkedList<>();

        for(Goods g : gList){
            CacGoods cg = new CacGoods();
            cgList.add(cg);

            cg.setId(g.getId());
            cg.setCatId(g.getCategoryId());
            cg.setsKey(g.getSearchKey());
            cg.setBrandId(g.getBrandId());
            cg.setName(g.getName());
            cg.setImg0(g.getImgList().split("@")[0]);
            cg.setSubTitle(g.getSubTitle());
            cg.setFreeShippingFlag(g.getFreeShippingFlag());
            cg.setMaxLimitCount(g.getMaxLimitCount());
            cg.setMinLimitCount(g.getMinLimitCount());
            cg.setNonMemberBuyFlag(g.getNonMemberBuyFlag());
            //TODO 商品表添加上市时间
            cg.setMarketTime(g.getCreateTime());
        }

        fillFields(cgList,catLit,brandList,gsList);
        fillSpecList(cgList,sList);
        fillPrice(cgList);

        return cgList;
    }

    private void fillPrice(List<CacGoods> cgList) {
        for(CacGoods cg : cgList){
            List<CacSpec> specList = cg.getOriSpecList();
            BigDecimal minPrice = null;
            BigDecimal minOrigPrice = null;
            for(CacSpec spec : specList){
                if(minPrice == null || spec.getPrice().compareTo(minPrice) < 0)
                    minPrice = spec.getPrice();
                if(minOrigPrice == null || spec.getOriPrice().compareTo(minOrigPrice) < 0)
                    minOrigPrice = spec.getOriPrice();
            }

            cg.setPrice(minPrice);
            cg.setOriPrice(minOrigPrice);

        }
    }

    private void fillSpecList(List<CacGoods> cgList, List<Spec> sList) {
        Map<Long,List<CacSpec>> map = new HashMap<>();
        for(Spec spec : sList){
            if(!map.containsKey(spec.getGoodsId())){
                map.put(spec.getGoodsId(),new LinkedList<>());
            }

            CacSpec cs = new CacSpec();
            cs.setId(spec.getId());
            cs.setGoodsId(spec.getGoodsId());
            cs.setPrice(spec.getPrice());
            cs.setOriPrice(spec.getOriPrice());
            cs.setName(spec.getName());
            cs.setImg(spec.getImg());
            map.get(spec.getGoodsId()).add(cs);
        }

        for(CacGoods cg : cgList){
            cg.setOriSpecList(map.get(cg.getId()));
        }
    }

    private void fillFields(List<CacGoods> cgList,  List<Category> catList,List<Brand> brandList,List<GoodsSta> gsList){
        Map<Long,Category> catMap = new HashMap<>();
        for(Category c : catList)
            catMap.put(c.getId(),c);
        Map<Long,Brand> brandMap = new HashMap<>();
        for(Brand brand : brandList)
            brandMap.put(brand.getId(),brand);
        Map<Long,GoodsSta> gsMap = new HashMap<>();
        for(GoodsSta gs : gsList)
            gsMap.put(gs.getGoodsId(),gs);

        for(CacGoods cg : cgList){
            Category cat = catMap.get(cg.getCatId());
            if(cat != null){
                cg.setCatSKey(cat.getSearchKey());
            }

            Brand brand = brandMap.get(cg.getBrandId());
            if(brand != null){
                cg.setBrandName(brand.getName());
            }

            GoodsSta gs = gsMap.get(cg.getId());
            if(gs != null){
                cg.setSaleCount(gs.getPlaSaleCount().add(gs.getShopSaleCount()).longValue());
                cg.setSumSaleCount(gs.getPlaSaleCount().add(gs.getShopSaleCount()).intValue());
            }
        }

    }
}
