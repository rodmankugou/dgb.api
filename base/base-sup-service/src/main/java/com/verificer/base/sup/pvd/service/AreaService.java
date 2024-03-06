package com.verificer.base.sup.pvd.service;

import com.verificer.beans.AreaQryVo;
import com.verificer.beans.AreaVo;

import java.util.List;

public interface AreaService {
    /**
     * 区域列表
     * @param qryVo
     * @return
     */
    List<AreaVo> areaList(AreaQryVo qryVo);

    /**
     * 根据code获取Area
     * @param code
     * @return
     */
    AreaVo areaGetByCode(String code);
}
