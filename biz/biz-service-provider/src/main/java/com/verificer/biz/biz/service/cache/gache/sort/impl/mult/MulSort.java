package com.verificer.biz.biz.service.cache.gache.sort.impl.mult;

import com.verificer.biz.biz.service.cache.gache.sort.ISort;
import com.verificer.biz.biz.service.cache.gache.sort.SortBuilder;
import com.verificer.biz.biz.service.cache.gache.sort.impl.DistanceSort;
import com.verificer.biz.biz.service.cache.vo.CacGoods;
import com.verificer.biz.biz.service.cache.vo.SortableGoods;

import java.math.BigDecimal;
import java.util.*;

public class MulSort implements ISort {
    private  static Comparator<SortableGoods> comparator = new Comparator<SortableGoods>(){
        @Override
        public int compare(SortableGoods o1, SortableGoods o2) {

            return o2.getMulSortVal().compareTo(o1.getMulSortVal());
        }
    };

    private SortBuilder builder;
    private boolean isMember;
    private int priceWeight;
    private int salesWeight;
    private int distanceWeight;
    private int shopWeight;

    public MulSort(SortBuilder builder,boolean isMember, int priceWeight, int salesWeight, int distanceWeight, int shopWeight) {
        this.builder = builder;
        this.isMember = isMember;
        this.priceWeight = priceWeight;
        this.salesWeight = salesWeight;
        this.distanceWeight = distanceWeight;
        this.shopWeight = shopWeight;
    }


    static enum Type {
        PRICE(1),
        SALES(2),
        DISTANCE(3),
        SHOP(4);

        private int value;

        Type(int val) {
            value = val;
        }


        public int getValue() {
            return value;
        }


    }

    @Override
    public void sort(List<SortableGoods> list) {


       calRank(list,Type.PRICE);
       calRank(list,Type.SALES);
       calRank(list,Type.DISTANCE);
       calRank(list,Type.SHOP);




        calSortVal(list);
        Collections.sort(list,comparator);

    }

    private void calSortVal(List<SortableGoods> gList) {
        for(SortableGoods g : gList){
            BigDecimal val = BigDecimal.ZERO;
            val = val.add(new BigDecimal((0-g.getMulParam().price)*priceWeight));
            val = val.add(new BigDecimal((0-g.getMulParam().sale)*salesWeight));
            val = val.add(new BigDecimal((0-g.getMulParam().distance)*distanceWeight));
            val = val.add(new BigDecimal((0-g.getMulParam().shop)*shopWeight));
            g.setMulSortVal(val);
        }
    }

    private void calRank(List<SortableGoods> gList, Type type){
        if(type.equals(Type.PRICE) ){
            ISort sort = builder.priceSort(isMember);
            sort.sort(gList);
            adjustRank(gList,type);
            setSortParam(gList,type);
        }else if(type.equals(Type.SALES)){
            ISort sort = builder.salesSort();
            sort.sort(gList);
            adjustRank(gList,type);
            setSortParam(gList,type);
        }else if(type.equals(Type.DISTANCE)){
            ISort sort = builder.distanceSort();
            sort.sort(gList);
            adjustRank(gList,type);
            setSortParam(gList,type);
        }else if(type.equals(Type.SHOP)){
            setShopRank(gList);
            Collections.sort(gList, new Comparator<SortableGoods>() {
                @Override
                public int compare(SortableGoods o1, SortableGoods o2) {
                    return o1.getRank().compareTo(o2.getRank());
                }
            });
            adjustRank(gList,Type.SHOP);
            setSortParam(gList,type);
        }
    }


    private void adjustRank(List<SortableGoods> sortedList,Type type){
        if(sortedList.size() == 0)
            return;

        if(type.equals(Type.SHOP)){
            setShopRank(sortedList);
            return;
        }

        sortedList.get(0).setRank(1);

        for(int i=1; i< sortedList.size(); i++){
            SortableGoods pre = sortedList.get(i-1);
            SortableGoods g = sortedList.get(i);

            if(type.equals(Type.PRICE) ){
                boolean isEqPre ;
                if(isMember)
                    isEqPre = g.getMinPrice().compareTo(pre.getMinPrice()) == 0;
                else
                    isEqPre = g.getMinOrigPrice().compareTo(pre.getMinOrigPrice()) == 0;
                g.setRank(isEqPre ? pre.getRank() : i+1);
            }else if(type.equals(Type.SALES)){
                g.setRank(g.getSaleCount() == pre.getSaleCount() ? pre.getRank() : i+1);
            }else if(type.equals(Type.DISTANCE)){
                g.setRank(DistanceSort.compare(pre,g)  == 0 ? pre.getRank() : i+1);
            }else {
                throw new RuntimeException("不支持的排序类型");
            }
        }
    }

    private void setShopRank(List<SortableGoods> sortedList){
        for(SortableGoods g : sortedList){
            if(g.getDistance() != null)
                g.setRank(1);
            else
                g.setRank(2);
        }
    }

    private void setSortParam(List<SortableGoods> sortedList,Type type){

        for(int i=0; i<sortedList.size(); i++){
            SortableGoods g = sortedList.get(i);
            MulParam mulParam = g.getMulParam();
            if(type.equals(Type.PRICE) ){
                mulParam.price = g.getRank();
            }else if(type.equals(Type.SALES)){
                mulParam.sale = g.getRank();
            }else if(type.equals(Type.DISTANCE)){
                mulParam.distance = g.getRank();
            }else if(type.equals(Type.SHOP)){
                mulParam.shop = g.getRank();
            }
        }

    }




}
