package com.verificer.biz.biz.service.impl;

import com.verificer.ErrCode;
import com.verificer.biz.beans.exceptions.StockInsufficientException;
import com.verificer.biz.beans.vo.req.StockUpdVo;
import com.verificer.biz.biz.entity.*;
import com.verificer.biz.biz.mapper.StockMapper;
import com.verificer.biz.biz.service.*;
import com.verificer.common.exception.BaseException;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class StockCoreServiceImpl implements StockCoreService {

    @Autowired
    StockMapper mapper;

    @Autowired
    StageService stageService;

    @Autowired
    ShopService shopService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    SpecService specService;

    @Override
    public void addStageStockIfNotExist(Long goodsId, Long specId, List<String> stageIds) {
        for(String stageId : stageIds){
            Stage stage = stageService.getById(stageId);
            if(stage == null)
                throw new RuntimeException("Stage not exist!,id="+stageId);

            addIfNotExist(goodsId,specId,true,stageId,0);
        }
    }

    @Override
    public void addShopStockIfNotExist(String shopId, Long goodsId, Long specId) {
        addIfNotExist(goodsId,specId,false,shopId,0);
    }

    private void mCheck(Stock e){
        SCheckUtil.notEmpty(e.getGoodsId(),"stock.GoodsId");
        SCheckUtil.notEmpty(e.getSpecId(),"stock.SpecId");
        SCheckUtil.notEmpty(e.getStageFlag(),"stock.StageFlag");
        SCheckUtil.notEmpty(e.getRelId(),"stock.RelId");
        SCheckUtil.lgThan(e.getCount(),true,0,"stock.Count");
        SCheckUtil.notEmpty(e.getCreateTime(),"stock.CrateTime");
    }


    private void addIfNotExist(Long goodsId,Long specId,Boolean stageFlag,String relId,Integer count ){
        Stock old = mapper.selectByRefIdAndSpecId(relId,specId);
        if(old != null)
            return;
        Stock e = new Stock();
        e.setGoodsId(goodsId);
        e.setSpecId(specId);
        e.setStageFlag(stageFlag);
        e.setStageFlag(stageFlag);
        e.setRelId(relId);
        e.setCount(count);
        e.setCreateTime(System.currentTimeMillis());

        mCheck(e);
        mapper.insertSelective(e);
    }

    private StockInsufficientException buildException(Stock stock){
        String refName = null;
        if(stock.getStageFlag()){
            Stage stage = stageService.getById(stock.getRelId());
            refName = stage.getName();
        }else {
            Shop shop = shopService.getById(stock.getRelId());
            refName = shop.getName();
        }
        Goods goods = goodsService.getById(stock.getGoodsId());
        Spec spec = specService.getById(stock.getSpecId());

        return new StockInsufficientException(
                stock.getStageFlag(),
                stock.getRelId(),
                refName,
                stock.getGoodsId(),
                goods.getName(),
                stock.getSpecId(),
                spec.getName(),
                stock.getCount());
    }

    /**
     * 修改库存
     * @param locked 已枷锁的stock记录
     * @param updVo
     */
    private void updStock(Stock locked,StockUpdVo updVo) throws StockInsufficientException {
        if(updVo.isAddFlag() ){
            locked.setCount(locked.getCount() + updVo.getCount());
        }else {
            locked.setCount(locked.getCount() - updVo.getCount());
        }

        if(locked.getCount() < 0 )
            throw buildException(locked);

        mapper.updateByPrimaryKey(locked);
    }




    public void modifyStock(StockUpdVo updVo) throws StockInsufficientException {
        Stock stock = mapper.selectByRefIdAndSpecId(updVo.getRelId(),updVo.getSpecId());
        if(stock == null)
            throw new BaseException(ErrCode.SERVER_ERROR);
        Stock locked = mapper.selectByIdForUpd(stock.getId());

        updStock(locked,updVo);
    }


    /**
     * 该方法增加了防死锁设计
     * @param updVos
     */
    @Override
    public void modifyStock(List<StockUpdVo> updVos) throws StockInsufficientException {
        if(updVos.size() == 0)
            return;

        //get And Lock,为了避免死锁，根据id顺序进行加锁
        Map<Long,Stock> map = new HashMap<>();
        List<Stock> stocks = new LinkedList<>();
        List<Stock> lockedStocks = new LinkedList<>();
        for(StockUpdVo updVo : updVos){
            Stock stock = mapper.selectByRefIdAndSpecId(updVo.getRelId(),updVo.getSpecId());
            if(stock == null)
                throw new BaseException(ErrCode.SERVER_ERROR);
            if(!map.containsKey(stock.getId())){
                stocks.add(stock);
                map.put(stock.getId(),stock);
            }
        }
        Collections.sort(stocks, new Comparator<Stock>() {
            @Override
            public int compare(Stock o1, Stock o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });
        for(Stock stock : stocks){
            Stock lockedStock = mapper.selectByIdForUpd(stock.getId());
            if(lockedStock == null)
                throw new BaseException(ErrCode.SERVER_ERROR);
            lockedStocks.add(lockedStock);
        }

        //处理库存增减
        for(StockUpdVo updVo : updVos){
            Stock locked = getStock(lockedStocks,updVo.getRelId(),updVo.getSpecId());
            updStock(locked,updVo);
        }
    }

    @Override
    public boolean isStockExist(String relId, Long goodsId, Long specId) {
        return mapper.selectByRefIdAndSpecId(relId,specId) != null;
    }


    private Stock getStock(List<Stock> stocks,String refId,Long specId){
        for(Stock stock : stocks){
            if(stock.getRelId().equals(refId) && stock.getSpecId().equals(specId))
                return stock;
        }

        return null;
    }
}
