package com.verificer.base.sup.pvd.service;

import com.verificer.beans.AdvertPageVo;
import com.verificer.beans.AdvertFormVo;
import com.verificer.beans.IdVo;
import com.verificer.beans.suportVo.AdvertVo;

import java.util.List;

public interface AdvertService  {

    List<AdvertVo> advertPage(String language, AdvertPageVo queryVo);

    int advertCount(AdvertPageVo queryVo);

    void advertAdd(AdvertFormVo fVo);

    void advertUpd(AdvertFormVo fVo);

    void advertDel(IdVo idVo);
}
