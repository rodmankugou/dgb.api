package com.verificer.base.sup.itf;

import java.util.List;
import com.verificer.beans.*;

public interface BaseSupService {
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
