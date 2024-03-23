package com.verificer.biz.biz.service.core.stock.impl;

import com.verificer.ErrCode;
import com.verificer.biz.beans.exceptions.StockInsufficientException;
import com.verificer.biz.beans.vo.req.StockUpdVo;
import com.verificer.biz.biz.entity.*;
import com.verificer.biz.biz.mapper.StockLogMapper;
import com.verificer.biz.biz.mapper.StockMapper;
import com.verificer.biz.biz.service.*;
import com.verificer.biz.biz.service.common.GoodsCommon;
import com.verificer.biz.biz.service.common.StockCommon;
import com.verificer.biz.biz.service.core.stock.StockCoreService;
import com.verificer.biz.biz.service.core.stock.entity.StockIdVo;
import com.verificer.biz.biz.service.core.stock.notify.StockNotifier;
import com.verificer.biz.biz.service.core.stock.notify.events.IStockListener;
import com.verificer.biz.biz.service.core.stock.notify.events.StockEvent;
import com.verificer.common.exception.BaseException;
import com.verificer.common.exception.BizErrMsgException;
import com.verificer.utils.SBigDecimalUtils;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class StockCoreServiceImpl implements StockCoreService {
    private StockNotifier notifier = new StockNotifier();

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

    @Autowired
    StockCommon stockCommon;

    @Autowired
    GoodsCommon goodsCommon;

    @Autowired
    StockLogMapper stockLogMapper;

    @Override
    public void addListener(IStockListener listener) {
        notifier.addListener(listener);
    }

    @Override
    public void addStageStockIfNotExist(Long goodsId, Long specId,String stageId) {
        addIfNotExist(goodsId,specId,true,stageId,BigDecimal.ZERO);
    }

    @Override
    public void addShopStockIfNotExist(String shopId, Long goodsId, Long specId) {

        addIfNotExist(goodsId,specId,false,shopId,BigDecimal.ZERO);
    }

    private void mCheck(Stock e){
        SCheckUtil.notEmpty(e.getGoodsId(),"stock.GoodsId");
        SCheckUtil.notEmpty(e.getSpecId(),"stock.SpecId");
        SCheckUtil.notEmpty(e.getStageFlag(),"stock.StageFlag");
        SCheckUtil.notEmpty(e.getRelId(),"stock.RelId");
        SCheckUtil.lgThan(e.getCount(),true, BigDecimal.ZERO,"stock.Count");
        SCheckUtil.notEmpty(e.getCreateTime(),"stock.CrateTime");
    }


    private void addIfNotExist(Long goodsId,Long specId,Boolean stageFlag,String relId,BigDecimal count ){
        Stock old = mapper.selectByRefIdAndSpecId(relId,specId);
        if(old != null)
            return;
        boolean skuFlag = goodsCommon.isGoodsSku(goodsId);

        Stock e = new Stock();
        e.setGoodsId(goodsId);
        e.setSkuFlag(skuFlag);
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
                stockCommon.formatCount(stock) );
    }

    /**
     * 修改库存
     * @param locked 已枷锁的stock记录
     * @param updVo
     */
    private void updStock(Stock locked,StockUpdVo updVo) throws StockInsufficientException {
        BigDecimal origStock = locked.getCount();
        if(updVo.isAddFlag() ){
            locked.setCount(locked.getCount().add(updVo.getCount()));
        }else {
            locked.setCount(locked.getCount().subtract(updVo.getCount()));
        }

        StockLog log = new StockLog();
        log.setStockId(locked.getId());
        log.setGoodsId(locked.getGoodsId());
        log.setSpecId(locked.getSpecId());
        log.setOpType(updVo.getOpType());
        log.setRelId(locked.getRelId());
        log.setCount(updVo.getCount());
        log.setBeforeStock(origStock);
        log.setAfterStock(locked.getCount());
        log.setCreateTime(System.currentTimeMillis());
        log.setRemark(updVo.getRemark());
        stockLogMapper.insert(log);

        //通知监听器
        notifier.triggerAll(new StockEvent(updVo.isAddFlag(),locked.getStageFlag(),log));


        if(locked.getSkuFlag() && SBigDecimalUtils.getRealScale(locked.getCount()) != 0)
            throw new BizErrMsgException("SKU Stock's count can not be floating point number");

        if(locked.getCount().compareTo(BigDecimal.ZERO) < 0 )
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
        List<StockIdVo> idVos = new LinkedList<>();
        for(StockUpdVo vo : updVos)
            idVos.add(new StockIdVo(vo.getRelId(),vo.getSpecId()));
        List<Stock> lockedStocks = getAndLockStocks(idVos);

        //处理库存增减
        for(StockUpdVo updVo : updVos){
            Stock locked = getStock(lockedStocks,updVo.getRelId(),updVo.getSpecId());
            updStock(locked,updVo);
        }
    }



    private List<Stock> getAndLockStocks(List<StockIdVo> idVos){
        //get And Lock,为了避免死锁，根据id顺序进行加锁
        Map<Long,Stock> map = new HashMap<>();
        List<Stock> stocks = new LinkedList<>();
        List<Stock> lockedStocks = new LinkedList<>();
        for(StockIdVo updVo : idVos){
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
        return lockedStocks;
    }

    @Override
    public void lockStocks(List<StockIdVo> idVos){
        getAndLockStocks(idVos);
    }

    @Override
    public Stock getStock(String relId, Long specId) {
        return mapper.selectByRefIdAndSpecId(relId,specId);
    }

    @Override
    public Stock getById(Long id) {
        return mapper.selectByPrimaryKey(id);
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
