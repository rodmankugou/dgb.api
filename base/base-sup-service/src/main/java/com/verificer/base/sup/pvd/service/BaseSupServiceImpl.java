package com.verificer.base.sup.pvd.service;

import com.verificer.base.sup.itf.BaseSupService;
import com.verificer.beans.AreaQryVo;
import com.verificer.beans.AreaVo;
import com.verificer.dubbo.BaseDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("baseSupService")
public class BaseSupServiceImpl extends BaseDubboService implements BaseSupService {
    @Autowired
    AreaService areaService;

    @Override
    public List<AreaVo> areaList(AreaQryVo qryVo) {
        return areaService.areaList(qryVo);
    }

    @Override
    public AreaVo areaGetByCode(String code) {
        return null;
    }
}
