package com.verificer.biz.biz.service.impl;

import com.verificer.ErrCode;
import com.verificer.base.sup.itf.BaseSupService;
import com.verificer.base.sup.itf.CfgCodes;
import com.verificer.biz.beans.vo.GoodsVo;
import com.verificer.biz.beans.vo.SpecVo;
import com.verificer.biz.beans.vo.goods.AGoodsVo;
import com.verificer.biz.beans.vo.goods.enums.GoodsSearchType;
import com.verificer.biz.beans.vo.goods.enums.GoodsSortType;
import com.verificer.biz.beans.vo.goods.req.AGoodsQryVo;
import com.verificer.biz.beans.vo.req.*;
import com.verificer.biz.biz.entity.Goods;
import com.verificer.biz.biz.mapper.GoodsMapper;
import com.verificer.biz.biz.service.*;
import com.verificer.biz.biz.service.cache.gache.GCache;
import com.verificer.biz.biz.service.cache.gache.filter.IGFilters;
import com.verificer.biz.biz.service.cache.gache.filter.impl.*;
import com.verificer.biz.biz.service.cache.gache.mer.MerMatchReqVo;
import com.verificer.biz.biz.service.cache.gache.sort.ISort;
import com.verificer.biz.biz.service.cache.gache.sort.SortBuilder;
import com.verificer.common.exception.BaseException;
import com.verificer.common.exception.BizErrMsgException;
import com.verificer.utils.PriceUtils;
import com.verificer.utils.reflect.SBeanUtils;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper mapper;

    @Autowired
    SpecService specService;

    @Autowired
    GoodsStaService goodsStaService;

    @Autowired
    PosGoodsSyncService posGoodsSyncService;

    @Autowired
    SortBuilder sortBuilder;

    @Autowired
    GCache gCache;

    @Autowired
    BaseSupService baseSupService;

    @Autowired
    ShopGoodsService shopGoodsService;


    @Override
    public List<GoodsVo> goodsAll() {
        List<GoodsVo> voList = mapper.allUnDel();

        for(GoodsVo vo : voList){
            List<SpecVo> specList = specService.getGoodsSpecVoList(vo.getId());
            vo.setSpecList(specList);
            vo.setPrice(calPriceRange(specList));
        }

        return voList;
    }

    @Override
    public List<GoodsVo> goodsPage(GoodsQryVo qryVo) {
        List<GoodsVo> voList = mapper.page(qryVo);

        for(GoodsVo vo : voList){
            List<SpecVo> specList = specService.getGoodsSpecVoList(vo.getId());
            vo.setSpecList(specList);
            vo.setPrice(calPriceRange(specList));
        }

        return voList;
    }

    private String calPriceRange(List<SpecVo> specList){
        BigDecimal max = null;
        BigDecimal min = null;
        if(specList.size() == 1)
            return PriceUtils.format(specList.get(0).getPrice());

        for(SpecVo spec : specList){
            if(min == null || spec.getPrice().compareTo(min) < 0)
                min = spec.getPrice();
            if(max == null || spec.getPrice().compareTo(min) > 0)
                max = spec.getPrice();
        }
        return PriceUtils.format(min) + "-" + PriceUtils.format(max);

    }

    @Override
    public int goodsCount(GoodsQryVo qryVo) {
        return mapper.count(qryVo);
    }

    private void mCheck(Goods e){
        SCheckUtil.notEmpty(e.getCategoryId(),"Category Id");
        SCheckUtil.notEmpty(e.getBrandId(),"Brand Id");
        SCheckUtil.notEmpty(e.getName(),"Name");
        SCheckUtil.notEmpty(e.getImgList(),"Img List");
        SCheckUtil.notEmpty(e.getSearchKey(),"Search Key"); //需
        SCheckUtil.notEmpty(e.getFreeShippingFlag(),"Free Shipping Flag");
        SCheckUtil.notEmpty(e.getSaleTimeOutFlag(),"SaleTimeOutFlag");
        if(e.getSaleTimeOutFlag() == true)
            SCheckUtil.notEmpty(e.getStopSaleTime(),"Stop Sale Time");
        SCheckUtil.notEmpty(e.getDetail(),"Detail");
        SCheckUtil.notEmpty(e.getDelFlag(),"Del Flag");  //需
        SCheckUtil.notEmpty(e.getRubbishFlag(),"Rubbish Flag"); //需
        SCheckUtil.notEmpty(e.getPosByWeightFlag(),"Pos By Weight Flag"); //需
        SCheckUtil.notEmpty(e.getAppSaleFlag(),"App Sale Flag");
        SCheckUtil.notEmpty(e.getShopSaleFlag(),"Shop Sale Flag");
        SCheckUtil.notEmpty(e.getNonMemberBuyFlag(),"Non Member Buy Flag");
        SCheckUtil.notEmpty(e.getSkuFlag(),"Sku Flag");
        SCheckUtil.notEmpty(e.getSubTitle(),"Sub Title");
    }

    private String genSearchKey(Goods goods){
        if(SStringUtils.isEmpty(goods.getKeyWord()))
            return goods.getName();
        return goods.getName() +"@"+goods.getKeyWord();
    }

    @Override
    public void goodsAdd(GoodsFormVo formVo) {
        Goods goods = new Goods();
        SBeanUtils.copyProperties2(formVo,goods);
        goods.setSearchKey(genSearchKey(goods));
        goods.setDelFlag(false);
        goods.setRubbishFlag(false);
        goods.setCreateTime(System.currentTimeMillis());
        goods.setSkuFlag(!goods.getPosByWeightFlag());

        mCheck(goods);
        mapper.insertSelective(goods);
        goodsStaService.add(goods.getId());

        specService.add(goods,formVo.getSpecList());
    }

    @Override
    public void goodsUpd(GoodsFormVo formVo) {
        SCheckUtil.notEmpty(formVo.getId(),"ID");

        Goods old = mapper.selectByPrimaryKey(formVo.getId());
        if(old == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        if(old.getDelFlag())
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        Goods e = new Goods();
        SBeanUtils.copyProperties2(formVo,e);
        e.setCreateTime(old.getCreateTime());
        e.setSearchKey(genSearchKey(e));
        e.setCreateTime(old.getCreateTime());
        e.setDelFlag(old.getDelFlag());
        e.setRubbishFlag(old.getRubbishFlag());
        e.setPosByWeightFlag(old.getPosByWeightFlag());
        e.setSkuFlag(!e.getPosByWeightFlag());


        //TODO 目前没做新增和删除检测
        List<SpecReqVo> list = formVo.getSpecList();
        specService.upd(e,list);


        mCheck(e);
        mapper.updateByPrimaryKeySelective(e);
        posGoodsSyncService.onGoodsUpdate(e);
    }

    @Override
    public void goodsRubbish(GoodsRubbishVo reqVo) {
        if(reqVo.getId() != null)
            rubbish(reqVo.getId());
        else {
            if(reqVo.getIds() == null || reqVo.getIds().size() == 0)
                throw new BizErrMsgException("请求参数id和ids不能同时为空");
            for(Long id : reqVo.getIds()){
                rubbish(id);
            }
        }

    }

    private void rubbish(Long id){
        SCheckUtil.notEmpty(id,"ID");

        Goods e = mapper.selectByPrimaryKey(id);
        if(e == null ||  e.getDelFlag() )
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        if(e.getRubbishFlag() == true  )
            throw new BaseException(ErrCode.OP_GOODS_RUBBISH_STATUS_ERR,new Object[]{e.getName()});
        e.setRubbishFlag(true);
        e.setSaleFlag(false);
        mapper.updateByPrimaryKeySelective(e);
        posGoodsSyncService.onGoodsUpdate(e);

    }

    @Override
    public void goodsRecover(GoodsRecoverVo reqVo) {
        if(reqVo.getId() != null)
            recover(reqVo.getId());
        else{
            if(reqVo.getIds() == null || reqVo.getIds().size() == 0)
                throw new BizErrMsgException("请求参数id和ids不能同时为空");
            for(Long id : reqVo.getIds()){
                recover(id);
            }
        }

    }

    private void recover(Long id){
        SCheckUtil.notEmpty(id,"ID");

        Goods e = mapper.selectByPrimaryKey(id);
        if(e == null || e.getDelFlag() )
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        if(e.getRubbishFlag() == false )
            throw new BaseException(ErrCode.OP_GOODS_RECOVER_STATUS_ERR,new Object[]{e.getName()});
        e.setRubbishFlag(false);
        e.setRubbishTime(System.currentTimeMillis());
        mapper.updateByPrimaryKeySelective(e);
        posGoodsSyncService.onGoodsUpdate(e);

    }

    @Override
    public void goodsDel(GoodsDelVo reqVo) {
        if(reqVo.getId() != null)
            del(reqVo.getId());
        else{
            if(reqVo.getIds() == null || reqVo.getIds().size() == 0)
                throw new BizErrMsgException("请求参数id和ids不能同时为空");

            for(Long id : reqVo.getIds()){
                del(id);
            }
        }

    }

    private void del(Long id){
        SCheckUtil.notEmpty(id,"ID");

        Goods e = mapper.selectByPrimaryKey(id);
        if(e == null || e.getDelFlag() )
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        if(e.getRubbishFlag() == false )
            throw new BaseException(ErrCode.OP_GOODS_DEL_STATUS_ERR,new Object[]{e.getName()});
        e.setDelFlag(true);
        e.setDelTime(System.currentTimeMillis());
        e.setSaleFlag(false);
        mapper.updateByPrimaryKeySelective(e);
        posGoodsSyncService.onGoodsUpdate(e);

    }

    @Override
    public void goodsUpdSaleFlag(GoodsUpdSaleFlagVo reqVo) {
        SCheckUtil.notEmpty(reqVo.getId(),"ID");
        SCheckUtil.notEmpty(reqVo.getSaleFlag(),"Sale Flag");

        Goods e = mapper.selectByPrimaryKey(reqVo.getId());
        if(e == null || e.getDelFlag() )
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        if(reqVo.getSaleFlag() && e.getRubbishFlag()){
            throw new BaseException(ErrCode.RUBBISH_GOODS_CAN_NOT_ON_SALE);
        }
        if(reqVo.getSaleFlag() && e.getDelFlag()){
            throw new BaseException(ErrCode.DEL_GOODS_CAN_NOT_ON_SALE);
        }
        e.setSaleFlag(reqVo.getSaleFlag());
        mapper.updateByPrimaryKeySelective(e);
        posGoodsSyncService.onGoodsUpdate(e);

    }

    @Override
    public Goods getById(Long goodsId) {
        return mapper.selectByPrimaryKey(goodsId);
    }

    @Override
    public boolean isGoodsOnSale(Goods goods) {
        if(goods.getDelFlag() || goods.getRubbishFlag() || !goods.getSaleFlag())
            return false;
        return true;
    }

    @Override
    public List<Goods> getAllEffGoods() {
        return mapper.selectEffGoods();
    }

    @Override
    public List< AGoodsVo> appGoodsList(AGoodsQryVo qryVo) {
        SCheckUtil.notEmpty(qryVo.getSortType(),"sortType");
        SCheckUtil.notEmpty(qryVo.getSortType(),"userMemberFlag");

        //排序方式
        ISort sort = null;
        Integer sortType = qryVo.getSortType();
        if(GoodsSortType.MUL.getValue() == sortType){
            sort = sortBuilder.mulSort(qryVo.getUserMemberFlag());
        }else if(GoodsSortType.PRICE.getValue() == sortType){
            sort = sortBuilder.priceSort(qryVo.getUserMemberFlag());
        }else if(GoodsSortType.SALES.getValue() == sortType){
            sort = sortBuilder.salesSort();
        }else if(GoodsSortType.MARKET_TIME.getValue() == sortType){
            sort = sortBuilder.marketTimeSort();
        }else {
            throw new BizErrMsgException("非法的参数值sortType="+sortType);
        }


        MerMatchReqVo merMatchReqVo = new MerMatchReqVo(
                SStringUtils.isEmpty(qryVo.getShopId()) ? null : qryVo.getShopId(),
                qryVo.getLongitude(),
                qryVo.getLatitude(),
                BigDecimal.ONE,
                Long.parseLong(baseSupService.getCfg(CfgCodes.SHOP_SRV_DISTANCE)));

        IGFilters filter = new IGFilters();
        if(qryVo.getNonMemberFlag() != null && qryVo.getNonMemberFlag())
            filter.add(new NonMemberFilter());
        if(!SStringUtils.isEmpty(qryVo.getShopId())){
            List<Long> ids = shopGoodsService.getEffGoodsIsByShopId(qryVo.getShopId());
            filter.add(new IdsFilter(ids));
        }
        if(qryVo.getCatId() != null)
            filter.add(new CatIdFilter(qryVo.getCatId()));
        if(qryVo.getSearchType() != null && !SStringUtils.isEmpty(qryVo.getsKey())){
            if(GoodsSearchType.GOODS.getValue() == qryVo.getSearchType()){
                filter.add(new GoodsKeyFilter(qryVo.getsKey()));
            }else if(GoodsSearchType.CAT.getValue() == qryVo.getSearchType()){
                filter.add(new CatKeyFilter(qryVo.getsKey()));
            }else {
                throw new BizErrMsgException("非法的参数值searchType="+sortType);

            }
        }
        if(qryVo.getExcludeSaleOutFlag())
            filter.add(new ExcludeSaleOutFilter());


        return gCache.select(qryVo.getUserMemberFlag(),merMatchReqVo,filter,sort,qryVo);

    }


}
