package com.verificer.base.sup.pvd.service;

import com.verificer.beans.HelpFormVo;
import com.verificer.beans.HelpPageVo;
import com.verificer.beans.IdVo;
import com.verificer.beans.suportVo.HelpVo;

import java.util.List;

public interface HelpService {
    List<HelpVo> helpPage(String language, HelpPageVo queryVo);

    int helpCount(HelpPageVo queryVo);

    void helpAdd(HelpFormVo fVo);

    void helpUpd(HelpFormVo fVo);

    void helpDel(IdVo idVo);

    HelpVo helpDetail(IdVo idVo);

}
