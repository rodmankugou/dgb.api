package com.verificer.base_user.service;

import com.verificer.beans.NationalVo;

import java.util.List;

/**
 * Created by 35336 on 2020/12/25.
 */
public interface NationalService {
    /**
     * 获取可用的地区列表
     * @return
     */
    List<NationalVo> queryEnableNational();

    /**
     * 判断某个national是否存在并且是否有效
     * @return
     */
    boolean checkExistAndEnable(Long nationalId);

    /**
     * 根据id获取对应语言环境下的名称
     * @param nationalId
     * @param language
     * @return
     */
    String getName(Long nationalId, String language);
}
