package com.verificer.base.sup.pvd.service.impl;

import com.verificer.base.sup.pvd.entity.Area;
import com.verificer.base.sup.pvd.mapper.AreaMapper;
import com.verificer.base.sup.pvd.service.AreaService;
import com.verificer.beans.AreaQryVo;
import com.verificer.beans.AreaVo;
import com.verificer.utils.reflect.SBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AreaServiceImpl implements AreaService {
    @Autowired
    AreaMapper areaMapper;

    @Override
    public List<AreaVo> areaList(AreaQryVo qryVo) {
        List<Area> list = areaMapper.list(qryVo);
        List<AreaVo> voList = new LinkedList<>();
        for(Area a : list){
            AreaVo vo = new AreaVo();
            SBeanUtils.copyProperties2(a,vo);
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public AreaVo areaGetByCode(String code) {
        Area area = areaMapper.selectByCode(code);
        AreaVo vo = new AreaVo();
        SBeanUtils.copyProperties2(area,vo);
        return vo;
    }
}
