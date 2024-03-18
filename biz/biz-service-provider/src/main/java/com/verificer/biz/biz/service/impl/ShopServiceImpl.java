package com.verificer.biz.biz.service.impl;

import com.verificer.ErrCode;
import com.verificer.GlobalConfig;
import com.verificer.base.sup.itf.BaseSupService;
import com.verificer.beans.AreaVo;
import com.verificer.biz.beans.vo.*;
import com.verificer.biz.beans.vo.ShopVo;
import com.verificer.biz.beans.vo.req.*;
import com.verificer.biz.biz.entity.*;
import com.verificer.biz.biz.entity.Shop;

import com.verificer.biz.biz.mapper.ShopInfoMapper;
import com.verificer.biz.biz.mapper.ShopMapper;
import com.verificer.biz.biz.pospay.YinBaoClient;
import com.verificer.biz.biz.service.ShopInfoService;
import com.verificer.biz.biz.service.ShopService;
import com.verificer.common.exception.BaseException;
import com.verificer.common.exception.BizErrMsgException;
import com.verificer.utils.AESUtils;
import com.verificer.utils.SBeanUtils;
import com.verificer.utils.UuidUtils;
import com.verificer.utils.check.SCheckUtil;
import com.verificer.utils.web.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ShopServiceImpl implements ShopService {
    @Autowired
    ShopMapper mapper;
    
    @Autowired
    ShopInfoMapper infoMapper;

    @Autowired
    BaseSupService baseSupService;

    @Autowired
    ShopInfoService shopInfoService;

    private ShopVo toVo(Shop e){
        if(e == null)
            return  null;
        ShopVo vo = new ShopVo();
        SBeanUtils.copyProperties2(e,vo);

        //shop info
        ShopInfo info = infoMapper.selectByShopId(e.getId());
        ShopComVo com = new ShopComVo();
        SBeanUtils.copyProperties2(info,com);
        vo.setCom(com);
        ShopLpVo lp = new ShopLpVo();
        SBeanUtils.copyProperties2(info,lp);
        vo.setLp(lp);
        ShopLsVo ls = new ShopLsVo();
        SBeanUtils.copyProperties2(info,ls);
        vo.setLs(ls);

        ShopBkVo bk = new ShopBkVo();
        bk.setUserName(info.getBkUserName());
        bk.setBankName(info.getBkBankName());
        bk.setCity(info.getBkCity());
        bk.setNum(info.getBkNum());
        vo.setBk(bk);

        ShopBkVo sbk = new ShopBkVo();
        sbk.setUserName(info.getSbkUserName());
        sbk.setBankName(info.getSbkBankName());
        sbk.setCity(info.getSbkCity());
        sbk.setNum(info.getSbkNum());
        vo.setSbk(sbk);

        return vo;
    }

    private List<ShopVo> toVoList(List<Shop> list){

        List<ShopVo> voList = new LinkedList<>();
        for(Shop e : list){
            voList.add(toVo(e));
        }
        return voList;
    }
    
    @Override
    public List<ShopVo> shopVoList(ShopListVo qryVo) {
        List<Shop> list = mapper.list(qryVo);
        return toVoList(list);
    }

    @Override
    public List<ShopVo> shopPage(ShopPageVo qryVo) {
        List<Shop> list = mapper.page(qryVo);

        return toVoList(list);
    }

    @Override
    public int shopCount(ShopPageVo qryVo) {
        return mapper.count(qryVo);
    }


    private void fillFields(Shop e){
        AreaVo a1 = baseSupService.areaGetByCode(e.getAdrArea1());
        if(a1 == null)
            throw new RuntimeException("Parameter adrArea1 error");

        AreaVo a2 = baseSupService.areaGetByCode(e.getAdrArea2());
        if(a2 == null)
            throw new RuntimeException("Parameter adrArea2 error");

        AreaVo a3 = baseSupService.areaGetByCode(e.getAdrArea3());
        if(a3 == null)
            throw new RuntimeException("Parameter adrArea3 error");

        e.setAdrArea1Name(a1.getName());
        e.setAdrArea2Name(a2.getName());
        e.setAdrArea3Name(a3.getName());
        e.setFullAddr(e.getAdrArea1Name()+e.getAdrArea2Name()+e.getAdrArea3Name()+ e.getAdrDetail());

    }

    private void mCheck(Shop e){
        SCheckUtil.notEmpty(e.getName(),"Name");
        SCheckUtil.notEmpty(e.getLoginName(),"Login Name"); //
        SCheckUtil.notEmpty(e.getLoginPwd(),"Login Pwd"); //
        SCheckUtil.notEmpty(e.getAvatarImg(),"Avatar Img");
        SCheckUtil.notEmpty(e.getBigImg(),"Big Img");
        SCheckUtil.notEmpty(e.getDescription(),"Description");
        SCheckUtil.inRangeAndNotNull(e.getCommissionRate(),true, BigDecimal.ZERO,false,BigDecimal.ONE,"Commission Rate");
        SCheckUtil.notEmpty(e.getShopLevel(),"Shop Level");
        SCheckUtil.notEmpty(e.getAdrArea1(),"Adr Area1");
        SCheckUtil.notEmpty(e.getAdrArea1Name(),"Adr Area1 Name");//
        SCheckUtil.notEmpty(e.getAdrArea2(),"Adr Area2");
        SCheckUtil.notEmpty(e.getAdrArea2Name(),"Adr Area2 Name");//
        SCheckUtil.notEmpty(e.getAdrArea3(),"Adr Area3");
        SCheckUtil.notEmpty(e.getAdrArea3Name(),"Adr Area3 Name");//
        SCheckUtil.notEmpty(e.getAdrDetail(),"Adr Detail");
        SCheckUtil.notEmpty(e.getFullAddr(),"Full Addr"); //
        SCheckUtil.notEmpty(e.getCpName(),"Cp Name");
        SCheckUtil.notEmpty(e.getCpMobile(),"Cp Mobile");
        SCheckUtil.notEmpty(e.getCpTel(),"Cp Tel");
        SCheckUtil.notEmpty(e.getLongitude(),"Longitude");
        SCheckUtil.notEmpty(e.getLatitude(),"Latitude");
        SCheckUtil.notEmpty(e.getFrozenFlag(),"Frozen Flag");//
        SCheckUtil.notEmpty(e.getCreateTime(),"Create Time");//
        SCheckUtil.notEmpty(e.getDelFlag(),"Del Flag");//
        SCheckUtil.notEmpty(e.getOpSTime(),"OpSTime");
        SCheckUtil.notEmpty(e.getOpETime(),"OpETime");

        SCheckUtil.notEmpty(e.getPosBaseUrl(),"Host : Port");
        if(!e.getPosBaseUrl().startsWith("https"))
            throw new BaseException(ErrCode.SHOP_BASE_URL_MUST_START_WITH_HTTPS);
        SCheckUtil.notEmpty(e.getSearchKey(),"Search Key");//
        SCheckUtil.notEmpty(e.getPosAppSecret(),"Pos App Secret");//
        SCheckUtil.notEmpty(e.getPosAppId(),"Pos App ID");//

    }

    private void uniqueCheck(Shop e){
        Shop old = mapper.selectByNameLimit1(e.getName());
        if(e.getId() == null){
            if(old != null)
                throw new BaseException(ErrCode.BRAND_NAME_REPEAT,new Object[]{e.getName()});
        }else {
            if(old != null && !old.getId().equals(e.getId()))
                throw new BaseException(ErrCode.BRAND_NAME_REPEAT,new Object[]{e.getName()});
        }
    }



    private String genSearchKey(Shop e){
        return e.getName();
    }

    private void buildChildRel(String parentId,List<String> childIds){
        mapper.setParentIdNull(parentId);

        if(childIds != null && childIds.size() > 0)
            mapper.setParentId(parentId,childIds);
    }
    
    @Override
    public void shopAdd(ShopFormVo formVo) {
        Shop e = new Shop();
        SBeanUtils.copyProperties2(formVo,e);
        e.setId(UuidUtils.newUuid());
        e.setLoginName(UuidUtils.newUuid());
        e.setLoginPwd(PasswordUtil.loginPassword("Dbg6688"));
        fillFields(e);
        e.setCreateTime(System.currentTimeMillis());
        e.setDelFlag(false);
        e.setFrozenFlag(false);
        e.setSearchKey(genSearchKey(e));

        mCheck(e);
        uniqueCheck(e);

        if(mapper.selectByPosAppIdLimit1(e.getPosAppId()) != null)
            throw new BaseException(ErrCode.POS_API_ID_ERR);

        if(!YinBaoClient.checkApiKey(e.getPosBaseUrl(),e.getPosAppId(),e.getPosAppSecret())){
            throw new BaseException(ErrCode.ADD_SHOP_POS_SETTING_ERR);
        }

        try {
            e.setPosAppSecret(AESUtils.encrypt(GlobalConfig.AES_SEED,e.getPosAppSecret()));
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(),ex);
        }
        mapper.insertSelective(e);

        e.setLoginName(e.getAdrArea2()+e.getId());
        mapper.updateByPrimaryKeySelective(e);

        //建立上下级关系
        buildChildRel(e.getId(),formVo.getChildIds());

        //创建shopInfo
        shopInfoService.add(e.getId(),formVo);
    }

    @Override
    public void shopUpd(ShopFormVo formVo) {
        SCheckUtil.notEmpty(formVo.getId(),"ID");

        Shop old = mapper.selectByPrimaryKey(formVo.getId());
        if(old == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        if(old.getDelFlag())
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        Shop e = new Shop();
        SBeanUtils.copyProperties2(formVo,e);
        e.setLoginName(old.getLoginName());
        e.setLoginPwd(old.getLoginPwd());
        fillFields(e);
        e.setCreateTime(old.getCreateTime());
        e.setDelFlag(old.getDelFlag());
        e.setFrozenFlag(old.getFrozenFlag());
        e.setSearchKey(genSearchKey(e));
        e.setPosAppId(old.getPosAppId());
        e.setPosBaseUrl(old.getPosBaseUrl());
        e.setPosAppSecret(old.getPosAppSecret());

        mCheck(e);
        uniqueCheck(e);
        mapper.updateByPrimaryKeySelective(e);

        //建立上下级关系
        buildChildRel(e.getId(),formVo.getChildIds());

        //修改shopInfo
        shopInfoService.update(formVo);
    }

    @Override
    public void shopDel(ShopDelVo delVo) {
        SCheckUtil.notEmpty(delVo.getId(),"ID");
        Shop e = mapper.selectByPrimaryKey(delVo.getId());
        e.setDelFlag(true);
        e.setDelTime(System.currentTimeMillis());
        mapper.updateByPrimaryKeySelective(e);
    }

    @Override
    public void shopUpdFrozenSta(ShopFrozenVo reqVo) {
        SCheckUtil.notEmpty(reqVo.getId(),"ID");
        Shop e = mapper.selectByPrimaryKey(reqVo.getId());
        e.setFrozenFlag(true);
        e.setFrozenTime(System.currentTimeMillis());
        mapper.updateByPrimaryKeySelective(e);
    }

    @Override
    public Shop getById(String relId) {
        return mapper.selectByPrimaryKey(relId);
    }

    @Override
    public List<Shop> shopList() {
        return mapper.selectAll();
    }
}
