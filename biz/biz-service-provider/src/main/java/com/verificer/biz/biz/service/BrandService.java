package com.verificer.biz.biz.service;

import com.verificer.beans.AreaVo;
import com.verificer.biz.beans.vo.BrandVo;
import com.verificer.biz.beans.vo.req.BrandDelVo;
import com.verificer.biz.beans.vo.req.BrandFormVo;
import com.verificer.biz.beans.vo.req.BrandListQryVo;
import com.verificer.biz.beans.vo.req.BrandPageQryVo;

import java.util.List;

public interface BrandService {
    /**
     * 分页获取品牌
     * @param qryVo
     * @return
     */
    List<BrandVo> brandPage(BrandPageQryVo qryVo);

    /**
     * 统计符合条件的品牌数
     * @param qryVo
     * @return
     */
    int brandCount(BrandPageQryVo qryVo);

    /**
     * 品牌列表
     * @param qryVo
     * @return
     */
    List<BrandVo> brandList(BrandListQryVo qryVo);


    /**
     * 新增Brand
     * @param formVo
     */
    void brandAdd(BrandFormVo formVo);

    /**
     * 修改Brand
     * @param formVo
     */
    void brandUpd(BrandFormVo formVo);

    /**
     * 删除Brand
     * @param delVo
     */
    void brandDel(BrandDelVo delVo);
}
