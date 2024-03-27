package com.verificer.biz.biz.service.cache.gache;

import com.verificer.GlobalConfig;
import com.verificer.beans.PageQueryVo;
import com.verificer.biz.beans.vo.goods.ASpecStockVo;
import com.verificer.biz.beans.vo.goods.ASpecVo;
import com.verificer.biz.biz.ThreadPools;
import com.verificer.biz.biz.service.GoodsService;
import com.verificer.biz.biz.service.cache.exception.CacheInitFailedException;
import com.verificer.biz.biz.service.cache.gache.filter.IGFilter;
import com.verificer.biz.biz.service.cache.gache.mer.MerMatchReqVo;
import com.verificer.biz.biz.service.cache.gache.mer.impl.ShopStore;
import com.verificer.biz.biz.service.cache.gache.mer.impl.SpecStockMap;
import com.verificer.biz.biz.service.cache.gache.mer.impl.StageStore;
import com.verificer.biz.biz.service.cache.gache.sort.ISort;
import com.verificer.biz.biz.service.cache.vo.*;
import com.verificer.utils.page.PageIndex;
import com.verificer.utils.page.PageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 商品缓存
 */
@Component
public class GCache  {
    private static final Logger logger = LoggerFactory.getLogger(GCache.class);



    @Autowired
    ICacheLoader cacheLoader;

    /**
     * 读写锁
     */
    private final ReentrantReadWriteLock L = new ReentrantReadWriteLock();
    private final Lock WLock = L.writeLock();
    private final Lock RLock = L.readLock();

    private GStore gStore = new GStore();

    private ShopStore shopStore = new ShopStore();

    private StageStore stageStore = new StageStore();

    @PostConstruct
    void init(){
        try {
            refresh();
        } catch (CacheInitFailedException e) {
            logger.error("商品缓存加载失败，退出系统，异常信息："+e.getMessage(),e);
            System.exit(-1);
        }

        ThreadPools.G_CACHE_EXECUTOR.scheduleWithFixedDelay(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            refresh();
                        } catch (Exception e) {
                            logger.error("商品缓存加载失败，异常信息："+e.getMessage(),e);
                        }
                    }
                },
                GlobalConfig.G_CACHE_REFRESH_PERIOD_MS,
                GlobalConfig.G_CACHE_REFRESH_PERIOD_MS,
                TimeUnit.MILLISECONDS
        );
    }

    private void refresh() throws CacheInitFailedException {
        try {
            List<CacGoods> gList = cacheLoader.loadEffGoods();
            EffMerSet merSet = cacheLoader.loadEffMers();


            WLock.lock();
            try {
                shopStore.refresh(merSet.getShops());
                stageStore.refresh(merSet.getStages());
                gStore.refresh(gList);
            } finally {
                WLock.unlock();;
            }
        } catch (Exception e) {
            throw new CacheInitFailedException(e.getMessage(),e);
        }
    }

    public List<SortableGoods> select(boolean isMemberUser,MerMatchReqVo merMatchReqVo, IGFilter filter, ISort sort, PageQueryVo page){
        RLock.lock();
        try {
            List<CacGoods> goodsList = gStore.select(filter);
            List<SortableGoods> agList = new ArrayList<>(goodsList.size());
            for(CacGoods g : goodsList){
                agList.add(toAGoods(isMemberUser,g));
            }

            SpecStockMap cssMap = stageStore.getStockMap(merMatchReqVo);
            SpecStockMap sssMap = shopStore.getStockMap(merMatchReqVo);
            for(SortableGoods ag : agList){

                //设置库存
                for(ASpecVo spec : ag.getSpecList()){
                    fillStocks(spec,cssMap,sssMap);
                }

                //设置距离,一定要放置在 设置库存后
                fillDistance(ag);

            }

            //sort
            sort.sort(agList);


            //page
            agList = page(agList,page);
            return agList;
        } finally {
            RLock.unlock();
        }

    }

    /**
     * 设置商品距离
     * @param ag
     */
    private void fillDistance(SortableGoods ag) {
        List<ASpecVo> sList = ag.getSpecList();
        Long min = null;
        //取距离最近的店铺的距离
        for(ASpecVo spec : sList){
            for(ASpecStockVo stock : spec.getStocks()){
                if(!stock.getShopFlag())
                    continue;
                if(stock.getDistance() != null){
                    if(min == null || min.compareTo(stock.getDistance()) > 0)
                        min = stock.getDistance();
                }
            }
        }

        ag.setDistance(min);
    }


    private SortableGoods toAGoods(boolean isUserMember,CacGoods g) {
        SortableGoods ag = new SortableGoods();
        ag.setId(g.getId());
        ag.setBrandName(g.getBrandName());
        ag.setName(g.getName());
        ag.setImg0(g.getImg0());
        ag.setSubTitle(g.getSubTitle());
        ag.setFreeShippingFlag(g.getFreeShippingFlag());
        ag.setMaxLimitCount(g.getMaxLimitCount());
        ag.setMinLimitCount(g.getMinLimitCount());
        ag.setNonMemberBuyFlag(g.getNonMemberBuyFlag());
        ag.setSumSaleCount(g.getSumSaleCount());
        ag.setStageCount(g.getStageCount());
        ag.setPrice(g.getPrice());
        ag.setOriPrice(g.getOriPrice());
        ag.setSaleCount(new Long(g.getSumSaleCount()));
        ag.setMinPrice(g.getPrice());
        ag.setMinOrigPrice(g.getOriPrice());
        ag.setMarketTime(g.getMarketTime());

        List<ASpecVo> specList = new LinkedList<>();
        ag.setSpecList(specList);
        for(CacSpec spec : g.getOriSpecList()){
            ASpecVo vo = new ASpecVo();
            vo.setId(spec.getId());
            vo.setPrice(spec.getPrice());
            vo.setOriPrice(spec.getOriPrice());
            vo.setName(spec.getName());
            vo.setImg(spec.getImg());
        }
        return ag;
    }

    private void fillStocks(ASpecVo spec, SpecStockMap cssMap, SpecStockMap sssMap) {
        List<ASpecStockVo> list = new LinkedList<>();

        //仓库
        SpecStock cs = cssMap.get(spec.getId());
        if(cs == null){
            ASpecStockVo empty = new ASpecStockVo();
            empty.setShopFlag(false);
            empty.setStock(BigDecimal.ZERO);
            list.add(empty);
        }else {
            ASpecStockVo stock = new ASpecStockVo();
            stock.setShopFlag(false);
            stock.setStock(cs.getStock());
            stock.setDistance(cs.getDistance());
        }

        //店铺
        SpecStock ss = sssMap.get(spec.getId());
        if(ss != null){
            ASpecStockVo stock = new ASpecStockVo();
            stock.setShopFlag(true);
            stock.setShopId(ss.getMerId());
            stock.setStock(cs.getStock());
            stock.setDistance(cs.getDistance());
        }

        spec.setStocks(list);

    }

    public List<SortableGoods> page(List<SortableGoods> list,PageQueryVo pageQueryVo){
        PageIndex pi = PageUtils.page(pageQueryVo,list.size());
        if(pi == null)
            return  new LinkedList<>();

        List<SortableGoods> rst = new LinkedList<>();
        for(int i=pi.getsIdx(); i <= pi.geteIdx(); i++ ){
            rst.add(list.get(i));
        }

        return rst;
    }


}
