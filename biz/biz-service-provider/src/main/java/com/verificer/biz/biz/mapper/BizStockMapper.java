package com.verificer.biz.biz.mapper;

import com.verificer.biz.beans.vo.shop.ShopStockVo;
import com.verificer.biz.beans.vo.shop.req.ShopStockQryVo;
import com.verificer.biz.beans.vo.stage.StageStockVo;
import com.verificer.biz.beans.vo.stage.req.StageStockQryVo;
import com.verificer.biz.beans.vo.stock.MerStockStaVo;
import com.verificer.biz.beans.vo.stock.MerStockVo;
import com.verificer.biz.beans.vo.stock.StaMaxMinVo;
import com.verificer.biz.beans.vo.stock.req.StockMerQryVo;
import com.verificer.biz.biz.entity.Stock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BizStockMapper {

    List<ShopStockVo> shopPage(ShopStockQryVo qryVo);

    int shopCount(ShopStockQryVo qryVo);

    List<StageStockVo> stagePage(StageStockQryVo qryVo);

    int stageCount(StageStockQryVo qryVo);

    List<StaMaxMinVo> staMaxMin(ShopStockQryVo qryVo);
}
