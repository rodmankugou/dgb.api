package com.verificer.biz.biz.service.impl;

import com.verificer.ErrCode;
import com.verificer.GlobalConfig;
import com.verificer.biz.beans.vo.GoodsVo;
import com.verificer.biz.beans.vo.SpecVo;
import com.verificer.biz.beans.vo.req.*;
import com.verificer.biz.biz.entity.Brand;
import com.verificer.biz.biz.entity.Goods;
import com.verificer.biz.biz.entity.Spec;
import com.verificer.biz.biz.mapper.GoodsMapper;
import com.verificer.biz.biz.service.GoodsService;
import com.verificer.biz.biz.service.GoodsStaService;
import com.verificer.biz.biz.service.PosGoodsSyncService;
import com.verificer.biz.biz.service.SpecService;
import com.verificer.common.exception.BaseException;
import com.verificer.common.exception.BizErrMsgException;
import com.verificer.utils.PriceUtils;
import com.verificer.utils.SBeanUtils;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.check.SCheckUtil;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.LinkedList;
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

        mCheck(goods);
        mapper.insertSelective(goods);
        goodsStaService.add(goods.getId());

        specService.add(goods.getId(),formVo.getSpecList());
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

        //TODO 目前没做新增和删除检测
        List<SpecReqVo> list = formVo.getSpecList();
        specService.upd(formVo.getId(),list);


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
            throw new BizErrMsgException(ErrCode.RUBBISH_GOODS_CAN_NOT_ON_SALE);
        }
        if(reqVo.getSaleFlag() && e.getDelFlag()){
            throw new BizErrMsgException(ErrCode.DEL_GOODS_CAN_NOT_ON_SALE);
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


}
