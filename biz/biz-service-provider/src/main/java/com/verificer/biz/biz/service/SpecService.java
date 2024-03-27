package com.verificer.biz.biz.service;

import com.verificer.biz.beans.vo.SpecVo;
import com.verificer.biz.beans.vo.req.SpecReqVo;
import com.verificer.biz.biz.entity.Goods;
import com.verificer.biz.biz.entity.Spec;

import java.util.List;

public interface SpecService {
    void add(Goods goods, List<SpecReqVo> specList);

    Spec getById(Long specId);

    /**
     * 获取未被删除的specId
     * @param specId
     * @return
     */
    Spec getActById(Long specId);

    List<SpecVo> getGoodsSpecVoList(Long id);

    List<Spec> getGoodsSpecList(Long id);

    void upd(Goods goods, List<SpecReqVo> list);

    List<Spec> getAll();
}
