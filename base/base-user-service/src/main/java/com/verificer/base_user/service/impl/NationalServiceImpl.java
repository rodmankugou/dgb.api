package com.verificer.base_user.service.impl;

import com.verificer.ErrCode;
import com.verificer.base_user.entity.National;
import com.verificer.base_user.mapper.NationalMapper;
import com.verificer.base_user.service.NationalService;
import com.verificer.beans.NationalVo;
import com.verificer.common.exception.BaseException;
import com.verificer.dubbo.BaseDubboService;
import com.verificer.enums.LanguageType;
import com.verificer.utils.reflect.SBeanUtils;
import com.verificer.utils.SStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 35336 on 2020/12/25.
 */
@Service("nationalService")
public class NationalServiceImpl extends BaseDubboService implements NationalService {
    @Autowired
    NationalMapper nationalMapper;

    @Override
    public List<NationalVo> queryEnableNational() {
        List<National> nationals = nationalMapper.queryEnableNational();

        List<NationalVo> rstList = new ArrayList<>();
        for(National national : nationals){
            NationalVo vo = new NationalVo();
            vo.setCountryCode(national.getCountryCode() == null ? null : national.getCountryCode().toString());
            SBeanUtils.copyProperties2(national,vo);
            rstList.add(vo);
        }
        return rstList;
    }

    /**
     * 判断某个national是否存在并且是否有效
     * @return
     */
    @Override
    public boolean checkExistAndEnable(Long nationalId) {
        if(nationalId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        National national = nationalMapper.selectByPrimaryKey(nationalId);
        return national != null && national.getEnable();
    }

    /**
     * 根据id获取对应语言环境下的名称
     * @param nationalId
     * @param language
     * @return
     */
    @Override
    public String getName(Long nationalId, String language) {
        if(nationalId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(SStringUtils.isEmpty(language)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }


        National national = nationalMapper.selectByPrimaryKey(nationalId);
        if(national == null){
            return null;
        }

        if(LanguageType.CN_TW.getName().equals(language)){
            return national.getNationalityTw();
        }else if(LanguageType.CN_ZH.getName().equals(language)){
            return national.getNationality();
        }else if(LanguageType.EN_US.getName().equals(language)){
            return national.getNationalityEn();
        }
        return null;
    }
}
