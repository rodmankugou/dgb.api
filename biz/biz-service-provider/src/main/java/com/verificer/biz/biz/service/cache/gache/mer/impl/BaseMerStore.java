package com.verificer.biz.biz.service.cache.gache.mer.impl;

import com.verificer.biz.beans.enums.MerType;
import com.verificer.biz.biz.service.cache.gache.mer.IMerStore;
import com.verificer.biz.biz.service.cache.gache.mer.MerMatchReqVo;
import com.verificer.biz.biz.service.cache.vo.CacMer;
import com.verificer.biz.biz.service.cache.vo.CacStock;
import com.verificer.biz.biz.service.cache.vo.SpecStock;
import com.verificer.utils.map.baidu.BaiduMapHelper;

import java.util.*;

public abstract class BaseMerStore implements IMerStore {
    private  static Comparator<SortMer> comparator = new Comparator<SortMer>(){
        @Override
        public int compare(SortMer o1, SortMer o2) {
            if(o1.distance == null || o2.distance == null)
                return 0;
            return o1.distance.compareTo(o2.distance);
        }
    };

    protected List<? extends CacMer> merList;

    abstract boolean isShop();

    /**
     * 过滤
     * @param reqVo
     * @return
     */
    abstract List<? extends CacMer> filter(MerMatchReqVo reqVo,List<? extends CacMer> merList);


    @Override
    public void refresh(List<? extends CacMer> merList) {
        this.merList = merList;
    }

    static class SortMer{
        private Long distance;
        private CacMer mer;

        public SortMer(Long distance, CacMer mer) {
            this.distance = distance;
            this.mer = mer;
        }
    }


    @Override
    public SpecStockMap getStockMap(MerMatchReqVo reqVo){
        List<SortMer> smList = getMatchMer(reqVo);
        //排序
        Collections.sort(smList,comparator);

        Map<Long, SpecStock> map = new HashMap<>();
        for(SortMer sm : smList){
            CacMer mer = sm.mer;
            for(CacStock stock : mer.getStockList()){

                if(map.containsKey(stock.getSpecId())){
                    SpecStock ss = map.get(stock.getSpecId());
                    if(ss.getStock().compareTo(reqVo.getPriorityThresholdCount() ) >= 0
                            || stock.getStock().compareTo(reqVo.getPriorityThresholdCount() ) < 0)
                        continue;

                    map.put(stock.getSpecId(),new SpecStock(isShop(),mer.getId(),stock.getStock(),sm.distance));

                }else {
                    map.put(stock.getSpecId(),new SpecStock(isShop(),mer.getId(),stock.getStock(),sm.distance));
                }

            }
        }
        return new SpecStockMap(map);
    }

    /**
     * 获取距离配置的仓库
     * @param reqVo
     * @return
     */
    private List<SortMer> getMatchMer(MerMatchReqVo reqVo){
        List<SortMer> targetList = new ArrayList<>();
        List<? extends CacMer> mList = filter(reqVo,merList);
        for(CacMer mer : mList){
            Long distance = BaiduMapHelper.getDistance(reqVo.getLongitude(), reqVo.getLatitude(),mer.getLongitude(),mer.getLatitude());
            //无定位用户看不到店铺商品
            if(distance == null){
                if(isShop())
                    continue;
                targetList.add(new SortMer(distance,mer));
            }else {
                if(distance <= reqVo.getDistanceLimit())
                    targetList.add(new SortMer(distance,mer));
            }
        }

        return targetList;
    }



}
