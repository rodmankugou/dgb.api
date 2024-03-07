package com.verificer.biz.biz.service.impl;

import com.verificer.ErrCode;
import com.verificer.biz.beans.vo.ShopBkVo;
import com.verificer.biz.beans.vo.req.ShopFormVo;
import com.verificer.biz.biz.entity.ShopInfo;
import com.verificer.biz.biz.mapper.ShopInfoMapper;
import com.verificer.biz.biz.service.ShopInfoService;
import com.verificer.common.exception.BaseException;
import com.verificer.utils.SBeanUtils;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ShopInfoServiceImpl implements ShopInfoService {
    @Autowired
    ShopInfoMapper mapper;

    private void fillFields(ShopInfo e,ShopFormVo formVo){
        if(formVo.getCom() != null)
            SBeanUtils.copyProperties2(formVo.getCom(),e);
        if(formVo.getLp() != null)
            SBeanUtils.copyProperties2(formVo.getLp(),e);
        if(formVo.getLs() != null)
            SBeanUtils.copyProperties2(formVo.getLs(),e);

        if(formVo.getBk() != null){
            ShopBkVo bk = formVo.getBk();
            e.setBkBankName(bk.getBankName());
            e.setBkCity(bk.getCity());
            e.setBkNum(bk.getNum());
            e.setBkUserName(bk.getUserName());
        }

        if(formVo.getSbk() != null){
            ShopBkVo sbk = formVo.getSbk();
            e.setSbkBankName(sbk.getBankName());
            e.setSbkCity(sbk.getCity());
            e.setSbkNum(sbk.getNum());
            e.setSbkUserName(sbk.getUserName());
        }
    }

    @Override
    public void add(String shopId,ShopFormVo formVo) {
        SCheckUtil.notEmpty(shopId,"ID");

        ShopInfo e = new ShopInfo();
        e.setShopId(shopId);
        fillFields(e,formVo);
        mapper.insertSelective(e);
    }

    @Override
    public void update(ShopFormVo formVo) {
        SCheckUtil.notEmpty(formVo.getId(),"ID");

        ShopInfo old = mapper.selectByShopId(formVo.getId());
        if(old == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        ShopInfo e = new ShopInfo();
        e.setId(old.getId());
        e.setShopId(old.getShopId());
        fillFields(e,formVo);
        mapper.updateByPrimaryKeySelective(e);
    }
}
