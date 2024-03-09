package com.verificer.biz.biz.service.impl;

import com.verificer.ErrCode;
import com.verificer.biz.beans.vo.GoodsVo;
import com.verificer.biz.beans.vo.SpecVo;
import com.verificer.biz.beans.vo.req.*;
import com.verificer.biz.biz.entity.Brand;
import com.verificer.biz.biz.entity.Goods;
import com.verificer.biz.biz.mapper.GoodsMapper;
import com.verificer.biz.biz.service.GoodsService;
import com.verificer.biz.biz.service.GoodsStaService;
import com.verificer.biz.biz.service.SpecService;
import com.verificer.common.exception.BaseException;
import com.verificer.common.exception.BizErrMsgException;
import com.verificer.utils.SBeanUtils;
import com.verificer.utils.check.SCheckUtil;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public List<GoodsVo> goodsPage(GoodsQryVo qryVo) {
        List<GoodsVo> voList = mapper.page(qryVo);
        for(GoodsVo vo : voList){
            List<SpecVo> specList = specService.getGoodsSpecVoList(vo.getId());
            vo.setSpecList(specList);
        }
        return voList;
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
        SCheckUtil.notEmpty(e.getKeyWord(),"Key Word");
        SCheckUtil.notEmpty(e.getSearchKey(),"Search Key"); //需
        SCheckUtil.notEmpty(e.getFreeShippingFlag(),"Free Shipping Flag");
        if(e.getFreeShippingFlag() != null)
            SCheckUtil.notEmpty(e.getStopSaleTime(),"Stop Sale Time");
        SCheckUtil.notEmpty(e.getDetail(),"Detail");
        SCheckUtil.notEmpty(e.getDelFlag(),"Del Flag");  //需

        SCheckUtil.notEmpty(e.getRubbishFlag(),"Rubbish Flag"); //需
        SCheckUtil.notEmpty(e.getPosByWeightFlag(),"Rubbish Flag"); //需
    }

    private String genSearchKey(Goods goods){
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


        mCheck(e);
        mapper.updateByPrimaryKeySelective(e);
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
        mapper.updateByPrimaryKeySelective(e);
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
        mapper.updateByPrimaryKeySelective(e);
    }

    @Override
    public void goodsUpdSaleFlag(GoodsUpdSaleFlagVo reqVo) {
        SCheckUtil.notEmpty(reqVo.getId(),"ID");
        SCheckUtil.notEmpty(reqVo.getSaleFlag(),"Sale Flag");

        Goods e = mapper.selectByPrimaryKey(reqVo.getId());
        if(e == null || e.getDelFlag() )
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        e.setSaleFlag(reqVo.getSaleFlag());
        mapper.updateByPrimaryKeySelective(e);
    }

    @Override
    public Goods getById(Long goodsId) {
        return mapper.selectByPrimaryKey(goodsId);
    }
}
