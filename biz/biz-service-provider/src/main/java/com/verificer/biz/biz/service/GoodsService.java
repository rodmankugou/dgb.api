package com.verificer.biz.biz.service;

import com.verificer.biz.beans.vo.GoodsVo;
import com.verificer.biz.beans.vo.req.*;

import java.util.List;

public interface GoodsService {
    /**
     * 分页查询商品
     * @param qryVo
     * @return
     */
    List<GoodsVo> goodsPage(GoodsQryVo qryVo);

    /**
     * 统计符合查询条件的商品数
     * @param qryVo
     * @return
     */
    int goodsCount(GoodsQryVo qryVo);

    /**
     * 新增商品
     * @param formVo
     */
    void goodsAdd(GoodsFormVo formVo);

    /**
     * 修改商品
     * @param formVo
     */
    void goodsUpd(GoodsFormVo formVo);

    /**
     * 删除（放入回收站），商品列表的删除功能使用该接口，商品被删除后将被放入回收站
     * @param reqVo
     */
    void goodsRubbish(GoodsRubbishVo reqVo);

    /**
     * 恢复，将回收站中的商品恢复
     * @param reqVo
     */
    void goodsRecover(GoodsRecoverVo reqVo);

    /**
     * 删除，删除回收站中的商品，回收站中的商品被删除后将不可恢复
     * @param reqVo
     */
    void goodsDel(GoodsDelVo reqVo);


    /**
     * 上架/下架
     * @param reqVo
     */
    void goodsUpdSaleFlag(GoodsUpdSaleFlagVo reqVo);
}
