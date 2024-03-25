package com.verificer.biz.biz.service.impl;

import com.verificer.ErrCode;
import com.verificer.base.sup.itf.BaseSupService;
import com.verificer.beans.AppReqVo;
import com.verificer.beans.AreaVo;
import com.verificer.biz.beans.enums.AddrDelType;
import com.verificer.biz.beans.vo.AppIdVo;
import com.verificer.biz.beans.vo.addr.AddrVo;
import com.verificer.biz.beans.vo.addr.req.AddrFormVo;
import com.verificer.biz.beans.vo.addr.req.AddrQryVo;
import com.verificer.biz.biz.entity.Addr;
import com.verificer.biz.biz.entity.Addr;
import com.verificer.biz.biz.entity.Shop;
import com.verificer.biz.biz.mapper.AddrMapper;
import com.verificer.biz.biz.pospay.entity.req.AddMemberReq;
import com.verificer.biz.biz.service.AddrService;
import com.verificer.biz.biz.service.common.UserCommon;
import com.verificer.common.exception.BaseException;
import com.verificer.common.exception.BizErrMsgException;
import com.verificer.utils.check.SCheckUtil;
import com.verificer.utils.reflect.SBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AddrServiceImpl implements AddrService {
    @Autowired
    AddrMapper mapper;

    @Autowired
    UserCommon userCommon;

    @Autowired
    BaseSupService baseSupService;

    @Override
    public Addr getById(Long addrId, Long userId) {

        Addr addr = mapper.selectByPrimaryKey(addrId);
        if(addr == null)
            throw new BizErrMsgException("Address Not exist");
        if(!addr.getUserId().equals(userId))
            throw new BizErrMsgException("Address Not exist");
        return addr;
    }

    private AddrVo toVo(Addr e){
        if(e == null)
            return  null;
        AddrVo vo = new AddrVo();
        SBeanUtils.copyProperties2(e,vo);
        return vo;
    }

    private List<AddrVo> toVoList(List<Addr> list){

        List<AddrVo> voList = new LinkedList<>();
        for(Addr e : list){
            voList.add(toVo(e));
        }
        return voList;
    }

    @Override
    public List<AddrVo> addrPage(AddrQryVo reqVo) {
        List<Addr> list = mapper.page(reqVo);

        return toVoList(list);
    }

    @Override
    public AddrVo addrDefault(AppReqVo reqVo) {
        SCheckUtil.notEmpty(reqVo.getUserId(),"User Id");
        Addr addr = mapper.selectByUserIdSortRutDesc(reqVo.getUserId());
        return toVo(addr);
    }


    private void mCheck(Addr e){

        SCheckUtil.notEmpty(e.getUserId(),"UserId");
        SCheckUtil.notEmpty(e.getAdrArea1(),"Adr Area1");
        SCheckUtil.notEmpty(e.getAdrArea1Name(),"Adr Area1 Name");//
        SCheckUtil.notEmpty(e.getAdrArea2(),"Adr Area2");
        SCheckUtil.notEmpty(e.getAdrArea2Name(),"Adr Area2 Name");//
        SCheckUtil.notEmpty(e.getAdrArea3(),"Adr Area3");
        SCheckUtil.notEmpty(e.getAdrArea3Name(),"Adr Area3 Name");//
        SCheckUtil.notEmpty(e.getAdrDetail(),"Adr Detail");
        SCheckUtil.notEmpty(e.getAdr(),"Adr");
        SCheckUtil.notEmpty(e.getFullAddr(),"Full Addr"); //
        SCheckUtil.notEmpty(e.getRcName(),"Cp Name");
        SCheckUtil.notEmpty(e.getMobile(),"Cp Mobile");
        SCheckUtil.notEmpty(e.getLongitude(),"Longitude");
        SCheckUtil.notEmpty(e.getLatitude(),"Latitude");
        SCheckUtil.notEmpty(e.getDelFlag(),"Del Flag");
        SCheckUtil.notEmpty(e.getCreateTime(),"CreateTime");
        SCheckUtil.notEmpty(e.getRecentUseTime(),"RecentUseTime");
    }

    private String genFullAddr(Addr addr){
        return addr.getAdr() + " " + addr.getAdrDetail();
    }

    private void fillFields(Addr e){
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
        e.setFullAddr(genFullAddr(e));

    }

    @Override
    public void addrAdd(AddrFormVo reqVo) {
        SCheckUtil.notEmpty(reqVo.getUserId(),"userId");
        userCommon.lockByUser(reqVo.getUserId());

        Addr addr = new Addr();
        SBeanUtils.copyProperties2(reqVo,addr);
        addr.setUserId(reqVo.getUserId());
        fillFields(addr);
        addr.setDelFlag(false);
        addr.setCreateTime(System.currentTimeMillis());
        addr.setRecentUseTime(addr.getCreateTime());

        mCheck(addr);

        mapper.insertSelective(addr);
    }


    @Override
    public void addrUpd(AddrFormVo reqVo) {
        SCheckUtil.notEmpty(reqVo.getUserId(),"userId");
        userCommon.lockByUser(reqVo.getUserId());

        SCheckUtil.notEmpty(reqVo.getId(),"id");
        Addr old = mapper.selectByPrimaryKey(reqVo.getId());
        if(old == null || !old.getUserId().equals(reqVo.getUserId()) || old.getDelFlag())
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        Addr addr = new Addr();
        SBeanUtils.copyProperties2(reqVo,addr);
        fillFields(addr);
        addr.setRecentUseTime(old.getRecentUseTime());
        addr.setDelFlag(true);
        addr.setDelType(AddrDelType.UPD.getValue());
        addr.setDelTime(System.currentTimeMillis());
        addr.setCreateTime(old.getCreateTime());

        mCheck(addr);
        mapper.updateByPrimaryKeySelective(addr);
    }

    @Override
    public void addrDel(AppIdVo reqVo) {
        SCheckUtil.notEmpty(reqVo.getUserId(),"userId");
        userCommon.lockByUser(reqVo.getUserId());

        SCheckUtil.notEmpty(reqVo.getId(),"id");
        userCommon.lockByUser(reqVo.getUserId());

        Addr old = mapper.selectByPrimaryKey(reqVo.getId());
        if(old == null || !old.getUserId().equals(reqVo.getUserId()) || old.getDelFlag())
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);


        old.setDelFlag(true);
        old.setDelType(AddrDelType.DEL.getValue());
        old.setDelTime(System.currentTimeMillis());

        mCheck(old);
        mapper.updateByPrimaryKeySelective(old);
    }


}
