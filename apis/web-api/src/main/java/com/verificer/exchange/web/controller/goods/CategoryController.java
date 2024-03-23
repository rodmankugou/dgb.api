package com.verificer.exchange.web.controller.goods;

import com.verificer.biz.beans.vo.CatVo;
import com.verificer.biz.beans.vo.cat.ACatVo;
import com.verificer.biz.beans.vo.req.CatFormVo;
import com.verificer.biz.beans.vo.req.CatListQryVo;
import com.verificer.biz.biz.service.BizService;
import com.verificer.exchange.web.controller.BaseController;
import com.verificer.utils.SBeanUtils;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 35336 on 2021/2/26.
 */
@Api(tags = "分类")
@RequestMapping("/category")
@RestController
public class CategoryController extends BaseController {

    @Autowired
    BizService bizService;


    @ApiOperation(
            value = "列表（分页）",
            response = ACatVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            
    })
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Object page(@RequestBody CatListQryVo qryVo) {
        List<CatVo> list = bizService.catList(qryVo);
        List<ACatVo> vo = new LinkedList<>();
        for(CatVo c : list){
            ACatVo a = new ACatVo();
            SBeanUtils.copyProperties2(c,a);
            vo.add(a);

        }
        return Response.dataSuccess(list);
    }





}
