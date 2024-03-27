package com.verificer.biz.biz.service;

import com.verificer.biz.beans.vo.CatVo;
import com.verificer.biz.beans.vo.req.CatDelVo;
import com.verificer.biz.beans.vo.req.CatFormVo;
import com.verificer.biz.beans.vo.req.CatListQryVo;
import com.verificer.biz.beans.vo.req.CatPageQryVo;
import com.verificer.biz.biz.entity.Category;

import java.util.List;

public interface CatService {
    /**
     * 分类列表（不分页）
     * @param qryVo
     * @return
     */
    List<CatVo> catList(CatListQryVo qryVo);


    /**
     * 分页获取品牌
     * @param qryVo
     * @return
     */
    List<CatVo> catPage(CatPageQryVo qryVo);

    /**
     * 统计符合条件的品牌数
     * @param qryVo
     * @return
     */
    int catCount(CatPageQryVo qryVo);


    /**
     * 新增Brand
     * @param formVo
     */
    void catAdd(CatFormVo formVo);

    /**
     * 修改Brand
     * @param formVo
     */
    void catUpd(CatFormVo formVo);

    /**
     * 删除Brand
     * @param delVo
     */
    void catDel(CatDelVo delVo);

    List<Category> getAll();
}
