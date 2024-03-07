package com.verificer.biz.biz.service.impl;

import com.mchange.v2.uid.UidUtils;
import com.verificer.ErrCode;
import com.verificer.base.sup.itf.BaseSupService;
import com.verificer.beans.AreaVo;
import com.verificer.biz.beans.vo.BrandVo;
import com.verificer.biz.beans.vo.StageVo;
import com.verificer.biz.beans.vo.req.StageFormVo;
import com.verificer.biz.beans.vo.req.StagePageVo;
import com.verificer.biz.biz.entity.Brand;
import com.verificer.biz.biz.entity.Stage;
import com.verificer.biz.biz.mapper.StageMapper;
import com.verificer.biz.biz.service.StageService;
import com.verificer.common.exception.BaseException;
import com.verificer.utils.SBeanUtils;
import com.verificer.utils.UuidUtils;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class StageServiceImpl implements StageService {
    @Autowired
    StageMapper mapper;

    @Autowired
    BaseSupService baseSupService;

    @Override
    public Stage getById(String stageId) {
        return mapper.selectByPrimaryKey(stageId);
    }



    private StageVo toVo(Stage e){
        if(e == null)
            return  null;
        StageVo vo = new StageVo();
        SBeanUtils.copyProperties2(e,vo);
        return vo;
    }

    private List<StageVo> toVoList(List<Stage> list){

        List<StageVo> voList = new LinkedList<>();
        for(Stage e : list){
            StageVo vo = new StageVo();
            SBeanUtils.copyProperties2(e,vo);
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public List<StageVo> stageList() {
        List<Stage> list = mapper.list();
        return toVoList(list);
    }

    @Override
    public List<StageVo> stagePage(StagePageVo qryVo) {
        List<Stage> list = mapper.page(qryVo);
        return toVoList(list);
    }

    @Override
    public int stageCount(StagePageVo qryVo) {
        return mapper.count(qryVo);
    }

    private void mCheck(Stage e){
        SCheckUtil.notEmpty(e.getName(),"Name");
        SCheckUtil.notEmpty(e.getCpName(),"CpName");
        SCheckUtil.notEmpty(e.getCpMobile(),"CpMobile");
        SCheckUtil.notEmpty(e.getAdrArea1(),"AdrArea1");
        SCheckUtil.notEmpty(e.getAdrArea1Name(),"AdrArea1Name"); //
        SCheckUtil.notEmpty(e.getAdrArea2(),"AdrArea2");
        SCheckUtil.notEmpty(e.getAdrArea2Name(),"AdrArea2Name"); //
        SCheckUtil.notEmpty(e.getAdrArea3(),"AdrArea3");
        SCheckUtil.notEmpty(e.getAdrArea3Name(),"AdrArea3Name"); //
        SCheckUtil.notEmpty(e.getAdrDetail(),"AdrDetail");
        SCheckUtil.notEmpty(e.getFullAddr(),"FullAddr"); //
        SCheckUtil.notEmpty(e.getLongitude(),"Longitude");
        SCheckUtil.notEmpty(e.getLatitude(),"Latitude");
        SCheckUtil.notEmpty(e.getCreateTime(),"Create Time"); //
    }

    private void uniqueCheck(Stage e){
        Stage old = mapper.selectByNameLimit1(e.getName());
        if(e.getId() == null){
            if(old != null)
                throw new BaseException(ErrCode.BRAND_NAME_REPEAT,new Object[]{e.getName()});
        }else {
            if(old != null && !old.getId().equals(e.getId()))
                throw new BaseException(ErrCode.BRAND_NAME_REPEAT,new Object[]{e.getName()});
        }
    }

    private void fillFields(Stage stage){
        AreaVo a1 = baseSupService.areaGetByCode(stage.getAdrArea1());
        if(a1 == null)
            throw new RuntimeException("Parameter adrArea1 error");

        AreaVo a2 = baseSupService.areaGetByCode(stage.getAdrArea2());
        if(a2 == null)
            throw new RuntimeException("Parameter adrArea2 error");

        AreaVo a3 = baseSupService.areaGetByCode(stage.getAdrArea3());
        if(a3 == null)
            throw new RuntimeException("Parameter adrArea3 error");

        stage.setAdrArea1Name(a1.getName());
        stage.setAdrArea2Name(a2.getName());
        stage.setAdrArea3Name(a3.getName());
        stage.setFullAddr(stage.getAdrArea1Name()+stage.getAdrArea2Name()+stage.getAdrArea3Name()+ stage.getAdrDetail());

    }

    @Override
    public void stageAdd(StageFormVo formVo) {
        Stage e = new Stage();
        SBeanUtils.copyProperties2(formVo,e);
        e.setCreateTime(System.currentTimeMillis());
        e.setId(UuidUtils.newUuid());
        fillFields(e);


        mCheck(e);
        uniqueCheck(e);
        mapper.insertSelective(e);
    }

    @Override
    public void stageUpd(StageFormVo formVo) {
        SCheckUtil.notEmpty(formVo.getId(),"ID");

        Stage old = mapper.selectByPrimaryKey(formVo.getId());
        if(old == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);


        Stage e = new Stage();
        SBeanUtils.copyProperties2(formVo,e);
        e.setCreateTime(old.getCreateTime());
        fillFields(e);

        mCheck(e);
        uniqueCheck(e);
        mapper.updateByPrimaryKeySelective(e);
    }
}
