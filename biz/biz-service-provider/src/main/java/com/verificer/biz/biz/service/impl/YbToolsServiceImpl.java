package com.verificer.biz.biz.service.impl;

import com.verificer.biz.beans.vo.CatVo;
import com.verificer.biz.beans.vo.GoodsVo;
import com.verificer.biz.beans.vo.req.CatListQryVo;
import com.verificer.biz.beans.vo.req.DbgOrderFormVo2;
import com.verificer.biz.beans.vo.req.GoodsQryVo;
import com.verificer.biz.biz.entity.Category;
import com.verificer.biz.biz.pospay.YinBaoClient;
import com.verificer.biz.biz.pospay.entity.YbCat;
import com.verificer.biz.biz.service.CatService;
import com.verificer.biz.biz.service.GoodsService;
import com.verificer.biz.biz.service.YbToolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class YbToolsServiceImpl implements YbToolsService {

    @Autowired
    CatService catService;

    @Autowired
    GoodsService goodsService;

    @Override
    public void ybSync() {

    }

    private void syncCat(){
        List<CatVo> catList = getCatList();
    }

    private List<CatVo> getCatList(){
        List<CatVo> catList = catService.catList(new CatListQryVo());
        if(catList.size() != 2)
            throw new RuntimeException("分类表的数据与预期不一致，条数应该为2，实际为"+catList.size());
        return catList;
    }

    private List<GoodsVo> getGoodsList(){
        List<GoodsVo> goodsVos = goodsService.goodsPage(new GoodsQryVo());
        if(goodsVos.size() != 2)
            throw new RuntimeException("商品表的数据与预期不一致，条数应该为2，实际为"+goodsVos.size());
        return goodsVos;
    }


}
